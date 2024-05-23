package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class SettingMenu {

    public static void printSettingMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.edit your card password");
        System.out.println(Constant.GREEN + "2.edit your password");
        System.out.println(Constant.GREEN + "3.activation your contact");
        System.out.println(Constant.GREEN + "4.unActivation your contact");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void settingMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printSettingMenu();
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
                    default:
                        throw new RuntimeException("invalid number");
                }
                number = ScannerWrapper.getInstance().nextInt();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    private void editCardPassword(Customer customer){
        //ask
    }

    private void editPersonPassword(Customer customer) {
        System.out.println(Constant.PURPLE + "enter your new password");
        String pass = ScannerWrapper.getInstance().next();
        customer.setPassword(pass);
    }

    private void activationContact(Customer customer) {
        customer.setContactIsOn(true);
    }

    public void unActivationContact(Customer customer) {
        customer.setContactIsOn(false);
    }
}
