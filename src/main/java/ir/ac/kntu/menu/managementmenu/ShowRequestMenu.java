package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.util.ScannerWrapper;

public class ShowRequestMenu {
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

    public void showRequestMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            printShowRequestMenu();
            number = ScannerWrapper.getInstance().nextInt();
            switch (number) {
                case 1:
                    SearchMenu searchMenu = new SearchMenu(database);
                    searchMenu.searchMenu(answerRequestDatabase);
                    break;
                case 2:
                    showRequest(answerRequestDatabase);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showRequest(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.RED  + "there is no customer to show!!");
        }
        answerRequestDatabase.printAnswerRequest();
        int number = ScannerWrapper.getInstance().nextInt();
        answerRequestDatabase.getAnswer().get(number - 1).setRequestState(RequestState.PROGRESSING);
        System.out.println(answerRequestDatabase.getAnswer().get(number - 1) + "cellNumber : " + answerRequestDatabase.getAnswer().get(number - 1).getCellNumber());
    }
}
