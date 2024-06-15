package ir.ac.kntu;

import ir.ac.kntu.faribank.FariBank;
import ir.ac.kntu.util.ScannerWrapper;

public class Main {

    public static void main(String[] args) {
        FariBank fariBank = new FariBank();
        fariBank.start();
        ScannerWrapper.getInstance().close();
    }

}
