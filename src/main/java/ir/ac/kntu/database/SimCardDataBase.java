package ir.ac.kntu.database;

import ir.ac.kntu.cellphone.CellPhone;

import java.util.Set;

public class SimCardDataBase {
    private Set<CellPhone> cellPhoneList;

    public SimCardDataBase(Set<CellPhone> cellPhoneList) {
        this.cellPhoneList = cellPhoneList;
    }

    public void addCellPhone(CellPhone cellPhone) {
        cellPhoneList.add(cellPhone);
    }

    public Set<CellPhone> getCellPhoneList() {
        return cellPhoneList;
    }

    public void setCellPhoneList(Set<CellPhone> cellPhoneList) {
        this.cellPhoneList = cellPhoneList;
    }

    public void removeCellPhone(CellPhone cellPhone) {
        cellPhoneList.remove(cellPhone);
    }

    public boolean containCellPhone(CellPhone cellPhone) {
        return cellPhoneList.contains(cellPhone);
    }

    public CellPhone findCellPhone(String cellNumber) {
        for (CellPhone cellPhone : cellPhoneList) {
            if (cellPhone.getCellNo().equals(cellNumber)) {
                return cellPhone;
            }
        }
        return null;
    }
}
