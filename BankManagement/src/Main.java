//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Account account = new SavingsAccount(new User("Kofi Taylor"),600);
        account.withdraw(500);
        account.checkBalance();
        account.deposit(2000);
        account.withdraw(1500);
        account.getLastNHistory(2);

        Account account1 = new CurrentAccount(new User("Jeremy Taylor"), 400);
        account1.withdraw(3000);
        account1.checkBalance();
        account1.deposit(2800);
        account1.withdraw(1200);
        account1.getLastNHistory(2);

    }
}