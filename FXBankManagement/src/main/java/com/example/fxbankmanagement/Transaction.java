package com.example.fxbankmanagement;

import java.time.LocalDate;

public class Transaction {
    double value;
    Transaction next;
    String transactionType;
    String transactionDate;

    Transaction(double value, String transactionType){
        this.value = value;
        this.next = null;
        this.transactionType = transactionType;
        transactionDate = LocalDate.now().toString();
    }
}
