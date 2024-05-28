package ir.ac.kntu.faribank;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.TransactionDb;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionKind;

public class Account {
    private long balance = 0;
    private String accountNumber;
    private Card card;
    private TransactionDb transactionDb;

    public Account(long balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        transactionDb = new TransactionDb();
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
//        Database database = new Database();
        Customer customer = database.findReceiver(accountNumber);
        Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.INCREASE_CREDIT);
        transactionDb.addTransaction(transaction);

    }

    public void transfer(long money, String accountNumber) {
        Database database = new Database();
        try {
            if (money + Constant.WAGE <= balance) {
                setBalance(getBalance() - money - Constant.WAGE);
                Customer customer = database.findReceiver(accountNumber);
                customer.getAccount().setBalance(getBalance() + money);
                Transaction transaction = new Transaction(customer.getFirstName(), customer.getLastName(), customer.getAccount().getAccountNumber(), getAccountNumber(), TransactionKind.TRANSFER_MONEY);
                transactionDb.addTransaction(transaction);
            } else {
                throw new RuntimeException("balance is not enough : " + balance);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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
}
