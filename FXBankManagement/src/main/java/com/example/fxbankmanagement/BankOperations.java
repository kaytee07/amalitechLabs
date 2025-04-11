package com.example.fxbankmanagement;

public interface BankOperations {

    void deposit(double amount);

    void withdraw(double amount) throws Exception;


    double checkBalance();

}
