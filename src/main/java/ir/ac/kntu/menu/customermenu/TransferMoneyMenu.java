package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

import java.util.List;

public class TransferMoneyMenu extends MainMenu {
    private Database database;

    public TransferMoneyMenu(Database database) {
        this.database = database;
    }

    public void printTransferMoneyMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.transfer money by recent account");
        System.out.println(Constant.GREEN + "2.transfer money by contact");
        System.out.println(Constant.GREEN + "3.transfer money by account number");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void transferMoney(Customer customer) {
        int number = 0;
        while (number != 99) {
            printTransferMoneyMenu();
            number = getNumber();
            try {
                switch (number) {
                    case 1:
                        transferMoneyByRecentTransactions(customer);
                        break;
                    case 2:
                        transferMoneyByContact(customer);
                        break;
                    case 3:
                        transferMoneyByAccountNumber(customer);
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

    public void transferMoneyByContact(Customer customer) {
        if (customer.getContactDatabase().getContactList().isEmpty()) {
            System.out.println(Constant.RED +  "there is no contact to transfer money");
            return;
        }
        if (!customer.isContactIsOn()) {
            System.out.println(Constant.RED + "you do not have access to contact");
            return;
        } else {
            int count = 1;
            for (Customer cust : customer.getContactDatabase().getContactList()) {
                System.out.println(count + "." + cust.getFirstName() + " " + cust.getLastName());
            }
            int number = getNumber();
            if (number >= 1 && number <= count) {
                checkContact(customer, number);
            } else {
                System.out.println(Constant.RED + "number out of the range!!");
            }
        }
    }

    public void transferMoneyByAccountNumber(Customer customer) {
        String accountNumber = getAccountNumber();
        Customer cust = database.findReceiver(accountNumber);
        try {
            if (cust == null) {
                throw new RuntimeException("there is no person with that accountNumber");
            } else {
                long money = getInputMoney();
                customer.getAccount().transfer(money, accountNumber);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkContact(Customer customer, int number) {
        Customer cust = customer.getContactDatabase().getContactList().get(number);
        if (cust.getContactDatabase().checkContactIsOn(customer.getAccount().getAccountNumber())) {
            long money = getInputMoney();
            customer.getAccount().transfer(money, cust.getAccount().getAccountNumber());
        } else {
            System.out.println(Constant.RED + "you are not the contact of " + cust.getFirstName() + " " + cust.getLastName());
        }
    }

    private void transferMoneyByRecentTransactions(Customer customer) {
        if (customer.getRecentTrans().getRecentTrans().isEmpty()) {
            System.out.println(Constant.RED + "there is no transaction to transfer money");
            return;
        }
        List<Customer> recentTrans = customer.getRecentTrans().getRecentTrans();
        customer.getRecentTrans().printContact();
        int number = getNumber();
        if (number >= 1 && number <= recentTrans.size()) {
            Customer cust = customer.getRecentTrans().getRecentTrans().get(number - 1);
            long money = getInputMoney();
            customer.getAccount().transfer(money, cust.getAccount().getAccountNumber());
        } else {
            System.out.println(Constant.RED + "number out of the range!!");
        }

    }
}
