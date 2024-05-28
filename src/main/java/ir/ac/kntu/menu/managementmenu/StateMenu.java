package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.menu.MainMenu;

public class StateMenu extends MainMenu {

    private void printStateMenu() {
        System.out.println(Constant.BLUE + "choose one of the the following options :");
        System.out.println(Constant.GREEN + "1.submit");
        System.out.println(Constant.GREEN + "2.answered");
        System.out.println(Constant.GREEN + "3.progressing");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void stateMenu(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            printStateMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    showSubmit(answerDB);
                    break;
                case 2:
                    showAnswered(answerDB);
                    break;
                case 3:
                    showProgressing(answerDB);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showSubmit(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        print(answerDB, RequestState.SUBMIT);
        int count = 0;
        int number = getNumber();
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestState() == RequestState.SUBMIT) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showAnswered(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkState(RequestState.ANSWERED, answerDB)) {
            System.out.println(Constant.RED + "there is no answered customer");
            return;
        }
        print(answerDB, RequestState.ANSWERED);
        int count = 0;
        int number = getNumber();
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestState() == RequestState.ANSWERED) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showProgressing(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkState(RequestState.PROGRESSING, answerDB)) {
            System.out.println(Constant.RED + "there is no progressing customer");
            return;
        }
        print(answerDB, RequestState.PROGRESSING);
        int count = 0;
        int number = getNumber();
        for (Request request : answerDB.getAnswer()) {
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

    private void print(AnswerRequestDatabase answerDB, RequestState requestState) {
        int count = 1;
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestState() == requestState) {
                System.out.println(count + "." + requestState);
                count++;
            }
        }
    }

    private boolean checkState(RequestState requestState, AnswerRequestDatabase answerDB) {
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestState() == requestState) {
                return true;
            }
        }
        return false;
    }
}
