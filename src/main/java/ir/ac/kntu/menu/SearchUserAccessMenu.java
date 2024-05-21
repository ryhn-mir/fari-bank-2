package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class SearchUserAccessMenu {
    public void printSearchUserAccessMenu(){
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "search by firstName");
        System.out.println(Constant.GREEN + "search by lastName");
        System.out.println(Constant.GREEN + "search by cellNumber");
        System.out.println(Constant.GREEN + "search by firstName and lastName");
        System.out.println(Constant.GREEN + "search by firstName and cellNumber");
        System.out.println(Constant.GREEN + "search by lastName and cellNumber");
        System.out.println(Constant.GREEN + "search by firstName and lastName and cellNumber");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void searchUserAccessMenu() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printSearchUserAccessMenu();
            switch (number) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }

    private void searchByFirstName() {
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
