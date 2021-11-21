package com.example.jbdl.major_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.example.jbdl.major_project.TransactionStatus.PENDING;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_CREATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_COMPLETE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.AMOUNT_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.SENDER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.RECEIVER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_ID_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_KAFKA_TOPIC;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_STATUS_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.WALLET_UPDATE_SUCCESS_STATUS;
import static com.example.jbdl.major_project.CommonConstants.EMAIL_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_STATUS_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_TIME_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.ACTOR_RECEIVER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.ACTOR_SENDER_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.ACTOR_TYPE_ATTRIBUTE;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_SUCCESS_STATUS;
import static com.example.jbdl.major_project.CommonConstants.TRANSACTION_FAILED_STATUS;


import java.util.Date;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    TransactionRepository transactionRepository;

    public String doTransaction(TransactionRequest transactionRequest) throws JsonProcessingException {

        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(PENDING)
                .amount(transactionRequest.getAmount())
                .purpose(transactionRequest.getPurpose())
                .sender(transactionRequest.getSender())
                .receiver(transactionRequest.getReceiver())
                .build();

        transactionRepository.save(transaction);

        JSONObject transactionCreateRequest = new JSONObject();
        transactionCreateRequest.put(AMOUNT_ATTRIBUTE, transaction.getAmount());
        transactionCreateRequest.put(SENDER_ATTRIBUTE, transaction.getSender());
        transactionCreateRequest.put(RECEIVER_ATTRIBUTE, transaction.getReceiver());
        transactionCreateRequest.put(TRANSACTION_ID_ATTRIBUTE, transaction.getTransactionId());


        kafkaTemplate.send(TRANSACTION_CREATE_KAFKA_TOPIC, objectMapper.writeValueAsString(transactionCreateRequest));

        return transaction.getTransactionId();
    }

    @KafkaListener(topics = {WALLET_UPDATE_KAFKA_TOPIC}, groupId = "jbdl123_grp")
    public void updateTransaction(String msg) throws JsonProcessingException {

        JSONObject walletUpdate = objectMapper.readValue(msg, JSONObject.class);

        String transactionId = (String)walletUpdate.get(TRANSACTION_ID_ATTRIBUTE);
        String walletUpdateStatus = (String) walletUpdate.get(WALLET_UPDATE_STATUS_ATTRIBUTE);
        String receiver = (String) walletUpdate.get(RECEIVER_ATTRIBUTE);
        String sender = (String) walletUpdate.get(SENDER_ATTRIBUTE);
        Double amount = (Double) walletUpdate.get(AMOUNT_ATTRIBUTE);

//        Transaction transaction = transactionRepository.findByTransactionId(transactionId);
//        TransactionStatus transactionStatus = WALLET_UPDATE_SUCCESS_STATUS.equals(walletUpdateStatus) ?
//                TransactionStatus.SUCCESS : TransactionStatus.FAILED;
//
//        transaction.setTransactionStatus(transactionStatus);
//        transactionRepository.save(transaction);

        String status;

        if(WALLET_UPDATE_SUCCESS_STATUS.equals(walletUpdateStatus)){
            status = TRANSACTION_SUCCESS_STATUS;
            transactionRepository.updateTxn(transactionId, TransactionStatus.SUCCESS);
        }else{
            status = TRANSACTION_FAILED_STATUS;
            transactionRepository.updateTxn(transactionId, TransactionStatus.FAILED);
        }

        System.out.println("---------------------");

        // failed - sender
        // success - sender, receiver

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(EMAIL_ATTRIBUTE, sender);
        jsonObject.put(ACTOR_TYPE_ATTRIBUTE, ACTOR_SENDER_ATTRIBUTE);
        jsonObject.put(AMOUNT_ATTRIBUTE, amount);
        jsonObject.put(TRANSACTION_ID_ATTRIBUTE, transactionId);
        jsonObject.put(TRANSACTION_STATUS_ATTRIBUTE, status);
        jsonObject.put(TRANSACTION_TIME_ATTRIBUTE, new Date());

        kafkaTemplate.send(TRANSACTION_COMPLETE_KAFKA_TOPIC, objectMapper.writeValueAsString(jsonObject));

        if(WALLET_UPDATE_SUCCESS_STATUS.equals(walletUpdateStatus)){
            jsonObject = new JSONObject();
            jsonObject.put(EMAIL_ATTRIBUTE, receiver);
            jsonObject.put(ACTOR_TYPE_ATTRIBUTE, ACTOR_RECEIVER_ATTRIBUTE);
            jsonObject.put(AMOUNT_ATTRIBUTE, amount);
            jsonObject.put(TRANSACTION_ID_ATTRIBUTE, transactionId);
            jsonObject.put(TRANSACTION_STATUS_ATTRIBUTE, status);
            jsonObject.put(TRANSACTION_TIME_ATTRIBUTE, new Date());

            kafkaTemplate.send(TRANSACTION_COMPLETE_KAFKA_TOPIC, objectMapper.writeValueAsString(jsonObject));
        }
    }
}
