package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.DataBase.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.util.ScannerWrapper;

public class StateMenu {
    private void printStateMenu() {
        System.out.println(Constant.BLUE + "choose one of the the following options :");
        System.out.println(Constant.GREEN + "1.submit");
        System.out.println(Constant.GREEN + "2.answered");
        System.out.println(Constant.GREEN + "3.progressing");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void stateMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printStateMenu();
            switch (number) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }
    private void showSubmit(AnswerRequestDatabase answerRequestDatabase) {

    }
    private void showAnswered(AnswerRequestDatabase answerRequestDatabase) {

    }
    private void showProgressing(AnswerRequestDatabase answerRequestDatabase) {
    }
}
