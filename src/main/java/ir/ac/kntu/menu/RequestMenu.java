package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.RequestDatabase;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;
import ir.ac.kntu.util.ScannerWrapper;

public class RequestMenu {
    public void printRequestMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.contact");
        System.out.println(Constant.GREEN + "2.setting");
        System.out.println(Constant.GREEN + "3.transfer");
        System.out.println(Constant.GREEN + "4.report");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void requestMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            switch (number) {
                case 1:

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }
    private void contact(Customer customer){
        System.out.println(Constant.PURPLE + "enter request : ");
        String request = ScannerWrapper.getInstance().next();
        Request request1 = new Request(request, RequestOption.CONTACT, customer.getCellNumber());

    }
    private void setting() {

    }
}
