package ir.ac.kntu.database;

import ir.ac.kntu.simcardtransaction.SimCardTransaction;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimTransactionDataBase implements Serializable {
    private List<SimCardTransaction> simTrans;

    public SimTransactionDataBase() {
        simTrans = new ArrayList<>();
    }

    public List<SimCardTransaction> getSimTrans() {
        return simTrans;
    }

    public void setSimTrans(List<SimCardTransaction> simTrans) {
        this.simTrans = simTrans;
    }

    public void addSim(SimCardTransaction simTrans) {
        this.simTrans.add(simTrans);
    }

    public void removeSim(SimCardTransaction simTrans) {
        this.simTrans.remove(simTrans);
    }

    public void printTransactions() {
        int count = 1;
        for (SimCardTransaction simTrans : this.simTrans) {
            System.out.println(count + "." + simTrans);
            count++;
        }
    }
}
