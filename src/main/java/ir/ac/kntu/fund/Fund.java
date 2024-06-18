package ir.ac.kntu.fund;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Fund implements Serializable {
    private String name;
    private long balance;
    private FundKind fundKind;
    private Date date;
    private int mouthCount;

    public Fund(String name, long balance, FundKind fundKind) {
        this.name = name;
        this.balance = balance;
        this.fundKind = fundKind;
        date = new Date();
    }

    public Fund(String name, long balance, FundKind fundKind, int mouthCount) {
        this.name = name;
        this.balance = balance;
        this.fundKind = fundKind;
        this.mouthCount = mouthCount;
        date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public FundKind getFundKind() {
        return fundKind;
    }

    public void setFundKind(FundKind fundKind) {
        this.fundKind = fundKind;
    }

    public void withdraw(long money) {
        setBalance(getBalance() - money);
    }

    public void deposit(long money) {
        setBalance(getBalance() + money);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMouthCount() {
        return mouthCount;
    }

    public void setMouthCount(int mouthCount) {
        this.mouthCount = mouthCount;
    }

    public void newDate() {
        date = new Date();
    }

    @Override
    public String toString() {
        return "Fund{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", fundKind=" + fundKind +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Fund fund = (Fund) obj;
        return Objects.equals(name, fund.name) && fundKind == fund.fundKind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fundKind);
    }
}
