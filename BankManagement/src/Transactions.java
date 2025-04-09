public class Transactions {
    private Transaction head;

    Transactions(){
        this.head = null;
    }

    public void addToHistory (double amount, String type){
        Transaction transaction = new Transaction(amount, type);
        if(head == null){
            head = transaction;
        } else {
            transaction.next = head;
            head = transaction;
        }
    }

    public void printHstory(int lastNHistory){
        Transaction current = this.head;
        while (current != null){
            if(lastNHistory < 0) break;
            System.out.printf("$s: %.2f",current.transactionType, current.value);
            current = current.next;
            lastNHistory--;
        }
    }
}
