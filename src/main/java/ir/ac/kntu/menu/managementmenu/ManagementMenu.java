package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.request.Request;
import ir.ac.kntu.request.RequestOption;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.RegistrationStatus;

public class ManagementMenu extends MainMenu {
    private Database database;

    public ManagementMenu(Database database) {
        this.database = database;
    }

    public void printManagementRegistration() {
        System.out.println(Constant.BLUE + "choose one of the following option :");
        System.out.println(Constant.GREEN + "1.log in");
        System.out.println(Constant.GREEN + "2.register");
        System.out.println(Constant.GREEN + "99.back");

    }

    public void managementRegistration(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            try {
                printManagementRegistration();
                number = getNumber();
                switch (number) {
                    case 1:
                        login(answerDB);
                        break;
                    case 2:
                        System.out.println(Constant.PURPLE + "coming soon :)");
                        break;
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

    public void login(AnswerRequestDatabase answerDB) {
        String userName = getUserName();
        String password = getPassword();
        for (Management management : database.getManagementDB()) {
            if (management.getUserName().equals(userName) && management.getPassword().equals(password)) {
                managementMenu(answerDB);
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

    public void managementMenu(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            try {
                printManagementMenu();
                number = getNumber();
                switch (number) {
                    case 1:
                        verify();
                        break;
                    case 2:
                        ShowRequestMenu showRequestMenu = new ShowRequestMenu();
                        showRequestMenu.showRequestMenu(answerDB);
                        break;
                    case 3:
                        UserAccessMenu userAccess = new UserAccessMenu(database);
                        userAccess.userAccessMenu();
                        break;
                    case 99:
                        break;
                    default:
                        throw new RuntimeException("invalid number!!");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public void verify() {
        int count = 1;
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                System.out.println(count + "." + customer);
                count++;
            }
        }
        if (database.getCustomerDataBase().isEmpty() || count == 1) {
            System.out.println(Constant.RED + "there is no customer!!");
            return;
        }
        int number = getNumber();
        System.out.println(Constant.BLUE + "wanna accept the customer ? 1(yes) 0(no)");
        int num = getNumber();
        if (num == 1) {
            accept(number);
        } else if (num == 0) {
            reject(number);
        } else {
            System.out.println(Constant.RED + "invalid input!");
        }
    }

    private void accept(int number) {
        int counter = 0;
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                counter++;
                if (counter == number) {
                    customer.setStatus(RegistrationStatus.ACCEPTED);
                }
            }
        }
    }

    private void reject(int number) {
        int counter = 0;
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                counter++;
                if (counter == number) {
                    customer.setStatus(RegistrationStatus.REJECTED);
                    System.out.println(Constant.BLUE + "enter the reason of reject");
                    String request = getRequest();
                    Request newRequest = new Request("", RequestOption.REPORT, customer.getCellNumber());
                    newRequest.setAnswer(request);
                    customer.getRequestDatabase().addRequest(newRequest);
                }
            }
        }
    }
}
