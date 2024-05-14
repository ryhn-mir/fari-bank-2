package ir.ac.kntu.DataBase;

import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.Person.Management;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private static Set<Management> managementDataBase;
    private static Set<Customer> customerDataBase;

    public Database() {
        customerDataBase = new HashSet<>();
        managementDataBase = new HashSet<>();
        managementDataBase.add(new Management("reyhane", "arabshahi", "Rr@1384", "reyhon123"));
        managementDataBase.add(new Management("sara", "ahmadi", "Ss@1383", "sara123"));
    }

    public static Set<Management> getManagementDataBase() {
        return managementDataBase;
    }

    public static Set<Customer> getCustomerDataBase() {
        return customerDataBase;
    }

    public static Customer find_transferToReceiver (String accountNumber) {
        Set<Customer> customersDB = getCustomerDataBase();
        for (Customer customer : customersDB) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }
}
