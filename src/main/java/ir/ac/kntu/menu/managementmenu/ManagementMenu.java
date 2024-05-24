package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.RegistrationStatus;
import ir.ac.kntu.util.ScannerWrapper;

public class ManagementMenu {
    public void printManagementRegistration() {
        System.out.println(Constant.BLUE + "choose one of the following option :");
        System.out.println(Constant.GREEN + "1.log in");
        System.out.println(Constant.GREEN + "2.register");
        System.out.println(Constant.GREEN + "99.back");

    }

    public void managementRegistration(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printManagementRegistration();
                switch (number) {
                    case 1:
                        login(answerRequestDatabase );
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

    public void login(AnswerRequestDatabase answerRequestDatabase) {
        System.out.println(Constant.PURPLE + "enter your userName : ");
        String userName = ScannerWrapper.getInstance().next();
        System.out.println(Constant.PURPLE + "enter your password : ");
        String password = ScannerWrapper.getInstance().next();
        for (Management management : Database.getManagementDataBase()) {
            if (management.getUserName().equals(userName) && management.getPassword().equals(password)) {
                managementMenu(answerRequestDatabase);
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

    public void managementMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printManagementMenu();
                switch (number) {
                    case 1:
                        verify();
                        break;
                    case 2:
                        ShowRequestMenu showRequestMenu = new  ShowRequestMenu();
                        showRequestMenu.showRequestMenu(answerRequestDatabase);
                        break;
                    case 3:
                        UserAccessMenu userAccess = new UserAccessMenu();
                        userAccess.userAccessMenu();
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



    public void verify() {
        int count = 1;
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                System.out.println(count + "." + customer);
                count++;
            }
        }
        int number = ScannerWrapper.getInstance().nextInt();
        int counter = 0;
        System.out.println(Constant.BLUE + "wanna accept the customer ? 1(yes) 0(no)");
        int num = ScannerWrapper.getInstance().nextInt();
        if (num == 1) {
            for (Customer customer : Database.getCustomerDataBase()) {
                if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                    counter++;
                    if (counter == number) {
                        customer.setStatus(RegistrationStatus.ACCEPTED);
                    }
                }
            }
        } else if (num == 0) {
            for (Customer customer : Database.getCustomerDataBase()) {
                if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                    counter++;
                    if (counter == number) {
                        customer.setStatus(RegistrationStatus.REJECTED);
                        System.out.println(Constant.BLUE + "enter the reason of reject");
                        String request = ScannerWrapper.getInstance().nextLine();
                        Request newRequest = new Request("", RequestOption.REPORT, customer.getCellNumber());
                        newRequest.setAnswer(request);
                        customer.getRequestDatabase().addRequest(newRequest);
                    }
                }
            }
        } else {
            System.out.println(Constant.RED + "invalid input!");
        }
    }
}
