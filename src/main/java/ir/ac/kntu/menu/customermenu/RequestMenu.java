package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;

public class RequestMenu extends MainMenu {

    public void printRequestMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.contact");
        System.out.println(Constant.GREEN + "2.setting");
        System.out.println(Constant.GREEN + "3.transfer");
        System.out.println(Constant.GREEN + "4.report");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void requestMenu(Customer customer, AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            printRequestMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    contact(customer, answerDB);
                    break;
                case 2:
                    setting(customer, answerDB);
                    break;
                case 3:
                    transfer(customer, answerDB);
                    break;
                case 4:
                    report(customer, answerDB);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void contact(Customer customer, AnswerRequestDatabase answerDB) {
        String request = getRequest();
        Request request1 = new Request(request, RequestOption.CONTACT, customer.getCellNumber());
        customer.getRequestDatabase().addRequest(request1);
        answerDB.add(request1);
    }

    private void transfer(Customer customer, AnswerRequestDatabase answerDB) {
        String request = getRequest();
        Request request1 = new Request(request, RequestOption.TRANSFER, customer.getCellNumber());
        customer.getRequestDatabase().addRequest(request1);
        answerDB.add(request1);
    }

    private void report(Customer customer, AnswerRequestDatabase answerDB) {
        String request = getRequest();
        Request request1 = new Request(request, RequestOption.REPORT, customer.getCellNumber());
        customer.getRequestDatabase().addRequest(request1);
        answerDB.add(request1);
    }

    private void setting(Customer customer, AnswerRequestDatabase answerDB) {
        String request = getRequest();
        Request request1 = new Request(request, RequestOption.SETTING, customer.getCellNumber());
        customer.getRequestDatabase().addRequest(request1);
        answerDB.add(request1);
    }
}
