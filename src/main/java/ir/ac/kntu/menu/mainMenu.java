package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.util.ScannerWrapper;

public class mainMenu {
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
    } public String getLastName() {
        System.out.println(Constant.PURPLE + "enter your lastName :");
        return ScannerWrapper.getInstance().nextLine();
    }
    public String getNationalCode() {
        System.out.println(Constant.PURPLE + "enter your national code :");
        return ScannerWrapper.getInstance().nextLine();
    }
    public String getPassword() {
        System.out.println(Constant.PURPLE + "enter your password :");
        return ScannerWrapper.getInstance().nextLine();
    }
    public long getInputMoney() {
        System.out.println(Constant.PURPLE + "enter the amount of money :");
        return Long.parseLong(ScannerWrapper.getInstance().nextLine());
    }
    public int getNumber() {
        System.out.println(Constant.PURPLE + "enter number :");
        return Integer.parseInt(ScannerWrapper.getInstance().nextLine());
    }

    private boolean checkCellNumber(String cellNumber) {
        if (!cellNumber.matches("^(09)[0-9]{9}")) {
            System.out.println(Constant.RED + "invalid cellNumber format!!");
            return false;
        }
        return true;
    }
}
