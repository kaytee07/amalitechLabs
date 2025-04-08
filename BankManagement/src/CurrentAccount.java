public class CurrentAccount extends Account{

    final private double overDraftLimit = 300.00;
    private double overDraftRemaining = 300.00;


    CurrentAccount(){
        this(0);
    }

    CurrentAccount(double Balance){
        super(Balance);
    }


    @Override
    public void deposit(double amount){
        double overdraftDebt = overDraftLimit - overDraftRemaining;
        if (amount > overdraftDebt){
            double amountAfterOverdraftDebt = amount - overdraftDebt;
            overDraftRemaining += overdraftDebt;
            transactionHistory.addToHistory(amount, "withdraw");
            super.deposit(amountAfterOverdraftDebt);
        } else {
            overDraftRemaining += amount;

        }
    }


    @Override
    public void withdraw(double amount){
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        double balance = getBalance();
        if (balance >= amount){
            setBalance(balance - amount);
            return;
        } else {
            double overdraftNeeded = amount - balance;
            if (overdraftNeeded > overDraftRemaining){
                System.out.println("Overdraft limit reached");
                return;
            }
            transactionHistory.addToHistory(amount, "withdraw");
            setBalance(0);
            overDraftRemaining -= overdraftNeeded;
            return;
        }

    }

}
