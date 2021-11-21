package com.example.jbdl.major_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Modifying
    @Transactional
    @Query("update Transaction t set t.transactionStatus = ?2 where t.transactionId = ?1")
    void updateTxn(String transactionId, TransactionStatus transactionStatus);

    Transaction findByTransactionId(String transactionId);
}
