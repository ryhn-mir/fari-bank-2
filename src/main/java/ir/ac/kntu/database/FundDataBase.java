package ir.ac.kntu.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FundDataBase {
    private List<Fund> fundList;

    public List<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(List<Fund> fundList) {
        this.fundList = fundList;
    }

    public FundDataBase() {
        fundList = new ArrayList<>();
    }

    public void addFund(Fund fund) {
        fundList.add(fund);
    }

    public void removeFund(Fund fund) {
        fundList.remove(fund);
    }

    public Fund findFund(String name) {
        for (Fund fund : fundList) {
            if (fund.getName().equals(name)) {
                return fund;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FundDataBase that = (FundDataBase) obj;
        return Objects.equals(fundList, that.fundList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fundList);
    }
}
