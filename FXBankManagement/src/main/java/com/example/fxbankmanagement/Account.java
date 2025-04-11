package com.example.fxbankmanagement;

import com.example.fxbankmanagement.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract  class Account implements BankOperations{
    private double Balance;
    private User user;
    private String identifier;
    protected Transactions transactionHistory;

    Account(User user, double amount){
        this.user = user;
        Balance = amount;
        identifier =  UUID.randomUUID().toString().replace("-", "").substring(0, 14);
        transactionHistory = new Transactions();
    }


    String getAccountNumber(){
        return identifier;
    }

    String getName(){
        return  user.getUsername();
    }

    public double getBalance(){
        return Balance;
    }

    public void setBalance(double amount){
        Balance = amount;
    }

    public void deposit(double amount) throws Exception {
        /*
        * deposit: add amount to the balance
        * amount: that is the amount to add to the balance
        * returns void
        * */
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        Balance += amount;
    }

    public String getId(){
        /*
        * get: returns the account number
        **/
        return identifier;
    }

    abstract public void withdraw(double amount) throws  Exception;

    public Transaction[] lastNHTransaction(int NumOfTransactions) throws Exception {
        return transactionHistory.getNHistory(NumOfTransactions);
    }

    public User getUser(){
        return  user;
    }

    public double checkBalance()  {
        return Balance;
    }

    public String toString(){
        return user.getUsername() + "your account Number is" + getAccountNumber();
    }

    public void addToHistory(Transaction transaction){
        transactionHistory.addToHistory(transaction);
    }

}
