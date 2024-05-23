package ir.ac.kntu.person;

import ir.ac.kntu.Constant;

public enum RegistrationStatus {
    REJECTED(Constant.CYAN + "rejected"),
    PROGRESSING(Constant.CYAN + "progressing"),
    ACCEPTED(Constant.CYAN + "accepted");

    private final String statusOfCustomer;

    RegistrationStatus(String statusOfCustomer) {
        this.statusOfCustomer = statusOfCustomer;
    }

    public String getStatusOfCustomer() {
        return statusOfCustomer;
    }
}
