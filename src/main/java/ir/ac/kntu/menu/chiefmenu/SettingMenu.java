package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.menu.MainMenu;

public class SettingMenu extends MainMenu {
    private void printSettingMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.change sim wage");
        System.out.println(Constant.GREEN + "2.change fari to fari wage");
        System.out.println(Constant.GREEN + "3.change fari to another card wage");
        System.out.println(Constant.GREEN + "4.change fari pole wage");
        System.out.println(Constant.GREEN + "5. change fari paya wage");
        System.out.println(Constant.GREEN + "6. change profit");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void settingMenu() {
        int number = 0;
        while (number != 99) {
            printSettingMenu();
            number = getNumber();
            switch (number) {
                case 1 -> simWage();
                case 2 -> fariToFari();
                case 3 -> fariToAnother();
                case 4 -> fariPole();
                case 5 -> fariPaya();
                case 6 -> profit();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void simWage() {
        long wage = getWage();
        Constant.setSim(wage);
    }

    private void fariToFari() {
        long wage = getWage();
        Constant.setFari(wage);
    }

    private void fariToAnother() {
        long wage = getWage();
        Constant.setOtherCart(wage);

    }

    private void fariPole() {
        long wage = getWage();
        Constant.setFariPole(wage);

    }

    private void fariPaya() {
        long wage = getWage();
        Constant.setFariPaya(wage);

    }

    private void profit() {
        long wage = getWage();
        Constant.setProfit(wage);

    }
}
