package ir.ac.kntu.database;

import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private Set<Management> managementDB;
    private Set<Customer> customerDataBase;

    public Database() {
        customerDataBase = new HashSet<>();
        managementDB = new HashSet<>();
        managementDB.add(new Management("reyhane", "arabshahi", "Rr@138406", "reyhane123"));
        managementDB.add(new Management("sara", "ahmadi", "Ss@138310", "sara123"));
    }

    public Set<Management> getManagementDB() {
        return managementDB;
    }

    public Set<Customer> getCustomerDataBase() {
        return customerDataBase;
    }

    public void setManagementDB(Set<Management> managementDB) {
        this.managementDB = managementDB;
    }

    public void setCustomerDataBase(Set<Customer> customerDataBase) {
        this.customerDataBase = customerDataBase;
    }

    public Customer findReceiver(String accountNumber) {
        Set<Customer> customersDB = getCustomerDataBase();
        for (Customer customer : customersDB) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    public Management findManagement(String userName) {
        Set<Management> managementDB = getManagementDB();
        for (Management management : managementDB) {
            if (management.getUserName().equals(userName)) {
                return management;
            }
        }
        return null;
    }

    public boolean doesCustomerExist(Customer customer) {
        for (Customer cust : customerDataBase) {
            if (customer.equals(cust)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesManagementExist(Management management) {
        for (Management cust : managementDB) {
            if (management.equals(cust)) {
                return true;
            }
        }
        return false;
    }

    public void removeCustomer(Customer customer) {

    }

    public void addCustomer(Customer customer) {
        customerDataBase.add(customer);
    }
}
