package com.example.fxbankmanagement;

public class CurrentAccount extends Account{

    final private double overDraftLimit = 300.00;
    private double overDraftRemaining = 300.00;

    CurrentAccount(User user){

        this(user, 0);
    }
    CurrentAccount(User user, double Balance){
        super(user, Balance);
    }


    @Override
    public void deposit(double amount) throws Exception{
        double overdraftDebt = overDraftLimit - overDraftRemaining;
        if (amount > overdraftDebt){
            double amountAfterOverdraftDebt = amount - overdraftDebt;
            overDraftRemaining += overdraftDebt;
            transactionHistory.addToHistory(new Transaction(amount, "deposit"));
            super.deposit(amountAfterOverdraftDebt);
        } else {
            overDraftRemaining += amount;
            transactionHistory.addToHistory(new Transaction(amount, "deposit"));

        }
    }


    @Override
    public void withdraw(double amount) throws Exception{
        if (amount <= 0) {
            throw new IllegalArgumentException("withdrawal must be positive");
        }
        double balance = getBalance();
        if (balance >= amount){
            transactionHistory.addToHistory(new Transaction(amount, "withdraw"));
            setBalance(balance - amount);
            return;
        } else {
            double overdraftNeeded = amount - balance;
            if (overdraftNeeded > overDraftRemaining){
                throw new Exception("Overdraft limit reached");
            }
            transactionHistory.addToHistory(new Transaction(amount, "withdraw"));
            setBalance(0);
            overDraftRemaining -= overdraftNeeded;
            return;
        }

    }

}
