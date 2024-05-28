package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;

public class ShowRequestMenu extends MainMenu {
    private Database database;

    public ShowRequestMenu(Database database) {
        this.database = database;
    }

    public void printShowRequestMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.search");
        System.out.println(Constant.GREEN + "2.show");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void showRequestMenu(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            printShowRequestMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    SearchMenu searchMenu = new SearchMenu(database);
                    searchMenu.searchMenu(answerDB);
                    break;
                case 2:
                    showRequest(answerDB);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showRequest(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.RED + "there is no customer to show!!");
        }
        answerDB.printAnswerRequest();
        int number = getNumber();
        answerDB.getAnswer().get(number - 1).setRequestState(RequestState.PROGRESSING);
        System.out.println(answerDB.getAnswer().get(number - 1) + "cellNumber : " + answerDB.getAnswer().get(number - 1).getCellNumber());
    }
}
