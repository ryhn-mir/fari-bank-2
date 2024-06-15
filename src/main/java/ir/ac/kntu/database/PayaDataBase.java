package ir.ac.kntu.database;

import ir.ac.kntu.Constant;

import java.util.List;

public class PayaDataBase {
    private List<Paya> payaList;
    public PayaDataBase(List<Paya> payaList) {
        this.payaList = payaList;
    }

    public List<Paya> getPayaList() {
        return payaList;
    }

    public void setPayaList(List<Paya> payaList) {
        this.payaList = payaList;
    }

    public void addPaya(Paya paya) {
        payaList.add(paya);
    }

    public void removePaya(Paya paya) {
        payaList.remove(paya);
    }
    public int size() {
        return payaList.size();
    }

    public Paya getIndex(int index) {
        return payaList.get(index);
    }

    public void printPaya() {
        int count = 1;
        for (Paya paya : payaList) {
            System.out.println(Constant.PURPLE + count + "." + "source customer : " + paya.getFromCustomer().getLastName() + "destination customer : " + paya.getToCustomer().getLastName() );
            count++;
        }
    }
}
