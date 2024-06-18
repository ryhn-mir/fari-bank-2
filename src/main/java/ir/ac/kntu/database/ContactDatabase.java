package ir.ac.kntu.database;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

import java.io.Serializable;
import java.util.*;

public class ContactDatabase implements Serializable {
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

    public void printContact() {
        int size = addToContactrMap().size();
        Map<Integer, Customer> map = addToContactrMap();
        int position = 1;
        int value = Constant.VALUE;
        if (value > size) {
            value = size;
        }
        String type;
        contactPrint(1, value + 1, map);
        do {
            type = ScannerWrapper.getInstance().nextLine();
            switch (type) {
                case "next" -> position = contactPositiveCheck(position, size, +value, map);
                case "back" -> position = contactNegativeCheck(position, size, -value, map);
                case "quit" -> {
                    return;
                }
                default -> System.out.println("invalid input");
            }

        } while (true);
    }

    private Map<Integer, Customer> addToContactrMap() {
        int count = 1;
        Map<Integer, Customer> showCustomer = new HashMap<>();
        for (Customer customer : contactList) {
            showCustomer.put(count, customer);
            count++;
        }
        return showCustomer;
    }

    private void contactPrint(int a, int b, Map<Integer, Customer> map) {
        for (int i = a; i < b; i++) {
            System.out.println(i + "." + map.get(i).getFirstName() + " " + map.get(i).getLastName() + " " + map.get(i).getCellNumber());
        }
    }

    private int contactPositiveCheck(int position, int size, int amount, Map<Integer, Customer> map) {
        if (position + amount > size) {
            position = size;
            contactPrint(size - amount, size, map);
        } else {
            if (position == 1) {
                position += amount;
            }
            if (position + amount > size) {
                position = size;
                contactPrint(size - amount, size, map);
                return position;
            }
            contactPrint(position, position + amount, map);
            position += amount;
        }
        return position;
    }

    private int contactNegativeCheck(int position, int size, int amount, Map<Integer, Customer> map) {
        if (position + amount < 0) {
            position = 0;
            contactPrint(1, -amount + 1, map);
        } else {
            if (position == size) {
                position += amount;
            }
            if (position + amount < 1) {
                position = 0;
                contactPrint(1, -amount + 1, map);
                return position;
            }
            contactPrint(position + amount, position + 1, map);
            position += amount;
        }
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContactDatabase that = (ContactDatabase) obj;
        return Objects.equals(contactList, that.contactList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactList);
    }

}
