public class User {
    private Account account;
    private double identifier;



    public void setAccount(Account account) {
        this.account = account;
    }

    public void deposit(double amount){
        account.deposit(amount);
    }

    public void withdraw(double amount) {
        account.withdraw(amount);
    }

  
    public void checkBalance(){
        account.checkBalance();
    }


}
