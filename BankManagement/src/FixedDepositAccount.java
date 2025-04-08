public class FixedDepositAccount extends  Account{

    private int Duration;
    final private double interestRate = 0.05;
    final private double penaltyRate = 0.02;

    FixedDepositAccount(double Deposit, int Duration){
        super(Deposit);
        this.Duration = Duration;
    }

    void withdrawal(double amount){

    }



}
