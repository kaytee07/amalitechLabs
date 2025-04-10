package com.example.fxbankmanagement;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private Transaction head;

    Transactions() {
        this.head = null;
    }

    public void addToHistory(double amount, String type) {
        Transaction transaction = new Transaction(amount, type);
        if (head == null) {
            head = transaction;
        } else {
            transaction.next = head;
            head = transaction;
        }
    }

    public List printHistory(int lastNHistory) {
        Transaction current = this.head;
        int transactionCount = 0;

        while (current != null) {
            transactionCount++;
            current = current.next;
        }

        if (transactionCount < lastNHistory) {
            lastNHistory = transactionCount;
        }

        current = this.head;
        int count = 0;


        List<Transaction> transactionsToDisplay = new ArrayList<Transaction>();

        while (current != null && count < lastNHistory) {
            transactionsToDisplay.add(current);
            current = current.next;
            count++;
        }

        return transactionsToDisplay;
    }
}

