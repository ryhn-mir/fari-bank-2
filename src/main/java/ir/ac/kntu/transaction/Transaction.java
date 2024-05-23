package ir.ac.kntu.transaction;

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
    private TransactionKind transactionKind;


    public Transaction(String receiverFirstName, String receiverLastName, String receiverAccountNumber, String senderAccountNumber, TransactionKind transactionKind) {
        this.receiverFirstName = receiverFirstName;
        this.receiverLastName = receiverLastName;
        Calendar calendar = new Calendar();
        this.date = calendar.getDate();
        this.tracingNumber = getTracingNumber();
        this.dateFormat = calendar.getDateFormat();
        this.receiverAccountNumber = receiverAccountNumber;
        this.senderAccountNumber = senderAccountNumber;
        this.transactionKind = transactionKind;
    }

    public Date getDate() {
        return date;
    }

    public TransactionKind getTransactionKind() {
        return transactionKind;
    }

    public void setTransactionKind(TransactionKind transactionKind) {
        this.transactionKind = transactionKind;
    }

    private String getTracingNumber() {
        Random random = new Random();
        int max = 999999;
        int min = 100000;
        int valueOfTracingNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(valueOfTracingNumber);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "receiverFirstName='" + receiverFirstName + '\'' +
                ", receiverLastName='" + receiverLastName + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                ", tracingNumber='" + tracingNumber + '\'' +
                ", receiverAccountNumber='" + receiverAccountNumber + '\'' +
                ", senderAccountNumber='" + senderAccountNumber + '\'' +
                ", transactionKind=" + transactionKind +
                '}';
    }

}
