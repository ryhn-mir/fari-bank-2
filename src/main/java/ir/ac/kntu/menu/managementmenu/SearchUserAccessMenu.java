package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

public class SearchUserAccessMenu extends MainMenu {
    private Database database;

    public SearchUserAccessMenu(Database database) {
        this.database = database;
    }

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
            number = getNumber();
            switch (number) {
                case 1 -> searchByFirstName();
                case 2 -> searchByLastName();
                case 3 -> searchByCellNumber();
                case 4 -> searchByFirstNameAndLastName();
                case 5 -> searchByFirstNameAndCellNumber();
                case 6 -> searchByLastNameAndCellNumber();
                case 7 -> searchByFirstNameAndLastNameAndCellNumber();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    private void searchByFirstName() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = getFirstName();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByLastName() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String lastName = getLastName();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getLastName().equals(lastName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByCellNumber() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByFirstNameAndLastName() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = getFirstName();
        String lastName = getLastName();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }

    }

    private void searchByFirstNameAndCellNumber() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = getFirstName();
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByLastNameAndCellNumber() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String lastName = getLastName();
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getLastName().equals(lastName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }
    }

    private void searchByFirstNameAndLastNameAndCellNumber() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        int count = 1;
        String firstName = getFirstName();
        String lastName = getLastName();
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName) && customer.getCellNumber().equals(cellNumber)) {
                System.out.println(count + "." + customer.toString() + " " + customer.getAccount().getTransactionDb());
                count++;
            }
        }

    }
}
