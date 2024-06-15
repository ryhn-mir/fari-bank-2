package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;

public class SearchUserMenu extends MainMenu {
    private Database database;

    public SearchUserMenu(Database database) {
        this.database = database;
    }

    private void printSearchUserMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.by firstName");
        System.out.println(Constant.GREEN + "2.by lastName");
        System.out.println(Constant.GREEN + "3.by cellNumber");
        System.out.println(Constant.GREEN + "4.by user role");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void searchUserMenu() {
        int number = 0;
        while (number != 99) {
            printSearchUserMenu();
            number = getNumber();
            switch (number) {
                case 1 -> searchByFirstName();
                case 2 -> searchByLastName();
                case 3 -> searchByCellNumber();
                case 4 -> searchByRole();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    private void searchByFirstName() {
        boolean customerExist = false, managementExist = false, chiefExist = false;
        String firstName = getFirstName();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getFirstName().equals(firstName)) {
                System.out.println(Constant.PURPLE + customer + " " + customer.getClass());
                customerExist = true;
            }
        }
        for (Management management : database.getManagementDB()) {
            if (management.getFirstName().equals(firstName)) {
                System.out.println(Constant.PURPLE + management + " " + management.getClass());
                managementExist = true;
            }
        }
        for (Chief chief : database.getChiefDB()) {
            if (chief.getFirstName().equals(firstName)) {
                System.out.println(Constant.PURPLE + chief + " " + chief.getClass());
                chiefExist = true;
            }
        }
        if (!chiefExist && ! managementExist && !customerExist) {
            System.out.println(Constant.RED + "user with this firstName not found");
        }
    }

    private void searchByLastName() {
        boolean customerExist = false, chiefExist = false, managementExist = false;
        String lastName = getLastName();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getLastName().equals(lastName)) {
                System.out.println(Constant.PURPLE + customer + " " + customer.getClass());
                customerExist = true;
            }
        }
        for (Management management : database.getManagementDB()) {
            if (management.getLastName().equals(lastName)) {
                System.out.println(Constant.PURPLE + management + " " + management.getClass());
                managementExist = true;
            }
        }
        for (Chief chief : database.getChiefDB()) {
            if (chief.getLastName().equals(lastName)) {
                System.out.println(Constant.PURPLE + chief + " " + chief.getClass());
                chiefExist = true;
            }
        }
        if (!chiefExist && !customerExist && !managementExist) {
            System.out.println(Constant.RED + "user with this lastName not found");
        }
    }

    private void searchByCellNumber() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer to show");
            return;
        }
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber)) {
                System.out.println(Constant.PURPLE + customer);
                return;
            }
        }
        System.out.println(Constant.RED + "user with this cellNumber not found");
    }

    private void searchByRole() {
        UserRoleMenu userRoleMenu = new UserRoleMenu(database);
        userRoleMenu.roleUserMenu();
    }
}
