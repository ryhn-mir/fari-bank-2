package ir.ac.kntu.faribank;

import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;

public class FariBank {

    public static void start() {
        Database database = new Database();


        Menu menu = new Menu(new AnswerRequestDatabase(), database);
        while (true) {
            if (menu.mainMenu() == 99) {
                break;
            }
        }
    }

}
