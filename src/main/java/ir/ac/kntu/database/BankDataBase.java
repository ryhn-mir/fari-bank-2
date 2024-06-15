package ir.ac.kntu.database;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;

import java.util.Set;

public class BankDataBase {
    private Set<Customer> bankList;

    public BankDataBase(SimCardDataBase simCardDataBase, Set<Customer> bankList) {
        this.bankList = bankList;
        Customer customer1 = new Customer("sara", "ghasemi", "Ss@1383", "91", "09052607040", simCardDataBase);
        Customer customer2 = new Customer("mozhgan", "hosseini", "Mm@1377", "92", "09372607040", simCardDataBase);
        customer1.getAccount().setAccountNumber("123456789");
        customer2.getAccount().setAccountNumber("123456798");
        customer1.getAccount().getCard().setCardNumber("123456789123");
        customer2.getAccount().getCard().setCardNumber("123456789132");
        customer1.setStatus(RegistrationStatus.ACCEPTED);
        customer2.setStatus(RegistrationStatus.ACCEPTED);
        bankList.add(customer1);
        bankList.add(customer2);
    }

    public Set<Customer> getBankList() {
        return bankList;
    }

    public void setBankList(Set<Customer> bankList) {
        this.bankList = bankList;
    }

    public void addCustomer(Customer customer) {
        bankList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        bankList.remove(customer);
    }

    public Customer findCustomer(String accountNumber) {
        for (Customer customer : bankList) {
            if (customer.getAccount().getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null;
    }

    public String getAccountNumberByCardNumber(String cardNumber) {
        for (Customer customer : bankList) {
            if (customer.getAccount().getCard().getCardNumber().equals(cardNumber)){
                return customer.getAccount().getAccountNumber();
            }
        }
        return null;
    }

    public void printBankDB() {
        int count = 1;
        for (Customer customer : bankList) {
            System.out.println(Constant.PURPLE + count + "." + customer.getFirstName() + " " + customer.getLastName());
            count++;
        }
    }

    public boolean contains(Customer customer) {
        return bankList.contains(customer);
    }

    public Customer findCustByPhone(String cellNumber) {
        for (Customer customer : bankList) {
            if (customer.getCellNumber().equals(cellNumber)) {
                return customer;
            }
        }
        return null;
    }
}
