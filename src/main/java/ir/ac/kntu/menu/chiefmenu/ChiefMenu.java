package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.PayaDataBase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.UserState;

public class ChiefMenu extends MainMenu {
    private Database database;
    private PayaDataBase payaDataBase;

    public ChiefMenu(Database database, PayaDataBase payaDataBase) {
        this.database = database;
        this.payaDataBase = payaDataBase;
    }

    private void printLoginMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.login");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void loginMenu() {
        int number = 0;
        while (number != 99) {
            printLoginMenu();
            number = getNumber();
            switch (number) {
                case 1 -> login();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }
    private void login() {
        String userName = getUserName();
        String password = getPassword();
        for (Chief chief : database.getChiefDB()) {
            if (chief.getUserName().equals(userName) && chief.getPassword().equals(password)) {
                if (chief.getUserState() == UserState.BLOCKED) {
                    System.out.println(Constant.RED + "you are blocked");
                    return;
                }
                chiefMenu(chief);
                return;
            }
        }
        System.out.println(Constant.RED + "chief not found!");
    }
    private void printChiefMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.setting");
        System.out.println(Constant.GREEN + "2.manage user");
        System.out.println(Constant.GREEN + "3.auto transaction");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void chiefMenu(Chief chief) {
        int number = 0;
        while (number != 99) {
            try {
                printChiefMenu();
                number = getNumber();
                switch (number) {
                    case 1 -> basicSetting();
                    case 2 -> manageUser(chief);
                    case 3 -> autoTransaction();
                    case 99 -> {
                        return;
                    }
                    default -> throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void autoTransaction() {
        AutoTransactionMenu autoTransactionMenu = new AutoTransactionMenu(database, payaDataBase);
        autoTransactionMenu.autoTransactionMenu();
    }


    private void basicSetting() {
        SettingMenu settingMenu = new SettingMenu();
        settingMenu.settingMenu();
    }


    private void manageUser(Chief chief) {
        ManageUserMenu manageUserMenu = new ManageUserMenu(database);
        manageUserMenu.manageUserMenu(chief);
    }
}
