package com.example.fxbankmanagement;

import com.example.fxbankmanagement.User;

public class SavingsAccount extends  Account{

    private static final double withdrawalLimit = 300;

    SavingsAccount(User user){
        this(user, 0);
    }

    SavingsAccount(User user, double Balance){
        super(user, Balance);
    }


    @Override
    public void withdraw(double amount) throws Exception{
        /*
        * Withdraw: withdraws amount from the account if balance is greater than limit
        * amount: amount you want to withdraw
        * calculate interest on witdrawal
        * return nothing
        */
        if (amount <= 0) {
            throw  new Exception("Withdrawal amount must be positive");
        }

        double Balance = super.getBalance();
        if (Balance - amount > withdrawalLimit) {

            transactionHistory.addToHistory(amount, "withdraw");
            super.setBalance(Balance - amount);
            System.out.println("you have withdrawn an ammount of: " + amount);
            return;
        }
        throw new Exception("withdrawal amount exceeds account limit: ");

    }

}
