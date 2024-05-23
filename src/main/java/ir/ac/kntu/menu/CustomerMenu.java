package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

public class CustomerMenu {
    public void printUserRegistrationCustomerMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.log in");
        System.out.println(Constant.GREEN + "2.register");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void UserRegistrationMenu() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printUserRegistrationCustomerMenu();
                switch (number) {
                    case 1: //login
                        login();
                        break;
                    case 2: //register
                        register();
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

    public void printCustomerMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.transfer money");        // transfer money recent account by contact or recent account or account number
        System.out.println(Constant.GREEN + "2.manage your account");   // increase credit or show account balance or show the list of transactions
        System.out.println(Constant.GREEN + "3.go to contact menu");    // show contact list , edit contact info, add contact
        System.out.println(Constant.GREEN + "4.support problems");
        System.out.println(Constant.GREEN + "5.go to setting menu");    // edit card pass, edit username, activation contact
        System.out.println(Constant.GREEN + "99.back");

    }

    public void customerMenu(Customer customer) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            try {
                printCustomerMenu();
                switch (number) {
                    case 1:
                        TransferMoneyMenu transferMoneyMenu = new TransferMoneyMenu();
                        break;
                    case 2:
                        ManageAccountMenu manageAccountMenu = new ManageAccountMenu();
                        manageAccountMenu.manageAccountMenu(customer);
                        break;
                    case 3:
                        contactMenu contactMenu = new contactMenu();
                        contactMenu.contactMenu(customer);
                        break;
                    case 4:
                        break;
                    case 5:
                        SettingMenu settingMenu = new SettingMenu();
                        settingMenu.settingMenu(customer);
                        break;
                    default:
                        throw new RuntimeException("invalid number");
                }
                number = ScannerWrapper.getInstance().nextInt();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void loginMenu() {
        System.out.println(Constant.BLUE + "please enter your celNumber and national code ");
    }

    private void login() {
        loginMenu();
        Customer cust = null;
        String nationalCode = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Customer customer : Database.getCustomerDataBase()) {
            if (customer.getCellNumber().equals(cellNumber) && customer.getNationalCode().equals(nationalCode)) {
                cust = customer;
                break;
            }
        }
        try {
            if (cust == null) {
                throw new RuntimeException("user not found!!");
            } else {
                customerMenu(cust);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void register() {
        String firstName = ScannerWrapper.getInstance().next();
        String lastName = ScannerWrapper.getInstance().next();
        String cellNumber = ScannerWrapper.getInstance().next();
        String nationalCode = ScannerWrapper.getInstance().next();
        String password = ScannerWrapper.getInstance().next();

        for (Customer customer : Database.getCustomerDataBase()) {
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
        Database.addCustomer(customer);
    }

    private boolean checkPassword(String password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean numeric = false;
        boolean character = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                upperCase = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                lowerCase = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                numeric = true;
            }
        }
        if (passwordHasSpecialChar(password)) {
            character = true;
        }
        return (numeric && character && lowerCase && upperCase);
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
