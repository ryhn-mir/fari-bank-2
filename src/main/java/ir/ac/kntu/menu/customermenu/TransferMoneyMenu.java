package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.BankDataBase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.PayaDataBase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

import java.util.List;

public class TransferMoneyMenu extends MainMenu {
    private Database database;
    private BankDataBase bankDataBase;
    private PayaDataBase payaDataBase;

    public TransferMoneyMenu(Database database, BankDataBase bankDataBase, PayaDataBase payaDataBase) {
        this.database = database;
        this.bankDataBase = bankDataBase;
        this.payaDataBase = payaDataBase;
    }

    public void printTransferMoneyMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.transfer money by recent account");
        System.out.println(Constant.GREEN + "2.transfer money by contact");
        System.out.println(Constant.GREEN + "3.transfer money by account number");
        System.out.println(Constant.GREEN + "4.transfer money by card number");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void transferMoney(Customer customer) {
        int number = 0;
        while (number != 99) {
            printTransferMoneyMenu();
            number = getNumber();
            try {
                switch (number) {
                    case 1 -> transferMoneyByRecentTransactions(customer);
                    case 2 -> transferMoneyByContact(customer);
                    case 3 -> accountToAccount(customer);
                    case 4 -> cardToCard(customer);
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

    private void accountToAccount(Customer customer) {
        AccountMenu accountMenu = new AccountMenu(database, bankDataBase, payaDataBase);
        accountMenu.accountMenu(customer);
    }

    private void cardToCard(Customer customer) {
        CardMenu cardMenu = new CardMenu(database, bankDataBase, payaDataBase);
        cardMenu.CardMenu(customer);
    }

    public void transferMoneyByContact(Customer customer) {
        if (customer.getContactDatabase().getContactList().isEmpty()) {
            System.out.println(Constant.RED + "there is no contact to transfer money");
            return;
        }
        if (!customer.isContactIsOn()) {
            System.out.println(Constant.RED + "you do not have access to contact");
        } else {
            int count = 1;
            for (Customer cust : customer.getContactDatabase().getContactList()) {
                System.out.println(count + "." + cust.getFirstName() + " " + cust.getLastName());
                count++;
            }
            int number = getNumber();
            if (number >= 1 && number <= count) {
                checkContact(customer, number);
            } else {
                System.out.println(Constant.RED + "number out of the range!!");
            }
        }
    }


    private void checkContact(Customer customer, int number) {
        Customer cust = customer.getContactDatabase().getContactList().get(number - 1);
        if (!cust.isContactIsOn()) {
            System.out.println(Constant.RED + "activation of your contact is off");
            return;
        }
        if (cust.getContactDatabase().checkContactIsOn(customer.getAccount().getAccountNumber())) {
            long money = getInputMoney();
            customer.getAccount().transfer(money, money + Constant.getFariFariWage(), cust.getAccount().getAccountNumber(), bankDataBase);
            customer.getRecentTrans().getRecentTrans().add(cust);
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
            customer.getAccount().transfer(money, wageKind(cust) + money, cust.getAccount().getAccountNumber(), bankDataBase);
            customer.getRecentTrans().getRecentTrans().add(cust);
        } else {
            System.out.println(Constant.RED + "number out of the range!!");
        }

    }

    private long wageKind(Customer customer) {
        if (database.getCustomerDataBase().contains(customer)) {
            return Constant.getFariFariWage();
        }
        if (bankDataBase.getBankList().contains(customer)) {
            return Constant.getFariAnotherCartWage();
        }
        return 0;
    }


}
