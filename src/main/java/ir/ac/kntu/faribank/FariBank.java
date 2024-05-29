package ir.ac.kntu.faribank;

import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.RegistrationStatus;

public class FariBank {

    public static void start() {
        Database database = new Database();


        Customer customer1 = new Customer("a", "a", "Mm@1383", "12", "09102607040");
        Customer customer2 = new Customer("b", "b", "Rr@1384", "13", "09112607040");
        Customer customer3 = new Customer("c", "c", "Cc@1383", "14", "09122607040");

        customer1.setStatus(RegistrationStatus.ACCEPTED);
        customer2.setStatus(RegistrationStatus.ACCEPTED);
        customer3.setStatus(RegistrationStatus.ACCEPTED);

        database.getCustomerDataBase().add(customer1);
        database.getCustomerDataBase().add(customer2);
        database.getCustomerDataBase().add(customer3);



        Menu menu = new Menu(new AnswerRequestDatabase(), database);
        while (true) {
            if (menu.mainMenu() == 99) {
                break;
            }
        }
    }

}
