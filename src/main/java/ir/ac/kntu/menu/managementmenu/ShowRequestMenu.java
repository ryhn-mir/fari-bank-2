package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.util.ScannerWrapper;

public class ShowRequestMenu {
    public void printShowRequestMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.search");
        System.out.println(Constant.GREEN + "2.show");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void showRequestMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printShowRequestMenu();
            switch (number) {
                case 1:
                    SearchMenu searchMenu = new SearchMenu();
                    searchMenu.searchMenu(answerRequestDatabase);
                    break;
                case 2:
                    showRequest(answerRequestDatabase);
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showRequest(AnswerRequestDatabase answerRequestDatabase) {
        answerRequestDatabase.printAnswerRequest();
        int number = ScannerWrapper.getInstance().nextInt();
        answerRequestDatabase.getAnswer().get(number - 1).setRequestState(RequestState.PROGRESSING);
        System.out.println(answerRequestDatabase.getAnswer().get(number - 1) + "cellNumber : " + answerRequestDatabase.getAnswer().get(number - 1).getCellNumber());
    }
}
