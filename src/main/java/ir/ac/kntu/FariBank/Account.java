package ir.ac.kntu.FariBank;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.DataBase.TransactionDb;
import ir.ac.kntu.Person.Customer;
import ir.ac.kntu.Transaction.Transaction;

import java.util.Set;

public class Account {
    private long balance = 0;
    private String accountNumber;
    private TransactionDb transactionDb;

    public Account(long balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactionDb = new TransactionDb();
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

    public void increaseCredit(long money) {
        setBalance(getBalance() + money);
    }

    public void transfer(long money, String accountNumber) {
        try {
            if (money + Constant.WAGE <= balance) {
                setBalance(getBalance() - money - Constant.WAGE);
                Customer customer = Database.find_transferToReceiver(accountNumber);
                customer.getAccount().increaseCredit(money);
                Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber());
                transactionDb.addTransaction(transaction);
            } else {
                throw new RuntimeException("balance is not enough : " + balance);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
