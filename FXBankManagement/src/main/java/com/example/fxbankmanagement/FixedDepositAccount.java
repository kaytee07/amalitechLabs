package com.example.fxbankmanagement;

public class FixedDepositAccount extends  Account{

    private int Duration = 24 / 12;;
    private int timePassed = 25;
    final private double interestRate = 0.05;
    final private double penaltyRate = 0.02;

    FixedDepositAccount(User user, Double deposit){
        super(user, deposit);
    }

    @Override
    public void deposit (double amount) throws Exception {
        /*
        * deposit: to deposit amount to balance but first check if there have been an initial deposit
        * amount: amount the user wants
        * */
        if (super.getBalance() > 0) {
            throw new Exception("Wait for deposit to mature before making another deposit");
        } else {
            transactionHistory.addToHistory(new Transaction(amount, "deposit"));
            super.deposit(amount);
        }
    }

    @Override
    public void withdraw(double amount) throws Exception{
        if (getBalance() >= amount){
            setBalance(getBalance() - amount);
            transactionHistory.addToHistory(new Transaction(amount, "withdraw"));
        } else {
            throw new Exception("insufficient funds");
        }

    }

    public void addInterest() throws Exception {
        double balance = getBalance() + calculateInterest(timePassed);
        setBalance(balance);
    }

    public double calculateInterest(int timeElapsed) throws Exception{
        if (timeElapsed < 1) throw  new Exception("you just made an initial deposit");
        double rate;
        if (timeElapsed >= Duration){
            rate = interestRate;
        } else {
            rate = penaltyRate;
        }
        return getBalance() * rate * Duration;
    }

}
