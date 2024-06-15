package ir.ac.kntu.person;

import java.util.ArrayList;
import java.util.List;

public class Chief extends  Person{
    private String userName;
    private List<Chief> heads;
    private int position;
    private UserState userState;

    public Chief(String firstName, String lastName, String password, String userName, int position) {
        super(firstName, lastName, password);
        this.userName = userName;
        heads = new ArrayList<>();
        this.position = position;
        this.userState = UserState.UNBLOCKED;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public List<Chief> getHeads() {
        return heads;
    }

    public void setHeads(List<Chief> heads) {
        this.heads = heads;
    }

    @Override
    public String toString() {
        return "Chief{" + super.toString() +
                "userName='" + userName + '\'' +
                '}';
    }
}
