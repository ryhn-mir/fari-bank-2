package ir.ac.kntu.database;

import ir.ac.kntu.person.Customer;

import java.util.LinkedList;
import java.util.List;

public class RecentTransactionsDataBase {
    private List<Customer> recentTrans;

    public RecentTransactionsDataBase() {
        recentTrans = new LinkedList<>();
    }

    public List<Customer> getRecentTrans() {
        return recentTrans;
    }

    public void setRecentTrans(List<Customer> recentTrans) {
        this.recentTrans = recentTrans;
    }

    public void addTransactions(Customer customer) {
        recentTrans.add(customer);
    }

    public void printContact() {
        int count = 1;
        for (Customer contact : recentTrans) {
            System.out.println(count + "." + contact.getFirstName() + " " + contact.getLastName());
            count++;
        }
    }
}
