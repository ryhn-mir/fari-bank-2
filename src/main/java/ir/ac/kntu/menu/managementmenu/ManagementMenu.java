package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.*;
import ir.ac.kntu.request.Request;
import ir.ac.kntu.request.RequestOption;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;

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
                if (management.getUserState() == UserState.UNBLOCKED) {
                    managementMenu(answerDB, management);
                    return;
                } else {
                    System.out.println(Constant.RED + "you are blocked!");
                    return;
                }

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

    public void managementMenu(AnswerRequestDatabase answerDB, Management management) {
        int number = 0;
        while (number != 99) {
            try {
                printManagementMenu();
                number = getNumber();
                switch (number) {
                    case 1:
                        checkVerify(management);
                        break;
                    case 2:
                        checkRequest(management, answerDB);
                        break;
                    case 3:
                        checkUserAccess(management);
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
        database.printCustomer();
        if (database.getCustomerDataBase().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer!!");
            return;
        }
        int number = getNumber();
        if (!(0 < number && number < database.getCustomerDataBase().size())) {
            System.out.println(Constant.RED + "number out of the range");
            return;
        }
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

    private void checkVerify(Management management) {
        Permission permission = management.getPermission();
        if (!permission.isVerify()) {
            System.out.println(Constant.RED + "you do not have access");
            return;
        }
        verify();
    }

    private void checkRequest(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isRequest()) {
            System.out.println(Constant.RED + "you do not have access");
            return;
        }
        ShowRequestMenu showRequestMenu = new ShowRequestMenu();
        showRequestMenu.showRequestMenu(answerDB, management);
    }

    private void checkUserAccess(Management management) {
        Permission permission = management.getPermission();
        if (!permission.isUserAccess()) {
            System.out.println(Constant.RED + "you do not have access");
            return;
        }
        UserAccessMenu userAccess = new UserAccessMenu(database);
        userAccess.userAccessMenu();
    }
}
