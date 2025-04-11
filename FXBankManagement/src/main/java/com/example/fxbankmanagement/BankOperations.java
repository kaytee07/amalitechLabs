package com.example.fxbankmanagement;

public interface BankOperations {

    void deposit(double amount) throws  Exception;

    void withdraw(double amount) throws Exception;


    double checkBalance();

}
