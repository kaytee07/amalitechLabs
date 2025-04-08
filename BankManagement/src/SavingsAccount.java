public class SavingsAccount extends  Account{

    private static final double withdrawalLimit = 300;


    SavingsAccount(){
        this(0);
    }

    SavingsAccount(double Balance){
        super(Balance);
    }

  

    @Override
    public void withdraw(double amount){
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }

        double Balance = super.getBalance();
        if (Balance - amount > withdrawalLimit) {

            transactionHistory.addToHistory(amount, "withdraw");
            super.setBalance(Balance - amount);
            System.out.println("you have withdrawn an ammount of: " + amount);
            return;
        }
        System.out.println("withdrawal amount exceeds account limit: ");
        return;
    }


}
