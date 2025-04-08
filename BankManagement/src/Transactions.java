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
        System.out.println("========Transaction History========");
        System.out.println();
        while (current != null && lastNHistory > 0){
            System.out.printf("%s: %.2f%n",current.transactionType, current.value);
            current = current.next;
            lastNHistory--;
        }
        System.out.println();
        System.out.println("====================================");
    }
}
