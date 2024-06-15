package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;

public class UserRoleMenu extends MainMenu {
    private Database database;

    public UserRoleMenu(Database database) {
        this.database = database;
    }
    
    private void printRoleUserMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.management");
        System.out.println(Constant.GREEN + "2.chief");
        System.out.println(Constant.GREEN + "3.customer");
        System.out.println(Constant.GREEN + "99.back");
    }
    
    public void roleUserMenu() {
        int number = 0;
        while (number != 99) {
            printRoleUserMenu();
            number = getNumber();
            switch (number) {
                case 1 -> management();
                case 2 -> chief();
                case 3 -> customer();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    private void management() {
        int count = 1;
        if (database.getManagementDB().isEmpty()) {
            System.out.println(Constant.RED + "there is no management to show");
            return;
        }
        for (Management management : database.getManagementDB()) {
            System.out.println(Constant.PURPLE + count + "." + management.getFirstName() + " " + management.getLastName());
            count++;
        }
        int number = getNumber();
        int counter = 0;
        if (1 <= number && number < count) {
            for (Management management : database.getManagementDB()) {
                counter++;
                if (counter == number) {
                    System.out.println(Constant.PURPLE + management + " " + management.getClass());
                }
            }
        }
    }

    private void chief() {
        int count = 1;
        if (database.getChiefDB().isEmpty()) {
            System.out.println(Constant.RED + "there is no chief to show");
            return;
        }
        for (Chief chief : database.getChiefDB()) {
            System.out.println(Constant.PURPLE + count + "." + chief.getFirstName() + " " + chief.getLastName());
            count++;
        }
        int number = getNumber();
        int counter = 0;
        if (1 <= number && number < count) {
            for (Chief chief : database.getChiefDB()) {
                counter++;
                if (counter == number) {
                    System.out.println(Constant.PURPLE + chief + " " + chief.getClass());
                }
            }
        }
    }

    private void customer() {
        int count = 1;
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer to show");
            return;
        }
        for (Customer customer : database.getCustomerDataBase()) {
            System.out.println(Constant.PURPLE + count + "." + customer.getFirstName() + " " + customer.getLastName());
            count++;
        }
        int number = getNumber();
        int counter = 0;
        if (1 <= number && number < count) {
            for (Customer customer : database.getCustomerDataBase()) {
                counter++;
                if (counter == number) {
                    System.out.println(Constant.PURPLE + customer + " " + customer.getClass());
                }
            }
        }
    }
}
