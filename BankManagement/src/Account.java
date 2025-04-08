public abstract  class Account implements BankOperations{
    private double Balance;

    private double identifier;
    protected Transactions transactionHistory;

    Account(double amount){
        Balance = amount;
        identifier =  Math.random() * 100;
        transactionHistory = new Transactions();
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
