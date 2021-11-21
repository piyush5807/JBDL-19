package com.example.jbdl.major_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public String doTransaction(@RequestBody TransactionRequest transactionRequest) throws Exception {

        if(!transactionRequest.validate()){
            throw new Exception("Request is not valid");
        }

        return transactionService.doTransaction(transactionRequest);

    }
}
