public abstract  class Account implements BankOperations{
    private double Balance;


    Account(double amount){
        Balance = amount;
    }

    public double getBalance(){
        return Balance;
    }

    public void setBalance(double amount){
        Balance = amount;
    }



    public void checkBalance(){
        System.out.println("Your account balance is: " + Balance);
    }

}
