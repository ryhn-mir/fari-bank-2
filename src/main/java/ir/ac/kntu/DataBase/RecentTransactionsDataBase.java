package ir.ac.kntu.DataBase;

import ir.ac.kntu.Person.Customer;

import java.util.LinkedList;
import java.util.List;

public class RecentTransactionsDataBase {
    private List<Customer> recentTransactions;

    public RecentTransactionsDataBase() {
        recentTransactions = new LinkedList<>();
    }

    public List<Customer> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<Customer> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }

    public void addTransactions(Customer customer) {
        recentTransactions.add(customer);
    }

    public void printContact() {
        int count = 1;
        for (Customer contact : recentTransactions) {
            System.out.println(contact + "." + contact.getFirstName() + " " + contact.getLastName());
            count++;
        }
    }
}
