package ir.ac.kntu.database;

import java.util.ArrayList;
import java.util.List;

public class SimTransactionDataBase {
    private List<SimCardTransaction> simCardTransactions;

    public SimTransactionDataBase() {
        simCardTransactions = new ArrayList<>();
    }

    public List<SimCardTransaction> getSimCardTransactions() {
        return simCardTransactions;
    }

    public void setSimCardTransactions(List<SimCardTransaction> simCardTransactions) {
        this.simCardTransactions = simCardTransactions;
    }

    public void addSim(SimCardTransaction simCardTransaction) {
        simCardTransactions.add(simCardTransaction);
    }

    public void removeSim(SimCardTransaction simCardTransaction) {
        simCardTransactions.remove(simCardTransaction);
    }

    public void printTransactions() {
        int count = 1;
        for (SimCardTransaction simCardTransaction : simCardTransactions) {
            System.out.println(count + "." + simCardTransaction);
            count++;
        }
    }
}
