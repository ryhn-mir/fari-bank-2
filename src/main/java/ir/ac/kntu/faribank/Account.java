package ir.ac.kntu.faribank;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.*;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.fund.FundKind;
import ir.ac.kntu.paya.Paya;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionKind;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private long balance = 0;
    private String accountNumber;
    private Card card;
    private TransactionDb transactionDb;
    private FundDataBase fundDataBase;

    public Account(long balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactionDb = new TransactionDb();
        card = new Card();
        fundDataBase = new FundDataBase();
        fundDataBase.addFund(new Fund("defaultRemaining", 0, FundKind.REMAINING));
    }

    public FundDataBase getFundDataBase() {
        return fundDataBase;
    }

    public void setFundDataBase(FundDataBase fundDataBase) {
        this.fundDataBase = fundDataBase;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionDb getTransactionDb() {
        return transactionDb;
    }

    public void setTransactionDb(TransactionDb transactionDb) {
        this.transactionDb = transactionDb;
    }

    public void increaseCredit(long money, Database database) {
        setBalance(getBalance() + money);
        Customer customer = database.findReceiver(accountNumber);
        Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.INCREASE_CREDIT);
        transactionDb.addTransaction(transaction);

    }

    public boolean transfer(long money, long moneyWithWage, String accountNumber, BankDataBase bankDataBase) {
        try {
            if (moneyWithWage <= balance) {
                setBalance(getBalance() - moneyWithWage);
                Customer customer = bankDataBase.findCustomer(accountNumber);
                long finalMoney = customer.getAccount().getBalance() + money;
                customer.getAccount().setBalance(finalMoney);
                Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.TRANSFER_MONEY);
                transactionDb.addTransaction(transaction);
                roundBalance();
                return true;
            } else {
                throw new RuntimeException("balance is not enough : " + balance);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;

        }
    }

    public boolean transferFari(long money, String accountNumber, Database database) {
        if (money > 8000000) {
            System.out.println(Constant.RED + "payment is out of limit");
            return false;
        }
        if (money + Constant.getFari() > getBalance()) {
            System.out.println(Constant.RED + "balance is not enough");
            return false;
        }
        setBalance(getBalance() - money - Constant.getFari());
        Customer customer = database.findReceiver(accountNumber);
        customer.getAccount().setBalance(customer.getAccount().getBalance() + money);
        Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.TRANSFER_MONEY);
        transactionDb.addTransaction(transaction);
        roundBalance();
        return true;
    }

    public boolean transferPole(long money, String accountNumber, BankDataBase bankDataBase) {
        if (money > 5000000) {
            System.out.println(Constant.RED + "payment is out of limit");
            return false;
        }
        long wage = money * Constant.getFariPole() / 100;
        if (money + wage > getBalance()) {
            System.out.println(Constant.RED + "balance is not enough");
            return false;
        }
        setBalance(getBalance() - money - wage);
        Customer customer = bankDataBase.findCustomer(accountNumber);
        customer.getAccount().setBalance(customer.getAccount().getBalance() + money);
        Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.TRANSFER_MONEY);
        transactionDb.addTransaction(transaction);
        roundBalance();
        return true;
    }

    public boolean transferPaya(long money, Customer customer, Customer cust, PayaDataBase payaDataBase) {
        if (money > 5000000) {
            System.out.println(Constant.RED + "payment is out of limit");
            return false;
        }
        if (money + Constant.getFariPaya() > getBalance()) {
            System.out.println(Constant.RED + "balance is not enough");
            return false;
        }
        Paya paya = new Paya(customer, cust, money);
        payaDataBase.addPaya(paya);
        return true;
    }

    public void withdraw(long money) {
        setBalance(getBalance() - money);
    }

    public void deposit(long money) {
        setBalance(getBalance() + money);
    }

    public void roundBalance() {
        int size = String.valueOf(balance).length();
        int length = (size * 3) / 4;
        long remain = balance % (long) Math.pow(10, length);
        long finalMoney = (long) Math.pow(10, length) - remain;
        withdraw(finalMoney);
        Fund fund = fundDataBase.findFund("defaultRemaining");
        fund.deposit(finalMoney);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", card=" + card +
                ", transactionDb=" + transactionDb +
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
        Account account = (Account) obj;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(card, account.card) && Objects.equals(transactionDb, account.transactionDb) && Objects.equals(fundDataBase, account.fundDataBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, card, transactionDb, fundDataBase);
    }
}