package ir.ac.kntu;

import ir.ac.kntu.menu.Menu;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        while (true) {
            if (menu.mainMenu() == 99){
                break;
            }
        }
    }

}
