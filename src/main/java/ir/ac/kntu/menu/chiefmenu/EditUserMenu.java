package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Management;

public class EditUserMenu extends MainMenu {
    private Database database;

    public EditUserMenu(Database database) {
        this.database = database;
    }

    private void printEditUserMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.edit chief");
        System.out.println(Constant.GREEN + "2.edit ");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void editUserMenu() {
        int number = 0;
        while (number != 99) {
            printEditUserMenu();
            number = getNumber();
            switch (number) {
                case 1 -> editChief();
                case 2 -> editManagement();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void editManagement() {
        String userName = getUserName();
        Management management = database.findManagement(userName);
        if (management == null) {
            System.out.println(Constant.RED + "there is no management with this userName");
            return;
        }
        management.setFirstName(getFirstName());
        management.setLastName(getLastName());
        management.setUserName(getUserName());
        management.setPassword(getPassword());
        System.out.println(Constant.PURPLE + "information change!");
    }

    private void editChief() {
        String userName = getUserName();
        Chief chief = database.findChief(userName);
        if (chief == null) {
            System.out.println(Constant.RED + "there is no chief with this userName");
            return;
        }
        chief.setFirstName(getFirstName());
        chief.setLastName(getLastName());
        chief.setUserName(getUserName());
        chief.setPassword(getPassword());
        System.out.println(Constant.PURPLE + "information change!");
    }
}
