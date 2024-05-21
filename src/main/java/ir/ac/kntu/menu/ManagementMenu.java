package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.Person.Management;
import ir.ac.kntu.util.ScannerWrapper;

public class ManagementMenu {
    public void printManagementRegistration() {
        System.out.println(Constant.BLUE + "choose one of the following option :");
        System.out.println(Constant.GREEN + "1.log in");
        System.out.println(Constant.GREEN + "2.register");
        System.out.println(Constant.GREEN + "99.back");

    }
    public void managementRegistration() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printManagementRegistration();
                switch (number) {
                    case 1:
                        break;
                    case 2:
                        System.out.println(Constant.PURPLE + "coming soon :)");
                        break;
                    default:
                       throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }
    public void login() {
        System.out.println(Constant.PURPLE + "enter your userName : ");
        String userName = ScannerWrapper.getInstance().next();
        System.out.println(Constant.PURPLE + "enter your password : ");
        String password = ScannerWrapper.getInstance().next();
        for (Management management : Database.getManagementDataBase()) {
            if (management.getUserName().equals(userName) && management.getPassword().equals(password)) {

                return;
            }
        }
        System.out.println(Constant.RED + "invalid information!!");
    }

    public void printManagementMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.verify");
        System.out.println(Constant.GREEN + "2.request");
        System.out.println(Constant.GREEN + "3.user access");
        System.out.println(Constant.GREEN + "99.back");

    }
    public void managementMenu() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printManagementMenu();
                switch (number) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        throw new RuntimeException("invalid number!!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }

}
