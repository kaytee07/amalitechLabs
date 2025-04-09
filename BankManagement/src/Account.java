public abstract  class Account implements BankOperations{
    private double Balance;
    private String identifier;
    protected Transactions transactionHistory;

    Account(double amount){
        Balance = amount;
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

    abstract public void withdraw(double amount);

    public void getLastNHistory(int NumOfTransactions) {
        transactionHistory.printHstory(NumOfTransactions);
    }



    public void checkBalance(){
        System.out.println("Your account balance is: " + Balance);
    }

}
