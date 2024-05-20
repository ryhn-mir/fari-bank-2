package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class SettingMenu {
    public void settingMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                PrintMenu.printSettingMenu();
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
