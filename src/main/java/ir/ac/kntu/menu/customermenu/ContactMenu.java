package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

public class ContactMenu extends MainMenu {
    private Database database;

    public ContactMenu(Database database) {
        this.database = database;
    }

    public void printContactMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.show contact");
        System.out.println(Constant.GREEN + "2.edit contact information");
        System.out.println(Constant.GREEN + "3.add contact");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void contactMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            printContactMenu();
            number = getNumber();
            try {
                switch (number) {
                    case 1:
                        showContact(customer);
                        break;
                    case 2:
                        editContact(customer);
                        break;
                    case 3:
                        addContact(customer);
                        break;
                    case 99:
                        break;
                    default:
                        throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void showContact(Customer customer) {
        if (customer.getContactDatabase().getContactList().isEmpty()) {
            System.out.println(Constant.RED + "there is no contact to show!");
            return;
        }
        int count = 1;
        for (Customer cust : customer.getContactDatabase().getContactList()) {
            System.out.println(count + "." + Constant.PURPLE + cust.getFirstName() + " " + Constant.PURPLE + cust.getLastName());
            count++;
        }
        try {
            int number = getNumber();
            if (number >= 1 && number <= count) {
                System.out.print(Constant.PURPLE + customer.getContactDatabase().getContactList().get(number - 1).getFirstName());
                System.out.print(" " + Constant.PURPLE + customer.getContactDatabase().getContactList().get(number - 1).getLastName());
                System.out.println(" " + Constant.PURPLE + customer.getContactDatabase().getContactList().get(number - 1).getCellNumber());


            } else {
                throw new RuntimeException("number out of range!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void editContact(Customer customer) {
        if (customer.getContactDatabase().getContactList().isEmpty()) {
            System.out.println(Constant.RED + "there is no contact to show!");
            return;
        }
        String cellPhone = getCellNumber();
        Customer cust = customer.getContactDatabase().findCustomer(cellPhone, database);
        try {
            if (cust == null) {
                throw new RuntimeException("contact not found");
            } else {
                customer.getContactDatabase().removeContact(cust);
                String firstName = getFirstName();
                String lastName = getLastName();
                Customer customer1 = new Customer(cust, firstName, lastName);
                customer.getContactDatabase().addContact(customer1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void printInformationOfContact() {
        System.out.println(Constant.PURPLE + "Enter your contact firstName lastName and cellNumber");
    }

    public void addContact(Customer customer) {
        printInformationOfContact();
        String firstName = getFirstName();
        String lastName = getLastName();
        String cellNumber = getCellNumber();
        if (customer.getContactDatabase().findCustomer(cellNumber, database) == null) {
            System.out.println(Constant.RED + "person with that cellNumber does not exist!!");
            return;
        }
        for (Customer customer1 : customer.getContactDatabase().getContactList()) {
            if (customer1.getCellNumber().equals(cellNumber)) {
                System.out.println(Constant.RED + "this person is your contact !!");
                return;
            }
        }
        customer.addContact(cellNumber, firstName, lastName, database);
    }
}
