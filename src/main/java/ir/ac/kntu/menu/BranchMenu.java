package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.util.ScannerWrapper;

public class BranchMenu {
    public void branchMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            RequestOption.print();
            switch (number) {
                case 1:
                    contact(answerRequestDatabase);
                    break;
                case 2:
                    setting(answerRequestDatabase);
                    break;
                case 3:
                    transfer(answerRequestDatabase);
                    break;
                case 4:
                    report(answerRequestDatabase);
                    break;
                default:
                    System.out.println(Constant.RED + " invalid number !!");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }

    public void report(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestOption.REPORT);
        int number = ScannerWrapper.getInstance().nextInt();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.REPORT) {
                count++;
                closeRequest(number, count, request);
            }
        }

    }

    private void transfer(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestOption.TRANSFER);
        int number = ScannerWrapper.getInstance().nextInt();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.TRANSFER) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void setting(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestOption.SETTING);
        int number = ScannerWrapper.getInstance().nextInt();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.SETTING) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void contact(AnswerRequestDatabase answerRequestDatabase) {
        print(answerRequestDatabase, RequestOption.CONTACT);
        int number = ScannerWrapper.getInstance().nextInt();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.CONTACT) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void print(AnswerRequestDatabase answerRequestDatabase, RequestOption requestOption) {
        int count = 1;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == requestOption) {
                System.out.println(count + "." + requestOption);
                count++;
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
}
