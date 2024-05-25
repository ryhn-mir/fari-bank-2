package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;
import ir.ac.kntu.util.ScannerWrapper;

public class CustomerMenu extends MainMenu{

    private Database database;

    public CustomerMenu(Database database) {
        this.database = database;
    }

    public void printUserRegistrationCustomerMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.log in");
        System.out.println(Constant.GREEN + "2.register");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void UserRegistrationMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            try {
                printUserRegistrationCustomerMenu();
                number = getNumber();
                switch (number) {
                    case 1: //login
                        login(answerRequestDatabase);
                        break;
                    case 2: //register
                        register();
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

    public void printCustomerMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.transfer money");        // transfer money recent account by contact or recent account or account number
        System.out.println(Constant.GREEN + "2.manage your account");   // increase credit or show account balance or show the list of transactions
        System.out.println(Constant.GREEN + "3.go to contact menu");    // show contact list , edit contact info, add contact
        System.out.println(Constant.GREEN + "4.support problems");
        System.out.println(Constant.GREEN + "5.go to setting menu");    // edit card pass, edit username, activation contact
        System.out.println(Constant.GREEN + "99.back");

    }

    public void customerMenu(Customer customer, AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            try {
                printCustomerMenu();
                number = getNumber();
                switch (number) {
                    case 1:
                        TransferMoneyMenu transferMoneyMenu = new TransferMoneyMenu(database);
                        transferMoneyMenu.transferMoney(customer);
                        break;
                    case 2:
                        ManageAccountMenu manageAccountMenu = new ManageAccountMenu(database);
                        manageAccountMenu.manageAccountMenu(customer);
                        break;
                    case 3:
                        contactMenu contactMenu = new contactMenu(database);
                        contactMenu.contactMenu(customer);
                        break;
                    case 4:
                        SupportMenu supportMenu = new SupportMenu(database);
                        supportMenu.supportMenu(customer, answerRequestDatabase);
                        break;
                    case 5:
                        SettingMenu settingMenu = new SettingMenu(database);
                        settingMenu.settingMenu(customer);
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

    public void loginMenu() {
        System.out.println(Constant.BLUE + "please enter your national code and celNumber ");
    }

    private void login(AnswerRequestDatabase answerRequestDatabase) {
        loginMenu();
        Customer cust = null;
        String nationalCode = getNationalCode();
        String cellNumber = getCellNumber();
        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber) && customer.getNationalCode().equals(nationalCode)) {
                cust = customer;
                break;
            }
        }
        try {
            if (cust == null) {
                throw new RuntimeException("user not found!!");
            } else {
                if (cust.getStatus().equals(RegistrationStatus.ACCEPTED)) {
                    customerMenu(cust, answerRequestDatabase);
                } else if (cust.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                    System.out.println("in progressing");
                } else if (cust.getStatus().equals(RegistrationStatus.REJECTED)) {
                    cust.getRequestDatabase().getRequestList().get(0);
                    database.getCustomerDataBase().remove(cust);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    private void register() {
        String firstName = getFirstName();
        String lastName = getLastName();
        String cellNumber = getCellNumber();
        String nationalCode = getNationalCode();
        String password = getPassword();

        for (Customer customer : database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber) || customer.getNationalCode().equals(nationalCode)) {
                System.out.println(Constant.RED + "a customer with this data is already exist!!");
                return;
            }
        }
        while (!checkPassword(password)) {
            System.out.println(Constant.RED + "password is too weak try another password!!");
            password = ScannerWrapper.getInstance().next();
        }
        Customer customer = new Customer(firstName, lastName, password, nationalCode, cellNumber);
        database.addCustomer(customer);
    }

    private boolean checkPassword(String password) {
//        boolean upperCase = false;
//        boolean lowerCase = false;
//        boolean numeric = false;
//        boolean character = false;
//
//        for (int i = 0; i < password.length(); i++) {
//            if (Character.isUpperCase(password.charAt(i))) {
//                upperCase = true;
//            }
//            if (Character.isLowerCase(password.charAt(i))) {
//                lowerCase = true;
//            }
//            if (Character.isDigit(password.charAt(i))) {
//                numeric = true;
//            }
//        }
//        if (passwordHasSpecialChar(password)) {
//            character = true;
//        }
//        return (numeric && character && lowerCase && upperCase);
        return true;
    }

    private boolean passwordHasSpecialChar(String password) {
        if (password.contains("/") || password.contains("%") || password.contains("~")) {
            return false;
        }
        if (password.contains("@") || password.contains("#") || password.contains("^") ||
                password.contains("$") || password.contains("&") || password.contains("*")) {
            return true;
        }
        return false;
    }
}
