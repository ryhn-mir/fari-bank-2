package ir.ac.kntu.database;

import ir.ac.kntu.transaction.Transaction;

import java.util.LinkedList;
import java.util.List;

public class TransactionDb {
    private List<Transaction> transactions;

    public TransactionDb() {
        transactions = new LinkedList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void showTransaction() {
        int count = 1;
        for (Transaction transaction : transactions) {
            System.out.println(count + "." + transaction);
            count++;
        }
    }

    @Override
    public String toString() {
        return "TransactionDb{" +
                "transactions=" + transactions +
                '}';
    }
}
