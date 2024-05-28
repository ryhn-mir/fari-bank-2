package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;

public class CustomerMenu extends MainMenu {

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

    public void userRegistrationMenu(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            try {
                printUserRegistrationCustomerMenu();
                number = getNumber();
                switch (number) {
                    case 1: //login
                        login(answerDB);
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

    public void customerMenu(Customer customer, AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            try {
                printCustomerMenu();
                number = getNumber();
                switch (number) {
                    case 1 -> transferMoney(customer);
                    case 2 -> manageAccount(customer, database);
                    case 3 -> contact(customer);
                    case 4 -> support(customer, answerDB);
                    case 5 -> setting(customer);
                    case 99 -> {
                        return;
                    }
                    default -> throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void loginMenu() {
        System.out.println(Constant.BLUE + "please enter your national code and celNumber ");
    }

    private void login(AnswerRequestDatabase answerDB) {
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
                    customerMenu(cust, answerDB);
                } else if (cust.getStatus().equals(RegistrationStatus.PROGRESSING)) {
                    System.out.println("in progressing");
                } else if (cust.getStatus().equals(RegistrationStatus.REJECTED)) {
                    System.out.println(Constant.PURPLE + cust.getRequestDatabase().getRequestList().get(0));
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
        Customer customer = new Customer(firstName, lastName, password, nationalCode, cellNumber);
        database.addCustomer(customer);
    }

    private void transferMoney(Customer customer) {
        TransferMoneyMenu transferMoneyMenu = new TransferMoneyMenu(database);
        transferMoneyMenu.transferMoney(customer);
    }

    private void manageAccount(Customer customer, Database database) {
        ManageAccountMenu manageAccountMenu = new ManageAccountMenu();
        manageAccountMenu.manageAccountMenu(customer, database);
    }

    private void contact(Customer customer) {
        ContactMenu contactMenu = new ContactMenu(database);
        contactMenu.contactMenu(customer);
    }

    private void support(Customer customer, AnswerRequestDatabase answerDB) {
        SupportMenu supportMenu = new SupportMenu();
        supportMenu.supportMenu(customer, answerDB);
    }

    private void setting(Customer customer) {
        SettingMenu settingMenu = new SettingMenu();
        settingMenu.settingMenu(customer);
    }
}
