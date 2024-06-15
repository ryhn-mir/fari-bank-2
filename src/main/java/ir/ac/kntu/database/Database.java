package ir.ac.kntu.database;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;

import java.util.HashSet;
import java.util.Set;

public class Database {
    private Set<Management> managementDB;
    private Set<Customer> customerDataBase;
    private Set<Chief> chiefDB;

    public Database(Set<Customer> customerDataBase, Set<Management> managementDB, Set<Chief> chiefDB) {
        this.customerDataBase = customerDataBase;
        this.managementDB = managementDB;
        this.chiefDB = chiefDB;
        chiefDB.add(new Chief("reyhane", "arabshahi", "Rr@138406", "reyhane123", 1));
        chiefDB.add(new Chief("a", "a", "Aa@138406", "a", 2));
        managementDB.add(new Management("reyhane", "arabshahi", "Rr@138406", "reyhane123"));
        managementDB.add(new Management("sara", "ahmadi", "Ss@138310", "sara123"));
    }

    public Set<Management> getManagementDB() {
        return managementDB;
    }

    public Set<Customer> getCustomerDataBase() {
        return customerDataBase;
    }

    public Set<Chief> getChiefDB() {
        return chiefDB;
    }

    public void setChiefDB(Set<Chief> chiefDB) {
        this.chiefDB = chiefDB;
    }

    public void setManagementDB(Set<Management> managementDB) {
        this.managementDB = managementDB;
    }

    public void setCustomerDataBase(Set<Customer> customerDataBase) {
        this.customerDataBase = customerDataBase;
    }

    public Customer findReceiver(String accountNumber) {
        for (Customer customer : customerDataBase) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomer(String cellNumber) {
        for (Customer customer : customerDataBase) {
            if (customer.getCellPhone().getCellNo().equals(cellNumber)) {
                return customer;
            }
        }
        return null;
    }

    public Chief findChief(String userName) {
        for (Chief chief : chiefDB) {
            if (chief.getUserName().equals(userName)) {
                return chief;
            }
        }
        return null;
    }

    public Management findManagement(String userName) {
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

    public void addCustomer(Customer customer) {
        customerDataBase.add(customer);
    }

    public void addManagement(Management management) {
        managementDB.add(management);
    }

    public void addChief(Chief chief) {
        chiefDB.add(chief);
    }

    public String getAccountNumberByCardNo(String cardNumber) {
        for (Customer customer : customerDataBase) {
            if (customer.getAccount().getCard().getCardNumber().equals(cardNumber)) {
                return customer.getAccount().getAccountNumber();
            }
        }
        return null;
    }

    public void printManagement() {
        int count = 1;
        for (Management management : managementDB) {
            System.out.println(Constant.PURPLE + count + "." + management.getFirstName() + " " + management.getLastName());
            count++;
        }
    }
}
