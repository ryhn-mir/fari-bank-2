package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.AnswerRequestDatabase;
import ir.ac.kntu.Request;
import ir.ac.kntu.RequestState;
import ir.ac.kntu.util.ScannerWrapper;

public class SearchMenu {
    public void printSearchMenu(AnswerRequestDatabase answerRequestDatabase) {
        System.out.println(Constant.BLUE + "choose one the the following option :");
        System.out.println(Constant.GREEN + "1.search by state");
        System.out.println(Constant.GREEN + "2.search by branch");
        System.out.println(Constant.GREEN + "3.search by user");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void searchMenu(AnswerRequestDatabase answerRequestDatabase) {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            switch (number) {
                case 1:

                    break;
                case 2:
                    BranchMenu branchMenu = new BranchMenu();
                    branchMenu.branchMenu(answerRequestDatabase);
                    break;
                case 3:
                    searchByUser(answerRequestDatabase);
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number!!");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }
    private void searchByUser(AnswerRequestDatabase answerRequestDatabase) {
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
