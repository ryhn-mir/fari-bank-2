package ir.ac.kntu.menu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.util.ScannerWrapper;

public class UserAccessMenu {
    public void printUserAccessMenu(){
        System.out.println(Constant.BLUE + "choose one of the following option : ");
        System.out.println(Constant.GREEN + "search by firstName");
        System.out.println(Constant.GREEN + "search by lastName");
        System.out.println(Constant.GREEN + "search by cellNumber");
        System.out.println(Constant.GREEN + "search by firstName and lastName");
        System.out.println(Constant.GREEN + "search by firstName and cellNumber");
        System.out.println(Constant.GREEN + "search by lastName and cellNumber");
        System.out.println(Constant.GREEN + "search by firstName and lastName and cellNumber");
        System.out.println(Constant.GREEN + "99.back");
    }
    public void userAccessMenu() {
        int number = ScannerWrapper.getInstance().nextInt();
        while (number != 99) {
            printUserAccessMenu();
            switch (number) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println(Constant.RED + "invalid number");
            }
            number = ScannerWrapper.getInstance().nextInt();
        }
    }
}
