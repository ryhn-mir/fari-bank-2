package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class UserAccessMenu {
    public void printUserAccessMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.showUser");
        System.out.println(Constant.GREEN + "2.searchUser");
        System.err.println(Constant.GREEN + "99.back");
    }

    public void userAccessMenu() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            switch (number) {
                case 1:
                    showUserDetails();
                    break;
                case 2:
                    SearchUserAccessMenu searchUserAccess = new SearchUserAccessMenu();
                    searchUserAccess.searchUserAccessMenu();
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
        }
    }

    public void showUserDetails() {
        int count = 1;
        for (Customer customer : Database.getCustomerDataBase()) {
            System.out.println(Constant.PURPLE + count + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getCellNumber());
            count++;
        }
    }
}
