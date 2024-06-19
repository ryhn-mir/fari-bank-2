package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;

public class UserAccessMenu extends MainMenu {
    private Database database;

    public UserAccessMenu(Database database) {
        this.database = database;
    }

    public void printUserAccessMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.showUser");
        System.out.println(Constant.GREEN + "2.searchUser");
        System.err.println(Constant.GREEN + "99.back");
    }

    public void userAccessMenu() {
        int number = 0;
        while (number != 99) {
            printUserAccessMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    showUserDetails();
                    break;
                case 2:
                    SearchUserAccessMenu searchUserAccess = new SearchUserAccessMenu(database);
                    searchUserAccess.searchUserAccessMenu();
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    public void showUserDetails() {
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer");
            return;
        }
        database.printCustomer();
    }
}
