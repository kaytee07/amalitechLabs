package com.example.fxbankmanagement;

import com.example.fxbankmanagement.User;

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
        transactionHistory.addToHistory(amount, "deposit");
        Balance += amount;
    }

    public String getId(){
        /*
        * get: returns the account number
        **/
        return identifier;
    }

    abstract public void withdraw(double amount) throws  Exception;

    public List getLastNHistory(int NumOfTransactions) {
        System.out.println("Traansaction history account Number " + identifier);
        return transactionHistory.printHistory(NumOfTransactions);
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

}
