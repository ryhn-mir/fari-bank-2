package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestOption;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.menu.MainMenu;

public class BranchMenu extends MainMenu {

    public void branchMenu(AnswerRequestDatabase answerDB) {
        int number = 0;
        while (number != 99) {
            RequestOption.print();
            number = getNumber();
            switch (number) {
                case 1:
                    contact(answerDB);
                    break;
                case 2:
                    setting(answerDB);
                    break;
                case 3:
                    transfer(answerDB);
                    break;
                case 4:
                    report(answerDB);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + " invalid number !!");
            }
        }
    }

    public void report(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.REPORT, answerDB)) {
            System.out.println(Constant.RED + "there is no report request to show");
            return;
        }
        print(answerDB, RequestOption.REPORT);
        int number = getNumber();
        int count = 0;
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestOption() == RequestOption.REPORT) {
                count++;
                closeRequest(number, count, request);
            }
        }

    }

    private void transfer(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.TRANSFER, answerDB)) {
            System.out.println(Constant.RED + "there is no transfer request to show");
            return;
        }
        print(answerDB, RequestOption.TRANSFER);
        int number = getNumber();
        int count = 0;
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestOption() == RequestOption.TRANSFER) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void setting(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.SETTING, answerDB)) {
            System.out.println(Constant.RED + "there is no setting request to show");
            return;
        }
        print(answerDB, RequestOption.SETTING);
        int number = getNumber();
        int count = 0;
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestOption() == RequestOption.SETTING) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void contact(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        if (!checkRequestOption(RequestOption.CONTACT, answerDB)) {
            System.out.println(Constant.RED + "there is no contact request to show");
            return;
        }
        print(answerDB, RequestOption.CONTACT);
        int number = getNumber();
        int count = 0;
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestOption() == RequestOption.CONTACT) {
                count++;
                closeRequest(number, count, request);
            }
        }
    }

    private void print(AnswerRequestDatabase answerDB, RequestOption requestOption) {
        int count = 1;
        for (Request request : answerDB.getAnswer()) {
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

    private boolean checkRequestOption(RequestOption requestOption, AnswerRequestDatabase answerDB) {
        for (Request request : answerDB.getAnswer()) {
            if (request.getRequestOption() == requestOption) {
                return true;
            }
        }
        return false;
    }
}
