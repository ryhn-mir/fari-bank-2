package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class SettingMenu extends MainMenu {

    public static void printSettingMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.edit your card password");
        System.out.println(Constant.GREEN + "2.edit your password");
        System.out.println(Constant.GREEN + "3.activation your contact");
        System.out.println(Constant.GREEN + "4.unActivation your contact");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void settingMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            try {
                printSettingMenu();
                number = getNumber();
                switch (number) {
                    case 1:
                        editCardPassword(customer);
                        break;
                    case 2:
                        editPersonPassword(customer);
                        break;
                    case 3:
                        activationContact(customer);
                        break;
                    case 4:
                        unActivationContact(customer);
                    case 99:
                        break;
                    default:
                        throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void editCardPassword(Customer customer) {
        String pass = getPassword();
        if (pass.matches("[0-9]{4}")) {
            customer.getAccount().getCard().setPassword(pass);
        } else {
            System.out.println(Constant.RED + "invalid password format!!");
        }
    }

    private void editPersonPassword(Customer customer) {
        System.out.println(Constant.PURPLE + "enter your new password");
        String pass = getPassword();
        customer.setPassword(pass);
    }

    private void activationContact(Customer customer) {
        customer.setContactIsOn(true);
    }

    public void unActivationContact(Customer customer) {
        customer.setContactIsOn(false);
    }
}
