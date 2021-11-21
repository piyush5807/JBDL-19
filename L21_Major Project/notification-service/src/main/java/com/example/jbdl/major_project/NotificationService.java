package com.example.jbdl.major_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.jbdl.major_project.CommonConstants.*;

@Service
public class NotificationService {

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = {TRANSACTION_COMPLETE_KAFKA_TOPIC}, groupId = "jbdl123_grp")
    public void publishEmail(String msg) throws Exception {

        JSONObject transactionCompleteRequest = objectMapper.readValue(msg, JSONObject.class);


        String email = (String) transactionCompleteRequest.get(EMAIL_ATTRIBUTE);
        String actor = (String) transactionCompleteRequest.get(ACTOR_TYPE_ATTRIBUTE);
        Double amount = (Double) transactionCompleteRequest.get(AMOUNT_ATTRIBUTE);
        String transactionId = (String) transactionCompleteRequest.get(TRANSACTION_ID_ATTRIBUTE);
        String status = (String) transactionCompleteRequest.get(TRANSACTION_STATUS_ATTRIBUTE);
        Long timeInMillis = (Long) transactionCompleteRequest.get(TRANSACTION_TIME_ATTRIBUTE);

        Date date = new Date(timeInMillis);

        String message;

        if (ACTOR_SENDER_ATTRIBUTE.equals(actor)) {
            switch (status) {
                case TRANSACTION_SUCCESS_STATUS:
                    message = "Transaction with id " + transactionId + " has been completed at " + date +
                            ", your account has been debited by amount " + amount;
                    break;
                case TRANSACTION_FAILED_STATUS:
                    message = "Transaction with id " + transactionId + " has failed at " + date + ", Please retry!!";
                    break;
                default:
                    throw new Exception("Invalid status found");
            }
        } else {
            message = "Your account has been credit with amount " + amount + " on " + date.toString();
        }


        simpleMailMessage.setText(message);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("geeks.jbdl19@gmail.com");
        simpleMailMessage.setSubject("Payment Notification!!!");

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (Exception e){
            // other table // retry logic
        }
    }
}
