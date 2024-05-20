package ir.ac.kntu.Person;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.ContactDatabase;
import ir.ac.kntu.DataBase.Database;
import ir.ac.kntu.DataBase.RecentTransactionsDataBase;
import ir.ac.kntu.FariBank.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer extends Person {
    private String nationalCode;
    private String cellNumber;
    private Account account;
    private boolean contactIsOn = true;
    private ContactDatabase contactDatabase;
    private RecentTransactionsDataBase recentTransactions;

    public Customer(String firstName, String lastName, String password, String nationalCode, String cellNumber) {
        super(firstName, lastName, password);
        this.nationalCode = nationalCode;
        recentTransactions = new RecentTransactionsDataBase();
        while (true) {
            setCellNumber(cellNumber);
            if (cellNumber != null) {
                break;
            } else {
                System.out.println(Constant.CYAN + "Enter your cellphone number again");
            }
        }
        contactDatabase = new ContactDatabase();

    }

    public Customer(Customer customer, String firstName, String lastName) {
        super(firstName, lastName, customer.getPassword());
        this.nationalCode = customer.nationalCode;
        this.cellNumber = customer.cellNumber;
        this.account = customer.account;
        this.contactDatabase = customer.getContactDatabase();
        this.contactIsOn = customer.contactIsOn;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String id) {
        this.nationalCode = nationalCode;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ContactDatabase getContactDatabase() {
        return contactDatabase;
    }

    public void setContactDatabase(ContactDatabase contactDatabase) {
        this.contactDatabase = contactDatabase;
    }

    public void setCellNumber(String cellNumber) {
        try {
            if (Pattern.matches("^(09)[0-9]{9}", cellNumber)) {
                this.cellNumber = cellNumber;
            } else {
                throw new RuntimeException("invalid format for cellphone number");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public RecentTransactionsDataBase getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(RecentTransactionsDataBase recentTransactions) {
        this.recentTransactions = recentTransactions;
    }

    public boolean isContactIsOn() {
        return contactIsOn;
    }

    public void setContactIsOn(boolean contactIsOn) {
        this.contactIsOn = contactIsOn;
    }

    public void addContact(String cellNumber, String firstName, String lastName) {
        Customer customer = contactDatabase.findCustomer(cellNumber);
        try {
            if (customer != null) {
                Customer cust = new Customer(customer, firstName, lastName);
                contactDatabase.addContact(cust);
            } else {
                throw new RuntimeException("contact not found!!");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return contactIsOn == customer.contactIsOn && Objects.equals(nationalCode, customer.nationalCode) && Objects.equals(cellNumber, customer.cellNumber) && Objects.equals(account, customer.account) && Objects.equals(contactDatabase, customer.contactDatabase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalCode, cellNumber, account, contactIsOn, contactDatabase);
    }
}
