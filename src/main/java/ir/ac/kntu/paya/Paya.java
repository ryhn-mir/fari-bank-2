package ir.ac.kntu.paya;

import ir.ac.kntu.person.Customer;

public class Paya {
    private Customer fromCustomer;
    private Customer toCustomer;
    private long money;

    public Paya(Customer fromCustomer, Customer toCustomer, long money) {
        this.fromCustomer = fromCustomer;
        this.toCustomer = toCustomer;
        this.money = money;
    }

    public Customer getFromCustomer() {
        return fromCustomer;
    }

    public void setFromCustomer(Customer fromCustomer) {
        this.fromCustomer = fromCustomer;
    }

    public Customer getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(Customer toCustomer) {
        this.toCustomer = toCustomer;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
