package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.fund.FundKind;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

import java.util.Date;

public class ManageFundMenu extends MainMenu {
    private Database database;

    public ManageFundMenu(Database database) {
        this.database = database;
    }

    private void printManageFund() {
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "1.transfer from fund to account");
        System.out.println(Constant.GREEN + "2.transfer from account to fund");
        //System.out.println(Constant.GREEN + "3.transfer from fund to fund");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void manageFund(Customer customer) {
        int number = 0;
        while (number != 99) {
            printManageFund();
            number = getNumber();
            switch (number) {
                case 1 -> fundToAccount(customer);
                case 2 -> accountToFund(customer);
                //case 3 -> fundToFund(customer);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

//    private void fundToFund(Customer customer) {
//        String fromFund = getFundName();
//        if (checkFund(fromFund, customer)) {
//            return;
//        }
//        String toFund = getFundName();
//        if (checkFund(toFund, customer)) {
//            return;
//        }
//        Fund fund1 = customer.getAccount().getFundDataBase().findFund(fromFund);
//        Fund fund2 = customer.getAccount().getFundDataBase().findFund(toFund);
//        long money = getInputMoney();
//        if (money > fund1.getBalance()) {
//            System.out.println(Constant.RED + "your balance is not enough!");
//            return;
//        }
//        fund1.withdraw(money);
//        fund2.deposit(money);
//
//    }

    private void accountToFund(Customer customer) {
        String fundName = getFundName();
        if (checkFund(fundName, customer)) {
            return;
        }
        Fund fund = customer.getAccount().getFundDataBase().findFund(fundName);
        if (fund.getFundKind().equals(FundKind.REMAINING)) {
            System.out.println(Constant.RED + "you can not transfer money to remaining fund");
            return;
        } else if (fund.getFundKind().equals(FundKind.PROFIT)) {
            System.out.println(Constant.RED + "you transfer money to profit fund");
            return;
        }
        long money = getInputMoney();
        if (money > customer.getAccount().getBalance()) {
            System.out.println(Constant.RED + "your balance is not enough!");
            return;
        }
        customer.getAccount().withdraw(money);
        fund.deposit(money);
    }

    private void fundToAccount(Customer customer) {
        String fundName = getFundName();
        if (checkFund(fundName, customer)) {
            return;
        }
        Fund fund = customer.getAccount().getFundDataBase().findFund(fundName);
        if (fund.getFundKind() == FundKind.PROFIT) {
            if (!checkCountMonth(fund)) {
                System.out.println(Constant.RED + "you can not transfer money before : " + fund.getMouthCount() + "month");
                return;
            }
        }
        long money = getInputMoney();
        if (money > fund.getBalance()) {
            System.out.println(Constant.RED + "your balance is not enough!");
            return;
        }
        customer.getAccount().deposit(money);
        fund.withdraw(money);
        if (fund.getFundKind() == FundKind.PROFIT) {
            customer.getAccount().getFundDataBase().removeFund(fund);
        }
    }

    private boolean checkFund(String fundName, Customer customer) {
        for (Fund fund : customer.getAccount().getFundDataBase().getFundList()) {
            if (fundName.equals(fund.getName())) {
                return false;
            }
        }
        System.out.println(Constant.RED + "there is no fund with that name!");
        return true;
    }

    private boolean checkCountMonth(Fund fund) {
        Date nowDate = new Date();
        Date fundDate = fund.getDate();
        long dif = nowDate.getTime() - fundDate.getTime();
        if (dif > Constant.mileSecond * fund.getMouthCount()) {
            return true;
        }
        return false;
    }
}
