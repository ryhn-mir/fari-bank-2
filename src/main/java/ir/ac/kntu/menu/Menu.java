package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.*;
import ir.ac.kntu.menu.chiefmenu.ChiefMenu;
import ir.ac.kntu.menu.customermenu.CustomerMenu;
import ir.ac.kntu.menu.managementmenu.ManagementMenu;

public class Menu extends MainMenu {

    private AnswerRequestDatabase answerDB;
    private Database database;
    private SimCardDataBase simCardDataBase;
    private BankDataBase bankDataBase;
    private PayaDataBase payaDataBase;

    public void printMainMenu() {
        System.out.println(Constant.PURPLE + "choose your character by the following number : ");
        System.out.println(Constant.YELLOW + "1.customer");
        System.out.println(Constant.YELLOW + "2.management");
        System.out.println(Constant.YELLOW + "3.chief");
        System.out.println(Constant.YELLOW + "99.exit");
    }

    public Menu(AnswerRequestDatabase answerDB, Database database, SimCardDataBase simCardDataBase, BankDataBase bankDataBase, PayaDataBase payaDataBase) {
        this.answerDB = answerDB;
        this.database = database;
        this.simCardDataBase = simCardDataBase;
        this.bankDataBase = bankDataBase;
        this.payaDataBase = payaDataBase;
    }

    public int mainMenu() {
        int number = 0;
        try {
            printMainMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    CustomerMenu customerMenu = new CustomerMenu(database,simCardDataBase, bankDataBase, payaDataBase, answerDB);
                    customerMenu.userRegistrationMenu(answerDB);
                    break;
                case 2:
                    ManagementMenu managementMenu = new ManagementMenu(database);
                    managementMenu.managementRegistration(answerDB);
                    break;
                case 3:
                    ChiefMenu chiefMenu = new ChiefMenu(database, payaDataBase);
                    chiefMenu.loginMenu();
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
