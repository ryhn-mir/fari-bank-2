package ir.ac.kntu.DataBase;

import ir.ac.kntu.Transaction.Transaction;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TransactionDb {
    private List<Transaction> transactions;
    public TransactionDb(){
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
}
