package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.BankDataBase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.PayaDataBase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

public class AccountMenu extends MainMenu {
    private Database database;
    private BankDataBase bankDataBase;
    private PayaDataBase payaDataBase;

    public AccountMenu(Database database, BankDataBase bankDataBase, PayaDataBase payaDataBase) {
        this.database = database;
        this.bankDataBase = bankDataBase;
        this.payaDataBase = payaDataBase;
    }

    private void printMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.transfer to fari account");
        System.out.println(Constant.GREEN + "2.transfer to another account (pole)");
        System.out.println(Constant.GREEN + "3.transfer to another account (paya)");
        System.out.println(Constant.GREEN + "99.back");
    }
    
    public void accountMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            printMenu();
            number = getNumber();
            switch (number) {
                case 1 -> fariAccount(customer);
                case 2 -> poleAccount(customer);
                case 3 -> payaAccount(customer);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void fariAccount(Customer customer) {
        String accountNumber = getAccountNumber();
        Customer cust = database.findReceiver(accountNumber);
        try {
            if (cust == null) {
                throw new RuntimeException("there is no person with that accountNumber");
            } else {
                if (!isAccepted(cust)) {
                    System.out.println(Constant.RED + "there is no customer");
                    return;
                }
                long money = getInputMoney();
                boolean check = customer.getAccount().transferFari(money, accountNumber, database);
                if (!check) {
                    return;
                }
                customer.getRecentTrans().getRecentTrans().add(cust);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void payaAccount(Customer customer) {
        String accountNumber = getAccountNumber();
        Customer cust = bankDataBase.findCustomer(accountNumber);
        if (cust == null) {
            System.out.println(Constant.RED + "person not found");
            return;
        }
        long money = getInputMoney();
        boolean check = customer.getAccount().transferPaya(money, customer, cust, payaDataBase);
        if (!check) {
            return;
        }
        System.out.println(Constant.PURPLE + "in progressing!");
    }

    private void poleAccount(Customer customer) {
        String accountNumber = getAccountNumber();
        Customer cust = bankDataBase.findCustomer(accountNumber);
        if (cust == null) {
            System.out.println(Constant.RED + "person not found!");
            return;
        }
        long money = getInputMoney();
        boolean check = customer.getAccount().transferPole(money, accountNumber, bankDataBase);
        if (!check) {
            return;
        }
        customer.getRecentTrans().getRecentTrans().add(cust);
    }
}
