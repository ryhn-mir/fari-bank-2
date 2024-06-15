package ir.ac.kntu.person;

public class Management extends Person {
    private String userName;
    private UserState userState;
    private Permission permission;
    public Management(String firstName, String lastName, String password, String userName) {
        super(firstName, lastName, password);
        this.userName = userName;
        this.userState = UserState.UNBLOCKED;
        permission = new Permission();
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Management{" + super.toString() +
                "userName='" + userName + '\'' +
                '}';
    }
}
