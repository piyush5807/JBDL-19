package com.example.jbdl.major_project;

public class CommonConstants {

    // Kafka topics constants
    public static final String USER_CREATE_KAFKA_TOPIC = "userCreate";
    public static final String TRANSACTION_CREATE_KAFKA_TOPIC = "transactionCreate";
    public static final String WALLET_UPDATE_KAFKA_TOPIC = "walletUpdate";
    public static final String TRANSACTION_COMPLETE_KAFKA_TOPIC = "transactionComplete";


    // Kafka attributes constants
    public static final String EMAIL_ATTRIBUTE = "email";
    public static final String PHONE_ATTRIBUTE = "phone";
    public static final String TRANSACTION_ID_ATTRIBUTE = "transactionId";
    public static final String SENDER_ATTRIBUTE = "sender";
    public static final String RECEIVER_ATTRIBUTE = "receiver";
    public static final String AMOUNT_ATTRIBUTE = "amount";
    public static final String WALLET_UPDATE_STATUS_ATTRIBUTE = "walletUpdateStatus";
    public static final String WALLET_UPDATE_SUCCESS_STATUS = "SUCCESS";
    public static final String WALLET_UPDATE_FAILED_STATUS = "FAILED";

    public static final String EMAIL_MESSAGE_ATTRIBUTE = "emailMsg";
    public static final String TRANSACTION_STATUS_ATTRIBUTE = "transactionStatus";
    public static final String TRANSACTION_SUCCESS_STATUS = "SUCCESS";
    public static final String TRANSACTION_FAILED_STATUS = "FAILED";
    public static final String TRANSACTION_TIME_ATTRIBUTE = "transactionTime";
    public static final String ACTOR_TYPE_ATTRIBUTE = "actor";
    public static final String ACTOR_SENDER_ATTRIBUTE = "sender";
    public static final String ACTOR_RECEIVER_ATTRIBUTE = "receiver";



//   public static final String SENDER_EMAIL_SUCCESS_MESSAGE_ = "Transaction with id %s has been completed, your account has been debited by amount %d";


}
