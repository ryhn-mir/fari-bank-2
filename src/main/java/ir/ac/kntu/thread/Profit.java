package ir.ac.kntu.thread;

import ir.ac.kntu.Constant;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.person.Customer;

public class Profit {
    private Fund fund;
    private Customer customer;
    public void setFund(Fund fund, Customer customer) {
        this.fund = fund;
        this.customer = customer;
    }
    @Override
    public void run() {
        customer.getAccount().deposit((fund.getBalance() * Constant.getPROFIT()) / 100);
        fund.setMouthCount(fund.getMouthCount() - 1);
        fund.newDate();
    }
}
