package ir.ac.kntu.Person;

public class Management extends Person {
    String userName;

    public Management(String firstName, String lastName, String password, String userName) {
        super(firstName, lastName, password);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
