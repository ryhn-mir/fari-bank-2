package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.menu.CustomerMenu;
import ir.ac.kntu.menu.PrintMenu;
import ir.ac.kntu.util.ScannerWrapper;

public class Menu {
    public void printMainMenu() {
        System.out.println(Constant.PURPLE + "choose your character by the following number : ");
        System.out.println(Constant.YELLOW + "1.customer");
        System.out.println(Constant.YELLOW + "2.management");
        System.out.println(Constant.YELLOW + "99.exit");
    }
    public int mainMenu() {

        int number = ScannerWrapper.getInstance().nextInt();
        try {
            printMainMenu();
            switch (number) {
                case 1:
                    CustomerMenu customer = new CustomerMenu();
                    customer.login_registerMenu();
                    break;
                case 2:
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
