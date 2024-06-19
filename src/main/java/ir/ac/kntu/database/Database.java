package ir.ac.kntu.database;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.RegistrationStatus;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.HashMap;
import java.util.Map;
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

    public int showCustomer() {
        int count = 1;
        for (Customer customer : customerDataBase) {
            if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                System.out.println(count + "." + customer);
                count++;
            }
        }
        return count;
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
        int size = addToManagementMap().size();
        Map<Integer, Management> map = addToManagementMap();
        int position = 1;
        int value = Constant.VALUE;
        if (value > size) {
            value = size;
        }
        String type;
        this.managementPrint(1, value + 1, map);
        do {
            type = ScannerWrapper.getInstance().nextLine();
            switch (type) {
                case "next" -> position = this.managementPositiveCheck(position, size, +value, map);
                case "back" -> position = this.managementNegativeCheck(position, size, -value, map);
                case "quit" -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }

        } while (true);
    }

    private Map<Integer, Management> addToManagementMap() {
        int count = 1;
        Map<Integer, Management> showManagement = new HashMap<>();
        for (Management management : managementDB) {
            showManagement.put(count, management);
            count++;
        }
        return showManagement;
    }

    private void managementPrint(int num1, int num2, Map<Integer, Management> map) {
        for (int i = num1; i < num2; i++) {
            System.out.println(i + "." + map.get(i).getFirstName() + " " + map.get(i).getLastName());
        }
    }

    private int managementPositiveCheck(int position, int size, int amount, Map<Integer, Management> map) {
        if (position + amount > size) {
            position = size;
            this.managementPrint(size - amount + 1, size + 1, map);
        } else {
            if (position == 1) {
                position += amount;
            }
            if (position + amount > size) {
                position = size;
                this.managementPrint(size - amount, size, map);
                return position;
            }
            this.managementPrint(position, position + amount, map);
            position += amount;
        }
        return position;
    }

    private int managementNegativeCheck(int position, int size, int amount, Map<Integer, Management> map) {
        if (position + amount < 0) {
            position = 0;
            this.managementPrint(1, -amount + 1, map);
        } else {
            if (position == size) {
                position += amount;
            }
            if (position + amount < 1) {
                position = 0;
                this.managementPrint(1, -amount + 1, map);
                return position;
            }
            this.managementPrint(position + amount, position + 1, map);
            position += amount;
        }
        return position;
    }

    public void printCustomer() {
        int size = addToCustomerMap().size();
        Map<Integer, Customer> map = addToCustomerMap();
        int position = 1;
        int value = Constant.VALUE;
        if (value > size) {
            value = size;
        }
        String type;
        customerPrint(1, value + 1, map);
        do {
            type = ScannerWrapper.getInstance().nextLine();
            switch (type) {
                case "next" -> position = customerPositiveCheck(position, size, +value, map);
                case "back" -> position = customerNegativeCheck(position, size, -value, map);
                case "quit" -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }

        } while (true);
    }

    private Map<Integer, Customer> addToCustomerMap() {
        int count = 1;
        Map<Integer, Customer> showCustomer = new HashMap<>();
        for (Customer customer : customerDataBase) {
            showCustomer.put(count, customer);
            count++;
        }
        return showCustomer;
    }

    private void customerPrint(int num1, int num2, Map<Integer, Customer> map) {
        for (int i = num1; i < num2; i++) {
            System.out.println(i + "." + map.get(i).getFirstName() + " " + map.get(i).getLastName() + " " + map.get(i).getCellNumber());
        }
    }

    private int customerPositiveCheck(int position, int size, int amount, Map<Integer, Customer> map) {
        if (position + amount > size) {
            position = size;
            customerPrint(size - amount + 1, size + 1, map);
        } else {
            if (position == 1) {
                position += amount;
            }
            if (position + amount > size) {
                position = size;
                customerPrint(size - amount, size, map);
                return position;
            }
            customerPrint(position, position + amount, map);
            position += amount;
        }
        return position;
    }

    private int customerNegativeCheck(int position, int size, int amount, Map<Integer, Customer> map) {
        if (position + amount < 0) {
            position = 0;
            customerPrint(1, -amount + 1, map);
        } else {
            if (position == size) {
                position += amount;
            }
            if (position + amount < 1) {
                position = 0;
                customerPrint(1, -amount + 1, map);
                return position;
            }
            customerPrint(position + amount, position + 1, map);
            position += amount;
        }
        return position;
    }
}
