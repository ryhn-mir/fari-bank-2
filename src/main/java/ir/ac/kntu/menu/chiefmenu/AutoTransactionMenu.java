package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.PayaDataBase;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.fund.FundKind;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.paya.Paya;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionKind;

import java.util.Date;

public class AutoTransactionMenu extends MainMenu {
    private Database database;
    private PayaDataBase payaDataBase;

    public AutoTransactionMenu(Database database, PayaDataBase payaDataBase) {
        this.database = database;
        this.payaDataBase = payaDataBase;
    }

    private void printAutoTransactionMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.transfer");
        System.out.println(Constant.GREEN + "2.fund profit");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void autoTransactionMenu() {
        int number = 0;
        while (number != 99) {
            printAutoTransactionMenu();
            number = getNumber();
            switch (number) {
                case 1 -> completeTransfers();
                case 2 -> fundProfit();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void completeTransfers() {
        payaDataBase.printPaya();
        if (payaDataBase.size() == 0) {
            System.out.println(Constant.RED + "there is no paya transfer to show");
            return;
        }
        int number = getNumber();
        if (0 < number && number <= payaDataBase.size()) {
            Paya paya = payaDataBase.getIndex(number - 1);
            String answer = getYesNo();
            if ("y".equals(answer)) {
                transferPaya(paya);
                payaDataBase.removePaya(paya);
            } else if ("n".equals(answer)) {
                payaDataBase.removePaya(paya);
            } else {
                System.out.println(Constant.RED + "invalid input");
                return;
            }
        } else {
            System.out.println(Constant.RED + "invalid input");
        }
    }

    private void transferPaya(Paya paya) {
        Customer fromCustomer = paya.getFromCustomer();
        Customer toCustomer = paya.getToCustomer();
        long money = paya.getMoney();
        fromCustomer.getAccount().withdraw(money + Constant.getFariPaya());
        toCustomer.getAccount().deposit(money);
        Transaction transaction = new Transaction(toCustomer.getFirstName(), toCustomer.getLastName(), toCustomer.getAccount().getAccountNumber(), fromCustomer.getAccount().getAccountNumber(), TransactionKind.TRANSFER_MONEY);
        fromCustomer.getAccount().getTransactionDb().addTransaction(transaction);
        fromCustomer.getRecentTrans().getRecentTrans().add(toCustomer);
        fromCustomer.getAccount().roundBalance();

    }

    private void fundProfit() {
        int count = showFundProfit();
        if (count == 1) {
            System.out.println(Constant.RED + "there is no fund to show");
            return;
        }
        int number = getNumber();
        if (!(0 < number && number < count)) {
            System.out.println(Constant.RED + "number out of range");
            return;
        }
        Fund fund = returnFund(number);
        Customer customer = returnCustomer(number);
        customer.getAccount().deposit((fund.getBalance() * Constant.getPROFIT()) / 100);

    }

    private int showFundProfit() {
        Date nowDate = new Date();
        int count = 1;
        for (Customer customer : database.getCustomerDataBase()) {
            for (Fund fund : customer.getAccount().getFundDataBase().getFundList()) {
                long seconds = nowDate.getTime() - fund.getDate().getTime();
                if (seconds > Constant.mileSecond && fund.getFundKind() == FundKind.PROFIT) {
                    System.out.println(Constant.PURPLE + count + "." + fund);
                    count++;
                }
            }
        }
        return count;
    }

    private Fund returnFund(int number) {
        Date nowDate = new Date();
        int count = 1;
        for (Customer customer : database.getCustomerDataBase()) {
            for (Fund fund : customer.getAccount().getFundDataBase().getFundList()) {
                long seconds = nowDate.getTime() - fund.getDate().getTime();
                if (seconds > Constant.mileSecond && fund.getFundKind() == FundKind.PROFIT) {
                    if (number == count) {
                        return fund;
                    }
                    count++;
                }
            }
        }
        return null;
    }

    private Customer returnCustomer(int number) {
        Date nowDate = new Date();
        int count = 1;
        for (Customer customer : database.getCustomerDataBase()) {
            for (Fund fund : customer.getAccount().getFundDataBase().getFundList()) {
                long seconds = nowDate.getTime() - fund.getDate().getTime();
                if (seconds > Constant.mileSecond && fund.getFundKind() == FundKind.PROFIT) {
                    if (number == count) {
                        return customer;
                    }
                    count++;
                }
            }
        }
        return null;
    }
}
