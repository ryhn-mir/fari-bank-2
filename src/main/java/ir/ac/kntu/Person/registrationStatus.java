package ir.ac.kntu.Person;

import ir.ac.kntu.Constant;

public enum registrationStatus {
    REJECTED(Constant.CYAN + "rejected"),
    PROGRESSING(Constant.CYAN + "progressing"),
    ACCEPTED(Constant.CYAN + "accepted");

    private final String statusOfCustomer;

    registrationStatus(String statusOfCustomer) {
        this.statusOfCustomer = statusOfCustomer;
    }

    public String getStatusOfCustomer() {
        return statusOfCustomer;
    }
}
