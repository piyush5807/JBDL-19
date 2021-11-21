package com.example.jbdl.major_project;

import lombok.*;
import org.apache.tomcat.util.buf.StringUtils;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String sender; // email of sender
    private String receiver; // email of receiver

    private Double amount;
    private String purpose;

    public boolean validate(){
        if(this.amount == null || this.amount == 0 ||
                this.sender == null || this.sender.equals("") ||
                this.receiver == null || this.receiver.equals("")){
            return false;
        }

        return true;
    }
}
