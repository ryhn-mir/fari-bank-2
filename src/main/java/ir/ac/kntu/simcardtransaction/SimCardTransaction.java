package ir.ac.kntu.simcardtransaction;

public class SimCardTransaction {
    private String firstName;
    private String lastName;
    private String cellNumber;
    private long charge;

    public SimCardTransaction(String firstName, String lastName, String cellNumber, Long charge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNumber = cellNumber;
        this.charge = charge;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public long getCharge() {
        return charge;
    }

    public void setCharge(long charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "SimCardTransaction{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cellNumber='" + cellNumber + '\'' +
                ", charge=" + charge +
                '}';
    }
}
