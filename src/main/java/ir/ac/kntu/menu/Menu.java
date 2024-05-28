package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.customermenu.CustomerMenu;
import ir.ac.kntu.menu.managementmenu.ManagementMenu;

public class Menu extends MainMenu {

    private AnswerRequestDatabase answerDB;
    private Database database;

    public void printMainMenu() {
        System.out.println(Constant.PURPLE + "choose your character by the following number : ");
        System.out.println(Constant.YELLOW + "1.customer");
        System.out.println(Constant.YELLOW + "2.management");
        System.out.println(Constant.YELLOW + "99.exit");
    }

    public Menu(AnswerRequestDatabase answerDB, Database database) {
        this.answerDB = answerDB;
        this.database = database;
    }

    public int mainMenu() {

        int number = 0;
        try {
            printMainMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    CustomerMenu customerMenu = new CustomerMenu(database);
                    customerMenu.userRegistrationMenu(answerDB);
                    break;
                case 2:
                    ManagementMenu managementMenu = new ManagementMenu(database);
                    managementMenu.managementRegistration(answerDB);
                    break;
                case 99:
                    return 99;
                default:
                    throw new RuntimeException("invalid number!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return number;
    }


}
