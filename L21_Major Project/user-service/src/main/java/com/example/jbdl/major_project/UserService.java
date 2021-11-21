package com.example.jbdl.major_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.jbdl.major_project.CommonConstants.USER_CREATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.EMAIL_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.PHONE_ATTRIBUTE;

@Service
public class UserService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String USER_CREATE_TOPIC = USER_CREATE_KAFKA_TOPIC;

    @Autowired
    UserRepository userRepository;

    public User getUser(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void createUser(User user) throws JsonProcessingException {
        userRepository.save(user);

        //TODO: Publish kafka event so that wallet service
        // can consume and add some default money (Rs.10) to the user's wallet

        JSONObject userCreateRequest = new JSONObject();
        userCreateRequest.put(EMAIL_ATTRIBUTE, user.getEmail());
        userCreateRequest.put(PHONE_ATTRIBUTE, user.getPhone());

        kafkaTemplate.send(USER_CREATE_TOPIC, objectMapper.writeValueAsString(userCreateRequest));
    }
}
