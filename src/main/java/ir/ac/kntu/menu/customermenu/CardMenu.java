package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.BankDataBase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.PayaDataBase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

public class CardMenu extends MainMenu {
    private Database database;
    private BankDataBase bankDataBase;
    private PayaDataBase payaDataBase;

    public CardMenu(Database database, BankDataBase bankDataBase, PayaDataBase payaDataBase) {
        this.database = database;
        this.bankDataBase = bankDataBase;
        this.payaDataBase = payaDataBase;
    }

    private void printMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.transfer to fari cart");
        System.out.println(Constant.GREEN + "2.transfer to another cart (pole)");
        System.out.println(Constant.GREEN + "3.transfer to another cart (paya)");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void cardMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            printMenu();
            number = getNumber();
            switch (number) {
                case 1 -> fariCart(customer);
                case 2 -> fariPole(customer);
                case 3 -> fariPaya(customer);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }

    }

    private void fariCart(Customer customer) {
        String cardNumber = getCardNumber();
        String accountNumber = database.getAccountNumberByCardNo(cardNumber);
        if (accountNumber == null) {
            System.out.println(Constant.RED + "there is no customer with this card number");
            return;
        }
        Customer cust = database.findReceiver(accountNumber);
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

    private void fariPole(Customer customer) {
        String cardNumber = getCardNumber();
        String accountNumber = bankDataBase.getAccountNumberByCardNumber(cardNumber);
        if (accountNumber == null) {
            System.out.println(Constant.RED + "there is no customer with this card number");
            return;
        }
        Customer cust = bankDataBase.findCustomer(accountNumber);
        long money = getInputMoney();
        boolean check = customer.getAccount().transferPole(money, accountNumber, bankDataBase);
        if (!check) {
            return;
        }
        customer.getRecentTrans().getRecentTrans().add(cust);
    }

    private void fariPaya(Customer customer) {
        String cardNumber = getCardNumber();
        String accountNumber = bankDataBase.getAccountNumberByCardNumber(cardNumber);
        if (accountNumber == null) {
            System.out.println(Constant.RED + "there is no customer with this card number");
            return;
        }
        Customer cust = bankDataBase.findCustomer(accountNumber);
        long money = getInputMoney();
        boolean check = customer.getAccount().transferPaya(money, customer, cust, payaDataBase);
        if (!check) {
            return;
        }
        System.out.println(Constant.PURPLE + "in progressing!");
    }

}
