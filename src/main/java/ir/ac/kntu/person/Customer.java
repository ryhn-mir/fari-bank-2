package ir.ac.kntu.person;

import ir.ac.kntu.Constant;
import ir.ac.kntu.cellphone.CellPhone;
import ir.ac.kntu.database.*;
import ir.ac.kntu.faribank.Account;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Customer extends Person implements Serializable {
    private String nationalCode;
    //    private String cellNumber;
    private CellPhone cellPhone;
    private Account account;
    private boolean contactIsOn = true;
    private ContactDatabase contactDatabase;
    private RecentTransactionsDataBase recentTrans;
    private RegistrationStatus status;
    private RequestDatabase requestDatabase;
    private SimTransactionDataBase simTrans;

    public Customer(String firstName, String lastName, String password, String nationalCode, String cellNumber, SimCardDataBase simCardDataBase) {
        super(firstName, lastName, password);
        this.nationalCode = nationalCode;
        recentTrans = new RecentTransactionsDataBase();
        this.cellPhone = new CellPhone(cellNumber, simCardDataBase);
        if (!simCardDataBase.containCellPhone(cellPhone)) {
            simCardDataBase.addCellPhone(cellPhone);
        }
        contactDatabase = new ContactDatabase();
        this.status = RegistrationStatus.PROGRESSING;
        requestDatabase = new RequestDatabase();
        account = new Account(0, randAccountNo());
        simTrans = new SimTransactionDataBase();

    }

    public Customer(Customer customer, String firstName, String lastName) {
        super(firstName, lastName, customer.getPassword());
        this.nationalCode = customer.nationalCode;
        this.cellPhone = customer.cellPhone;
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
        return cellPhone.getCellNo();
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
        this.cellPhone.setCellNo(cellNumber);
    }

    public RecentTransactionsDataBase getRecentTrans() {
        return recentTrans;
    }

    public void setRecentTrans(RecentTransactionsDataBase recentTrans) {
        this.recentTrans = recentTrans;
    }

    public CellPhone getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(CellPhone cellPhone) {
        this.cellPhone = cellPhone;
    }

    public SimTransactionDataBase getSimTrans() {
        return simTrans;
    }

    public void setSimTrans(SimTransactionDataBase simTrans) {
        this.simTrans = simTrans;
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
        if (customer.getStatus() != RegistrationStatus.ACCEPTED) {
            System.out.println(Constant.RED + "there is no customer!!");
            return;
        }
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
                ", cellNumber='" + cellPhone.getCellNo() + '\'' +
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
        return Objects.equals(nationalCode, customer.nationalCode) && Objects.equals(cellPhone, customer.cellPhone) && Objects.equals(account, customer.account) && Objects.equals(contactDatabase, customer.contactDatabase) && Objects.equals(recentTrans, customer.recentTrans) && Objects.equals(requestDatabase, customer.requestDatabase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalCode, cellPhone, account, contactDatabase, recentTrans, requestDatabase);
    }
}
