package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class SearchUserAccessMenu {
    public void printSearchUserAccessMenu() {
        System.out.println(Constant.BLUE + "1choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.search by firstName");
        System.out.println(Constant.GREEN + "2.search by lastName");
        System.out.println(Constant.GREEN + "3.search by cellNumber");
        System.out.println(Constant.GREEN + "4.search by firstName and lastName");
        System.out.println(Constant.GREEN + "5.search by firstName and cellNumber");
        System.out.println(Constant.GREEN + "6.search by lastName and cellNumber");
        System.out.println(Constant.GREEN + "7.search by firstName and lastName and cellNumber");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void searchUserAccessMenu() {
        int number = 0;
        while (number != 99) {
            printSearchUserAccessMenu();
            number = ScannerWrapper.getInstance().nextInt();
            switch (number) {
                case 1:
                    searchByFirstName();
                    break;
                case 2:
                    searchByLastName();
                    break;
                case 3:
                    searchByCellNumber();
                    break;
                case 4:
                    searchByFirstNameAndLastName();
                    break;
                case 5:
                    searchByFirstNameAndCellNumber();
                    break;
                case 6:
                    searchByLastNameAndCellNumber();
                    break;
                case 7:
                    searchByFirstNameAndLastNameAndCellNumber();
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    private void searchByFirstName() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByLastName() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String lastName = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getLastName().equals(lastName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByCellNumber() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByFirstNameAndLastName() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = ScannerWrapper.getInstance().next();
        String lastName = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }

    }

    private void searchByFirstNameAndCellNumber() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByLastNameAndCellNumber() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String lastName = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getLastName().equals(lastName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }


    }

    private void searchByFirstNameAndLastNameAndCellNumber() {
        if (Database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = ScannerWrapper.getInstance().next();
        String lastName = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }

    }
}
