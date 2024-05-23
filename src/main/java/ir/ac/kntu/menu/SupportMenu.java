package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.Request;
import ir.ac.kntu.util.ScannerWrapper;

public class SupportMenu {
    public void printSupportMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.add request");
        System.out.println(Constant.GREEN + "2.show request");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void supportMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printSupportMenu();
            switch (number) {
                case 1:
                    break;
                case 2:
                    showRequest(customer);
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }

    private void showRequest(Customer customer) {
        for (Request request : customer.getRequestDatabase().getRequestList()) {
            int count = 1;
            System.out.println(Constant.PURPLE + count + "." + request.getRequest());
            count++;
        }
        int number = ScannerWrapper.getInstance().nextInt();
        System.out.println(Constant.PURPLE + customer.getRequestDatabase().getRequestList().get(number - 1));
    }
}
