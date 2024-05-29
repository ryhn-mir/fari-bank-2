package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;
import ir.ac.kntu.util.ScannerWrapper;

public class MainMenu {
    public String getCellNumber() {
        String cellNumber = "";
        do {
            System.out.println(Constant.PURPLE + "enter your cellNumber :");
            cellNumber = ScannerWrapper.getInstance().nextLine();
        } while (!checkCellNumber(cellNumber));

        return cellNumber;
    }

    public String getFirstName() {
        System.out.println(Constant.PURPLE + "enter your firstName :");
        return ScannerWrapper.getInstance().nextLine();

    }

    public String getLastName() {
        System.out.println(Constant.PURPLE + "enter your lastName :");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getNationalCode() {
        System.out.println(Constant.PURPLE + "enter your national code :");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getPassword() {
        String passWord = "";
        do {
            System.out.println(Constant.PURPLE + "enter your password :");
            passWord = ScannerWrapper.getInstance().nextLine();

        } while (!checkPassword(passWord));
        return passWord;
    }

    public long getInputMoney() {
        System.out.println(Constant.PURPLE + "enter the amount of money :");
        return Long.parseLong(ScannerWrapper.getInstance().nextLine());
    }

    public int getNumber() {
        System.out.println(Constant.PURPLE + "enter number :");
        return Integer.parseInt(ScannerWrapper.getInstance().nextLine());
    }

    public String getRequest() {
        System.out.println(Constant.PURPLE + "enter request :");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getAccountNumber() {
        System.out.println(Constant.PURPLE + "enter accountNumber");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getAnswer() {
        System.out.println(Constant.PURPLE + "enter answer");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getUserName() {
        System.out.println(Constant.PURPLE + "enter userName");
        return ScannerWrapper.getInstance().nextLine();
    }

    public String getCardPass() {
        String pass = "";
        do {
            System.out.println(Constant.PURPLE + "enter password");
            pass = ScannerWrapper.getInstance().nextLine();
        } while (!checkCardPassword(pass));
        return pass;
    }

    public String getDate() {
        System.out.println(Constant.PURPLE + "enter date");
        return ScannerWrapper.getInstance().nextLine();
    }

    public boolean isAccepted(Customer customer) {
        return customer.getStatus() == RegistrationStatus.ACCEPTED;
    }

    private boolean checkCellNumber(String cellNumber) {
        if (!cellNumber.matches("^(09)[0-9]{9}")) {
            System.out.println(Constant.RED + "invalid cellNumber format!!");
            return false;
        }
        return true;
    }

    private boolean checkPassword(String password) {
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean numeric = false;
        boolean character = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                upperCase = true;
            }
            if (Character.isLowerCase(password.charAt(i))) {
                lowerCase = true;
            }
            if (Character.isDigit(password.charAt(i))) {
                numeric = true;
            }
        }
        if (passwordHasSpecialChar(password)) {
            character = true;
        }
        if (!(numeric && character && lowerCase && upperCase)) {
            System.out.println(Constant.RED + "password is too weak try another password!!");
        }
        return numeric && character && lowerCase && upperCase;
    }

    private boolean passwordHasSpecialChar(String password) {
        if (password.contains("/") || password.contains("%") || password.contains("~")) {
            return false;
        }
        return password.contains("@") || password.contains("#") || password.contains("^") ||
                password.contains("$") || password.contains("&") || password.contains("*");
    }

    private boolean checkCardPassword(String pass) {
        if (pass.matches("[0-9]{4}")) {
            return true;
        } else {
            System.out.println(Constant.RED + "invalid password format!!");
            return false;
        }
    }


}
