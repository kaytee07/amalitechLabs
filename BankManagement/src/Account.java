import java.util.UUID;

public abstract  class Account implements BankOperations{
    private double Balance;
    private User user;
    private String identifier;
    protected Transactions transactionHistory;

    Account(double amount, User user){
        this.user = user;
        Balance = amount;
        identifier =  UUID.randomUUID().toString().replace("-", "").substring(0, 14);
        transactionHistory = new Transactions();
        getAccountNumber();
    }


    void getAccountNumber(){
        System.out.println("Your account number is: "+ identifier);
    }

    public double getBalance(){
        return Balance;
    }

    public void setBalance(double amount){
        Balance = amount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        transactionHistory.addToHistory(amount, "deposit");
        Balance += amount;
    }

    public void getId(){
        System.out.println("Your account Number is " + identifier);
    }

    abstract public void withdraw(double amount);



    public void getLastNHistory(int NumOfTransactions) {
        System.out.println("Traansaction history account Number " + identifier);
        transactionHistory.printHstory(NumOfTransactions);
    }



    public void checkBalance(){
        System.out.println("Your account balance is: " + Balance);
    }

}
