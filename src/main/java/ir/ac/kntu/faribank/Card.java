package ir.ac.kntu.faribank;

import java.util.Random;

public class Card {
    private String password = "";

    public Card() {
        this.password = randomPass();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String randomPass() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 10000));
    }
}
