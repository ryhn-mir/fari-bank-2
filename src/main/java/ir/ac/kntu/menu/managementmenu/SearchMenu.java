package ir.ac.kntu.menu.managementmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.util.ScannerWrapper;

public class SearchMenu {
    public void printSearchMenu() {
        System.out.println(Constant.BLUE + "choose one the the following option :");
        System.out.println(Constant.GREEN + "1.search by state");
        System.out.println(Constant.GREEN + "2.search by branch");
        System.out.println(Constant.GREEN + "3.search by user");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void searchMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = 0;
        while (number != 99) {
            printSearchMenu();
            number = ScannerWrapper.getInstance().nextInt();
            switch (number) {
                case 1:
                    StateMenu stateMenu = new StateMenu();
                    stateMenu.stateMenu(answerRequestDatabase);
                    break;
                case 2:
                    BranchMenu branchMenu = new BranchMenu();
                    branchMenu.branchMenu(answerRequestDatabase);
                    break;
                case 3:
                    searchByUser(answerRequestDatabase);
                    break;
                case 99:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }
    private void searchByUser(AnswerRequestDatabase answerRequestDatabase) {
        if (answerRequestDatabase.getAnswer().isEmpty()) {
            System.out.println(Constant.PURPLE + "there is no customer");
            return;
        }
        System.out.println(Constant.PURPLE + "enter cellNumber");
        String cellNumber = ScannerWrapper.getInstance().next();
        for (Request request : answerRequestDatabase.getAnswer()) {
            if (request.getCellNumber().equals(cellNumber)) {
                System.out.println(Constant.PURPLE + request);
                String answer = ScannerWrapper.getInstance().nextLine();
                request.setAnswer(answer);
                request.setRequestState(RequestState.ANSWERED);
                return;
            }
        }
        System.out.println(Constant.PURPLE + "a customer with this cellNumber does not exist!");
    }
}
