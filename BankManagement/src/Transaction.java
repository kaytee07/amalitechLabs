public class Transaction {
    double value;
    Transaction next;
    String transactionType;

    Transaction(double value, String transactionType){
        this.value = value;
        this.next = null;
        this.transactionType = transactionType;
    }
}
