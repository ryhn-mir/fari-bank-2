package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.fund.FundKind;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.thread.Profit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FundMenu extends MainMenu {
    private void printFundMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.show list of funds");
        System.out.println(Constant.GREEN + "2.add funds");
        System.out.println(Constant.GREEN + "3.manage funds");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void fundMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            printFundMenu();
            number = getNumber();
            switch (number) {
                case 1 -> showFunds(customer);
                case 2 -> addFund(customer);
                case 3 -> manageFund(customer);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void manageFund(Customer customer) {
        ManageFundMenu manageFundMenu = new ManageFundMenu();
        manageFundMenu.manageFund(customer);
    }

    private void addFund(Customer customer) {
        String fundName = getFundName();
        Fund fund = customer.getAccount().getFundDataBase().findFund(fundName);
        if (fund != null) {
            System.out.println(Constant.RED + "this fund is already exist!");
            return;
        }
        String fundKind = getFundKind();
        if (fundKind.equals(FundKind.PROFIT.getKind())) {
            fund = createProfitFund(fundName, customer);
            if (fund == null) {
                return;
            }
            customer.getAccount().getFundDataBase().addFund(fund);
        } else if (fundKind.equals(FundKind.SAVING.getKind())) {
            customer.getAccount().getFundDataBase().addFund(new Fund(fundName, 0, FundKind.SAVING));
        } else if (fundKind.equals(FundKind.REMAINING.getKind())) {
            System.out.println(Constant.RED + "you can not add remaining fund!");
        } else {
            System.out.println(Constant.RED + "invalid input");
        }
    }

    private void showFunds(Customer customer) {
        int count = 1;
        for (Fund fund : customer.getAccount().getFundDataBase().getFundList()) {
            System.out.println(Constant.PURPLE + count + "." + fund);
            count++;
        }
    }

    private Fund createProfitFund(String fundName, Customer customer) {
        long money = getInputMoney();
        if (money > customer.getAccount().getBalance()) {
            System.out.println(Constant.RED + "your balance is not enough");
            return null;
        }
        int mouthCount = getMonthCount();
        Fund fund = new Fund(fundName, money, FundKind.PROFIT, mouthCount);
        customer.getAccount().withdraw(money);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(mouthCount);
        for (int i = 1; i <= mouthCount; i++) {
            Profit profit = new Profit();
            profit.setFund(fund, customer);
            Thread thread = new Thread(profit);
            executorService.schedule(thread, 30 * i, TimeUnit.DAYS);
        }
        executorService.close();
        return fund;
    }

}
