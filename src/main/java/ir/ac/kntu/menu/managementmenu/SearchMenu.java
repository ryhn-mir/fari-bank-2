package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.Permission;
import ir.ac.kntu.request.Request;
import ir.ac.kntu.request.RequestState;
import ir.ac.kntu.menu.MainMenu;

public class SearchMenu extends MainMenu {

    private Database database;

    public SearchMenu(Database database) {
        this.database = database;
    }

    public void printSearchMenu() {
        System.out.println(Constant.BLUE + "choose one the the following option :");
        System.out.println(Constant.GREEN + "1.search by state");
        System.out.println(Constant.GREEN + "2.search by branch");
        System.out.println(Constant.GREEN + "3.search by user");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void searchMenu(AnswerRequestDatabase answerDB, Management management) {
        int number = 0;
        while (number != 99) {
            printSearchMenu();
            number = getNumber();
            switch (number) {
                case 1:
                    checkState(management, answerDB);
                    break;
                case 2:
                    BranchMenu branchMenu = new BranchMenu();
                    branchMenu.branchMenu(answerDB, management);
                    break;
                case 3:
                    checkUser(management, answerDB);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void searchByUser(AnswerRequestDatabase answerDB) {
        if (answerDB.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        String cellNumber = getCellNumber();
        for (Request request : answerDB.getAnswer()) {
            if (request.getCellNumber().equals(cellNumber)) {
                System.out.println(Constant.PURPLE + request);
                String answer = getAnswer();
                request.setAnswer(answer);
                request.setRequestState(RequestState.ANSWERED);
                return;
            }
        }
        System.out.println(Constant.PURPLE + "a customer with this cellNumber does not exist!");
    }

    private void checkState(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isState()) {
            System.out.println(Constant.RED + "you do not have permission");
            return;
        }
        StateMenu stateMenu = new StateMenu();
        stateMenu.stateMenu(answerDB);
    }

    private void checkUser(Management management, AnswerRequestDatabase answerDB) {
        Permission permission = management.getPermission();
        if (!permission.isUser()) {
            System.out.println(Constant.RED + "you d not have permission");
            return;
        }
        searchByUser(answerDB);
    }
}
