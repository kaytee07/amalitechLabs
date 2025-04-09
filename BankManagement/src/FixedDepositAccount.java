public class FixedDepositAccount extends  Account{

    private int Duration; 
    private int timePassed = 0;
    final private double interestRate = 0.05;
    final private double penaltyRate = 0.02;

    FixedDepositAccount(double Deposit, int Duration){
        super(Deposit);
         if (Duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
         }
        this.Duration = Duration / 12;
    }

    void increaseTimePassed(){
        timePassed++;
    }

    @Override
    public void withdraw(double amount){
       double balance = getBalance();
       double interest;  
       double payout;

       if (balance == 0){
            System.out.println("No funds available in this account");
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
