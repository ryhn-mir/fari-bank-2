package ir.ac.kntu.person;

import ir.ac.kntu.database.ContactDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.RecentTransactionsDataBase;
import ir.ac.kntu.database.RequestDatabase;
import ir.ac.kntu.faribank.Account;

import java.util.Objects;
import java.util.Random;

public class Customer extends Person {
    private String nationalCode;
    private String cellNumber;
    private Account account;
    private boolean contactIsOn = true;
    private ContactDatabase contactDatabase;
    private RecentTransactionsDataBase recentTrans;
    private RegistrationStatus status;
    private RequestDatabase requestDatabase;

    public Customer(String firstName, String lastName, String password, String nationalCode, String cellNumber) {
        super(firstName, lastName, password);
        this.nationalCode = nationalCode;
        recentTrans = new RecentTransactionsDataBase();
//        while (true) {
//            setCellNumber(cellNumber);
//            if (cellNumber != null) {
//                break;
//            } else {
//                System.out.println(Constant.CYAN + "Enter your cellphone number again");
//            }
//        }
        setCellNumber(cellNumber);
        contactDatabase = new ContactDatabase();
        this.status = RegistrationStatus.PROGRESSING;
        requestDatabase = new RequestDatabase();
        account = new Account(0, randAccountNo());

    }

    public Customer(Customer customer, String firstName, String lastName) {
        super(firstName, lastName, customer.getPassword());
        this.nationalCode = customer.nationalCode;
        this.cellNumber = customer.cellNumber;
        this.account = customer.account;
        this.contactDatabase = customer.getContactDatabase();
        this.contactIsOn = customer.contactIsOn;
        this.status = customer.status;
    }


    public RequestDatabase getRequestDatabase() {
        return requestDatabase;
    }

    public void setRequestDatabase(RequestDatabase requestDatabase) {
        this.requestDatabase = requestDatabase;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
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
        this.cellNumber = cellNumber;
    }

    public RecentTransactionsDataBase getRecentTrans() {
        return recentTrans;
    }

    public void setRecentTrans(RecentTransactionsDataBase recentTrans) {
        this.recentTrans = recentTrans;
    }

    private String randAccountNo() {
        Random random = new Random();
        return String.valueOf(random.nextInt((int) Math.pow(10, 8), (int) Math.pow(10, 9)));
    }

    public boolean isContactIsOn() {
        return contactIsOn;
    }

    public void setContactIsOn(boolean contactIsOn) {
        this.contactIsOn = contactIsOn;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public void addContact(String cellNumber, String firstName, String lastName, Database database) {
        Customer customer = contactDatabase.findCustomer(cellNumber, database);
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
    public String toString() {
        return "Customer{" + super.toString() +
                "nationalCode='" + nationalCode + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", status=" + status +
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
        Customer customer = (Customer) obj;
        return contactIsOn == customer.contactIsOn && Objects.equals(nationalCode, customer.nationalCode) && Objects.equals(cellNumber, customer.cellNumber) && Objects.equals(account, customer.account) && Objects.equals(contactDatabase, customer.contactDatabase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalCode, cellNumber, account, contactIsOn, contactDatabase);
    }
}
