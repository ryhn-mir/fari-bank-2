package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.util.ScannerWrapper;

public class BranchMenu extends MainMenu {
    private Database database;

    public BranchMenu(Database database) {
        this.database = database;
    }

    public void branchMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            RequestOption.print();
            number = getNumber();
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
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + " invalid number !!");
            }
        }
    }

    public void report(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.REPORT, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no report request to show");
        }
        print(answerRequestDatabase, RequestOption.REPORT);
        int number = getNumber();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.REPORT) {
                count++;
                closeRequest(number, count, request);
            }
        }

    }

    private void transfer(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.TRANSFER, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no transfer request to show");
        }
        print(answerRequestDatabase, RequestOption.TRANSFER);
        int number = getNumber();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.TRANSFER) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void setting(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.SETTING, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no setting request to show");
        }
        print(answerRequestDatabase, RequestOption.SETTING);
        int number = getNumber();
        int count = 0;
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == RequestOption.SETTING) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void contact(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.CONTACT, answerRequestDatabase)) {
            System.out.println(Constant.RED + "there is no contact request to show");
        }
        print(answerRequestDatabase, RequestOption.CONTACT);
        int number = getNumber();
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
            String answer = getAnswer();
            request.setAnswer(answer);
            request.setRequestState(RequestState.ANSWERED);
        }
    }
    private boolean checkRequestOption(RequestOption requestOption, AnswerRequestDatabase answerRequestDatabase) {
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getRequestOption() == requestOption) {
                return true;
            }
        }
        return false;
    }
}
