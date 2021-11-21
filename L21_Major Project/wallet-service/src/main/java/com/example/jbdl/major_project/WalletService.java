package com.example.jbdl.major_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.jbdl.major_project.CommonConstants.USER_CREATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.PHONE_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.EMAIL_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_CREATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_ID_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.RECEIVER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.SENDER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.AMOUNT_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_STATUS_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_SUCCESS_STATUS;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_FAILED_STATUS;


@Service
public class WalletService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WalletRepository walletRepository;

    @Value("${amount.wallet.create}")
    Double amount;

    @KafkaListener(topics = {USER_CREATE_KAFKA_TOPIC}, groupId = "jdbl19_grp")
    public void createWallet(String msg) throws JsonProcessingException {
        JSONObject createWalletReq = objectMapper.readValue(msg, JSONObject.class);

        String email = (String) createWalletReq.get(EMAIL_ATTRIBUTE);
        String phone = (String) createWalletReq.get(PHONE_ATTRIBUTE);

        Wallet wallet = Wallet.builder()
                .email(email)
                .phone(phone)
                .balance(amount)
                .build();

        walletRepository.save(wallet);
    }

    @KafkaListener(topics = {TRANSACTION_CREATE_KAFKA_TOPIC}, groupId = "jdbl19_grp")
    public void updateWallet(String msg) throws JsonProcessingException {

        JSONObject updateWalletReq = objectMapper.readValue(msg, JSONObject.class);

        String transactionId = (String) updateWalletReq.get(TRANSACTION_ID_ATTRIBUTE);
        String sender = (String) updateWalletReq.get(SENDER_ATTRIBUTE);
        String receiver = (String) updateWalletReq.get(RECEIVER_ATTRIBUTE);
        Double amount = (Double) updateWalletReq.get(AMOUNT_ATTRIBUTE);

        Wallet senderWallet = walletRepository.findByEmail(sender);
        Wallet receiverWallet = walletRepository.findByEmail(receiver);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TRANSACTION_ID_ATTRIBUTE, transactionId);
        jsonObject.put(WALLET_UPDATE_STATUS_ATTRIBUTE, WALLET_UPDATE_FAILED_STATUS);
        jsonObject.put(SENDER_ATTRIBUTE, sender);
        jsonObject.put(RECEIVER_ATTRIBUTE, receiver);
        jsonObject.put(AMOUNT_ATTRIBUTE, amount);

        if(senderWallet != null && senderWallet.getBalance() - amount >= 0) {
            if(receiverWallet == null){
                receiverWallet = Wallet.builder()
                        .email(receiver)
                        .balance(amount)
                        .build();
                walletRepository.save(receiverWallet);
            }else{
                walletRepository.updateWallet(receiver, amount);
            }

            walletRepository.updateWallet(sender, 0 - amount);

            jsonObject.put(WALLET_UPDATE_STATUS_ATTRIBUTE, WALLET_UPDATE_SUCCESS_STATUS);
        }


        kafkaTemplate.send(WALLET_UPDATE_KAFKA_TOPIC, objectMapper.writeValueAsString(jsonObject));
    }
}
