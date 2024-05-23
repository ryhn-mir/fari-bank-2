package ir.ac.kntu.database;

import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private static Set<Management> managementDataBase;
    private static Set<Customer> customerDataBase;

    public Database() {
        customerDataBase = new HashSet<>();
        managementDataBase = new HashSet<>();
        managementDataBase.add(new Management("reyhane", "arabshahi", "Rr@1384", "reyhane123"));
        managementDataBase.add(new Management("sara", "ahmadi", "Ss@1383", "sara123"));
    }

    public static Set<Management> getManagementDataBase() {
        return managementDataBase;
    }

    public static Set<Customer> getCustomerDataBase() {
        return customerDataBase;
    }

    public static void setManagementDataBase(Set<Management> managementDataBase) {
        Database.managementDataBase = managementDataBase;
    }

    public static void setCustomerDataBase(Set<Customer> customerDataBase) {
        Database.customerDataBase = customerDataBase;
    }

    public static Customer findReceiver(String accountNumber) {
        Set<Customer> customersDB = getCustomerDataBase();
        for (Customer customer : customersDB) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    public Management findManagement(String userName) {
        Set<Management> managementDB = getManagementDataBase();
        for (Management management : managementDB) {
            if (management.getUserName().equals(userName)) {
                return management;
            }
        }
        return null;
    }

    public boolean DoesCustomerExist(Customer customer) {
        for (Customer cust : customerDataBase) {
            if (customer.equals(cust)) {
                return true;
            }
        }
        return false;
    }

    public boolean DoesManagementExist(Management management) {
        for (Management cust : managementDataBase) {
            if (management.equals(cust)) {
                return true;
            }
        }
        return false;
    }

    public void removeCustomer(Customer customer) {

    }

    public static void addCustomer(Customer customer) {
        customerDataBase.add(customer);
    }
}
