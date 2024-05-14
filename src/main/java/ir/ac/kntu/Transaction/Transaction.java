package ir.ac.kntu.Transaction;

import ir.ac.kntu.FariBank.Account;
import ir.ac.kntu.util.Calendar;

import java.util.Date;
import java.util.Random;

public class Transaction {
    private String receiverFirstName;
    private String receiverLastName;
    private Date date;
    private String dateFormat;
    private String tracingNumber;
    private String receiverAccountNumber;
    private String senderAccountNumber;

    public Transaction(String receiverFirstName, String receiverLastName, String receiverAccountNumber, String senderAccountNumber) {
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        Calendar calendar = new Calendar();
        this.date = calendar.getDate();
        this.tracingNumber = getTracingNumber();
        this.dateFormat = calendar.getDateFormat();
        this.receiverAccountNumber = receiverAccountNumber;
        this.senderAccountNumber = senderAccountNumber;
    }
    private String getTracingNumber () {
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int valueOfTracingNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(valueOfTracingNumber);
    }

}
