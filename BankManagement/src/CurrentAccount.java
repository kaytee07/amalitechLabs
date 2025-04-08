public class CurrentAccount extends Account{

    final private double overDraftLimit = 300.00;
    private double overDraftRemaining = 300.00;


    CurrentAccount(){
        this(0);
    }

    CurrentAccount(double Balance){
        super(Balance);
    }

    public void deposit(double amount){
        double balance = getBalance();
        super.setBalance(balance + amount);
    }

    public void withdraw(double amount){
        double balance = getBalance();
        double amtExceedingLimit;
        if(balance < amount){
           amtExceedingLimit  = amount - balance;
           if (overDraftRemaining - amtExceedingLimit < 0){
               System.out.println("Overdraft limit reached");
           } else {
               overDraftRemaining -= amtExceedingLimit;
            }
           setBalance(0);
        } else {
            super.setBalance(getBalance() - amount);
        }
    }


}
