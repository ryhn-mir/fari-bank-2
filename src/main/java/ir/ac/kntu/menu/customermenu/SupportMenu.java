package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.Request;
import ir.ac.kntu.util.ScannerWrapper;

public class SupportMenu {
    private Database database;

    public SupportMenu(Database database) {
        this.database = database;
    }

    public void printSupportMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.add request");
        System.out.println(Constant.GREEN + "2.show request");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void supportMenu(Customer customer, AnswerRequestDatabase answerRequestDB) {
        int number = 0;
        while (number != 99) {
            printSupportMenu();
            number = ScannerWrapper.getInstance().nextInt();
            switch (number) {
                case 1:
                    RequestMenu requestMenu = new RequestMenu(database);
                    requestMenu.requestMenu(customer, answerRequestDB);
                    break;
                case 2:
                    showRequest(customer);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
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
