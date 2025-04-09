//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Account account = new SavingsAccount(600, new User("Kofi Taylor"));
        account.withdraw(500);
        account.checkBalance();
        account.deposit(2000);
        account.withdraw(1500);
        account.getLastNHistory(2);

        Account account1 = new CurrentAccount(400, new User("Jeremy Taylor"));
        account1.withdraw(3000);
        account1.checkBalance();
        account1.deposit(2800);
        account1.withdraw(1200);
        account1.getLastNHistory(2);

    }
}