package ir.ac.kntu.DataBase;

import ir.ac.kntu.Person.Customer;

import java.util.LinkedList;
import java.util.List;

public class ContactDatabase {
    private List<Customer> contactList;
    public ContactDatabase() {
        contactList = new LinkedList<>();
    }

    public List<Customer> getContactList() {
        return contactList;
    }

    public void setContactList(List<Customer> contactList) {
        this.contactList = contactList;
    }
    public void addContact(Customer customer) {
        contactList.add(customer);
    }

    public boolean doesContactExist(Customer customer) {
        if (contactList.contains(customer)) {
            return true;
        }
        return false;
    }
    public Customer findCustomer(String cellNumber) {
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber)) {
                return customer;
            }
        }
        return null;
    }
    public void removeContact (Customer customer) {
        try {
            if (contactList.contains(customer)) {
                contactList.remove(customer);
            } else {
                throw new RuntimeException("your request contact not found");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean checkContactIsOn(String accountNumber) {
        for (Customer customer : contactList) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }
}
