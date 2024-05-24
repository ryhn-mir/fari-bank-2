package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class contactMenu {

    public void printContactMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.show contact");
        System.out.println(Constant.GREEN + "2.edit contact information");
        System.out.println(Constant.GREEN + "3.add contact");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void contactMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printContactMenu();
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
                    default:
                        throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void showContact(Customer customer) {
        int count = 1;
        for (Customer cust : customer.getContactDatabase().getContactList()) {
            System.out.println(count + "." + Constant.PURPLE + cust.getFirstName() + " " + Constant.PURPLE + cust.getLastName());
            count++;
        }
        try {
            int number = ScannerWrapper.getInstance().nextInt();
            if (number >= 1 && number <= count) {
                System.out.print(Constant.PURPLE + customer.getContactDatabase().getContactList().get(count - 1).getFirstName());
                System.out.print(" " + Constant.PURPLE + customer.getContactDatabase().getContactList().get(count - 1).getLastName());
                System.out.print(" " + Constant.PURPLE + customer.getContactDatabase().getContactList().get(count - 1).getCellNumber());


            } else {
                throw new RuntimeException("number out of range!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void editContact(Customer customer) {
        String cellPhone = ScannerWrapper.getInstance().next();
        Customer cust = customer.getContactDatabase().findCustomer(cellPhone);
        try {
            if (cust == null) {
                throw new RuntimeException("contact not found");
            } else {
                customer.getContactDatabase().removeContact(cust);
                String firstName = ScannerWrapper.getInstance().next();
                String lastName = ScannerWrapper.getInstance().next();
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
        String firstName = ScannerWrapper.getInstance().next();
        String lastName = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        if (customer.getContactDatabase().findCustomer(cellNumber) != null) {
            System.out.println(Constant.RED + "this contact is already exit!!");
            return;
        }
        customer.addContact(cellNumber, firstName, lastName);
    }
}
