public class User {
    private String name;
    private Account account;
    


    User( String name ){
        this.name = name;
    }

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
