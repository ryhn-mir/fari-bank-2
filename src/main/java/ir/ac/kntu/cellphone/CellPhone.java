package ir.ac.kntu.cellphone;

import java.util.Objects;

public class CellPhone {
    private String cellNo;
    private long charge;

    private SimCardDataBase simCardDataBase;

    public CellPhone(String cellNo, SimCardDataBase simCardDataBase) {
        this.cellNo = cellNo;
        this.simCardDataBase = simCardDataBase;
        checkExist(cellNo);
    }

    private void checkExist(String cellNumber) {
        for (CellPhone cellPhone : simCardDataBase.getCellPhoneList()) {
            if (cellPhone.getCellNo().equals(cellNumber)) {
                this.charge = cellPhone.getCharge();
                return;
            }
        }

        this.charge = 0;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public long getCharge() {
        return charge;
    }

    public void setCharge(long charge) {
        this.charge = charge;
    }
    public void increaseCharge(long charge) {
        setCharge(getCharge() + charge);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CellPhone cellPhone = (CellPhone) obj;
        return Objects.equals(cellNo, cellPhone.cellNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellNo);
    }
}
