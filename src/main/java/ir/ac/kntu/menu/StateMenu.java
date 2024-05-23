package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestState;
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
                    showSubmit(answerRequestDatabase);
                    break;
                case 2:
                    showAnswered(answerRequestDatabase);
                    break;
                case 3:
                    showProgressing(answerRequestDatabase);
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }

    private void showSubmit(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestState.SUBMIT);
        int count = 0;
        int number = ScannerWrapper.getInstance().nextInt();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.SUBMIT) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showAnswered(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestState.ANSWERED);
        int count = 0;
        int number = ScannerWrapper.getInstance().nextInt();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.ANSWERED) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void showProgressing(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestState.PROGRESSING);
        int count = 0;
        int number = ScannerWrapper.getInstance().nextInt();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestState() == RequestState.PROGRESSING) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void closeRequest(int number, int count, Request request) {
        if (number == count) {
            String answer = ScannerWrapper.getInstance().nextLine();
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
}
