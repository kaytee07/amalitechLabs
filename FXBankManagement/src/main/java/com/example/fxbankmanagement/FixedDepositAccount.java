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
    public void deposit (double amount) {
        /*
        * deposit: to deposit amount to balance but first check if there have been an initial deposit
        * amount: amount the user wants
        * */
        if (super.getBalance() > 0) {
            System.out.println("Wait for deposit to mature before making another deposit");
        } else {
            super.deposit(amount);
        }
    }


    @Override
    public void withdraw(double amount) throws Exception{
       double balance = getBalance();
       double interest;  
       double payout;

       if (balance == 0){
           throw new Exception("No funds available in this account");
       }

        if (timePassed >= Duration){
            interest = balance * interestRate * Duration;
            payout = balance + interest;
            transactionHistory.addToHistory(payout, "withdraw");
            setBalance(0);
            System.out.println("your fixed deposit accrued: " + payout);
            return;
            
        } else {
            interest = balance * penaltyRate * timePassed;
            payout = balance + interest;
            transactionHistory.addToHistory(payout, "withdraw");
            setBalance(0);
            System.out.println("your fixed deposit accrued: " + payout);
            return;
        }
    }

}
