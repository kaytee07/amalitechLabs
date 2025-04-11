package com.example.fxbankmanagement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transactions {
    private Transaction head;
    private int listLength;

    Transactions() {
        this.head = null;
    }

    public void addToHistory(Transaction transaction) {
        if (head == null) {
            head = transaction;
        } else {
            transaction.next = head;
            head = transaction;
            listLength++;
        }
    }

    public Transaction[] getNHistory(int N)throws Exception{
        if(head == null) throw new Exception("You have made no transactions");
        int index = 0;
        int actualSize = Math.min(N, listLength);
        Transaction[] transaction = new Transaction[actualSize];
        Transaction current = this.head;
        while(current != null && index < actualSize){
            transaction[index] = current;
            current = current.next;
            index++;
        }

        return  transaction;
    }


}

