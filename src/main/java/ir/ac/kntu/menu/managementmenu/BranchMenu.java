package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.Permission;
import ir.ac.kntu.request.Request;
import ir.ac.kntu.request.RequestOption;
import ir.ac.kntu.request.RequestState;
import ir.ac.kntu.menu.MainMenu;

public class BranchMenu extends MainMenu {

    public void branchMenu(AnswerRequestDatabase answerDB, Management management) {
        int number = 0;
        while (number != 99) {
            RequestOption.print();
            number = getNumber();
            switch (number) {
                case 1:
                    checkContact(management, answerDB);
                    break;
                case 2:
                    checkSetting(management, answerDB);
                    break;
                case 3:
                    checkTransfer(management, answerDB);
                    break;
                case 4:
                    checkReport(management, answerDB);
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
                System.out.println(count + "." + request);
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

    private void checkContact(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isContact()) {
            System.out.println(Constant.RED + "you do not have permission");
            return;
        }
        contact(answerDB);
    }

    private void checkSetting(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isSetting()) {
            System.out.println(Constant.RED + "you do not have permission");
            return;
        }
        setting(answerDB);
    }

    private void checkTransfer(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isTransfer()) {
            System.out.println(Constant.RED + "you do not have permission");
            return;
        }
        transfer(answerDB);
    }

    private void checkReport(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isRequest()) {
            System.out.println(Constant.RED + "you do not have permission");
            return;
        }
        report(answerDB);
    }
}
