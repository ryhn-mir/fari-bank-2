package ir.ac.kntu.Person;

import ir.ac.kntu.Constant;
import ir.ac.kntu.FariBank.Account;

import java.util.Map;
import java.util.regex.Pattern;

public class Customer extends Person {
    private String id;
    private String cellNumber;
    private Account account;

    public Customer(String firstName, String lastName, String password, String id, String cellNumber) {
        super(firstName, lastName, password);
        this.id = id;
        while (true) {
            setCellNumber(cellNumber);
            if(cellNumber != null) {
                break;
            } else {
                System.out.println( Constant.CYAN + "Enter your cellphone number again");
            }
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
