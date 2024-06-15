package ir.ac.kntu.database;

import ir.ac.kntu.person.Customer;

import java.util.*;

public class ContactDatabase {
    private Set<Customer> contactList;

    public ContactDatabase() {
        contactList = new HashSet<>();
    }

    public List<Customer> getContactList() {
        List<Customer> returns = new ArrayList<>();
        returns.addAll(contactList);
        return returns;
    }

    public void setContactList(Set<Customer> contactList) {
        this.contactList = contactList;
    }

    public void addContact(Customer customer) {
        contactList.add(customer);
    }

    public boolean doesContactExist(Customer customer) {
        return contactList.contains(customer);
    }

    public Customer findCustomer(String cellNumber, Database database) {
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber)) {
                return customer;
            }
        }
        return null;
    }

    public void removeContact(Customer customer) {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContactDatabase that = (ContactDatabase) obj;
        return Objects.equals(contactList, that.contactList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactList);
    }

}
