package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.util.ScannerWrapper;

public class StateMenu extends MainMenu {
    private Database database;

    public StateMenu(Database database) {
        this.database = database;
    }

    private void printStateMenu() {
        System.out.println(Constant.BLUE + "choose one of the the following options :");
        System.out.println(Constant.GREEN + "1.submit");
        System.out.println(Constant.GREEN + "2.answered");
        System.out.println(Constant.GREEN + "3.progressing");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void stateMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            printStateMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    showSubmit(answerRequestDatabase);
                    break;
                case 2:
                    showAnswered(answerRequestDatabase);
                    break;
                case 3:
                    showProgressing(answerRequestDatabase);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showSubmit(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        print(answerRequestDatabase, RequestState.SUBMIT);
        int count = 0;
        int number = getNumber();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.SUBMIT) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showAnswered(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkState(RequestState.ANSWERED, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no answered customer");
            return;
        }
        print(answerRequestDatabase, RequestState.ANSWERED);
        int count = 0;
        int number = getNumber();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.ANSWERED) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showProgressing(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkState(RequestState.PROGRESSING, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no progressing customer");
            return;
        }
        print(answerRequestDatabase, RequestState.PROGRESSING);
        int count = 0;
        int number = getNumber();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.PROGRESSING) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void closeRequest(int number, int count, Request request) {
        if (number == count) {
            System.out.println(Constant.PURPLE + request);
            String answer = getAnswer();
            request.setAnswer(answer);
            request.setRequestState(RequestState.ANSWERED);
        }
    }

    private void print(AnswerRequestDatabase answerRequestDatabase, RequestState requestState) {
        int count = 1;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == requestState) {
                System.out.println(count + "." + requestState);
                count++;
            }
        }
    }
    private boolean checkState(RequestState requestState, AnswerRequestDatabase answerRequestDatabase) {
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == requestState) {
                return true;
            }
        }
        return false;
    }
}
