package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Management;

public class AddUserMenu extends MainMenu {
    private Database database;

    public AddUserMenu(Database database) {
        this.database = database;
    }

    private void printAddUserMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.add management");
        System.out.println(Constant.GREEN + "2.add chief");
        System.out.println(Constant.GREEN + "99.back");

    }

    public void addUserMenu(Chief chief) {
        int number = 0;
        while (number != 99) {
            printAddUserMenu();
            number = getNumber();
            switch (number) {
                case 1 -> addManagement();
                case 2 -> addChief(chief);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    private void addChief(Chief headChief) {
        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String userName = getUserName();
        if (database.findChief(userName) != null) {
            System.out.println(Constant.RED + "this chief is already exist!");
            return;
        }
        Chief chief = new Chief(firstName, lastName, password, userName, headChief.getPosition() + 1);
        database.addChief(chief);
    }

    private void addManagement() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String password = getPassword();
        String userName = getUserName();
        if (database.findManagement(userName) != null) {
            System.out.println(Constant.RED + "this management is already exist!");
            return;
        }
        Management management = new Management(firstName, lastName, password, userName);
        database.addManagement(management);
    }
}
