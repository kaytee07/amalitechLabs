public class SavingsAccount extends  Account{

    private double limit = 300;

    SavingsAccount(){
        this(0);
    }

    SavingsAccount(double Balance){
        super(Balance);
    }

    public void deposit(double amount){
        double balance = getBalance();
        super.setBalance(balance + amount);
    }

    public void withdraw(double amount){
        double Balance = super.getBalance();
        if (Balance - amount < limit){
            System.out.println("withdrawal exceeds withdrawal limit");
            return;
        }

        super.setBalance(Balance - amount);
        System.out.println("you have withdrawn an ammount of: " + amount);
        return;
    }


}
