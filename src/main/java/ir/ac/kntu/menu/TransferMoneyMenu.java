package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.DataBase.RecentTransactionsDataBase;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.LinkedList;
import java.util.List;

public class TransferMoneyMenu {
    public void printTransferMoneyMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.transfer money by recent account");
        System.out.println(Constant.GREEN + "2.transfer money by contact");
        System.out.println(Constant.GREEN + "3.transfer money by account number");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void transferMoney(Customer customer) {
        int number= ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printTransferMoneyMenu();
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
                    default:
                        throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    public void transferMoneyByContact(Customer customer) {
        if(!customer.isContactIsOn()) {
            System.out.println(Constant.RED + "you do not have access to contact");
            return;
        } else {
            int count = 1;
            for (Customer cust : customer.getContactDatabase().getContactList()) {
                System.out.println(count + "." + cust.getFirstName()+ " " + cust.getLastName());
            }
            int number = ScannerWrapper.getInstance().nextInt();
            if (number >= 1 && number <= count) {
                checkContact(customer, number);
            } else {
                System.out.println(Constant.RED + "number out of the range!!");
            }
        }
    }
    public void transferMoneyByAccountNumber (Customer customer) {
        System.out.println(Constant.PURPLE + "enter account number");
        String accountNumber = ScannerWrapper.getInstance().next();
        Customer cust = Database.findReceiver(accountNumber);
        try {
            if (cust == null) {
                throw new RuntimeException("there is no person with that accountNumber");
            } else {
                System.out.println(Constant.PURPLE + "enter the amount you want to transfer");
                long money = ScannerWrapper.getInstance().nextLong();
                customer.getAccount().transfer(money, accountNumber);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    private void checkContact(Customer customer, int number) {
        Customer cust = customer.getContactDatabase().getContactList().get(number);
        if (cust.getContactDatabase().checkContactIsOn(customer.getAccount().getAccountNumber())) {
            System.out.println(Constant.PURPLE + "enter the amount you want to transfer");
            long money = ScannerWrapper.getInstance().nextLong();
            customer.getAccount().transfer(money, cust.getAccount().getAccountNumber());
        } else {
            System.out.println(Constant.RED + "you are not the contact of " + cust.getFirstName() + " " + cust.getLastName());
        }
    }

    private void transferMoneyByRecentTransactions(Customer customer) {
        List<Customer> recentTransactions = customer.getRecentTransactions().getRecentTransactions();
        customer.getRecentTransactions().printContact();
        int number = ScannerWrapper.getInstance().nextInt();
        if (number >= 1 && number <= recentTransactions.size()) {
            Customer cust = customer.getRecentTransactions().getRecentTransactions().get(number - 1);
            long money = ScannerWrapper.getInstance().nextLong();
            customer.getAccount().transfer(money, cust.getAccount().getAccountNumber());
        } else {
            System.out.println(Constant.RED + "number out of the range!!");
        }

    }
}
