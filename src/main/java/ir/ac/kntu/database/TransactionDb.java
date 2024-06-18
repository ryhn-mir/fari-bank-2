package ir.ac.kntu.database;

import ir.ac.kntu.transaction.Transaction;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TransactionDb implements Serializable {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransactionDb that = (TransactionDb) obj;
        return Objects.equals(transactions, that.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions);
    }
}
