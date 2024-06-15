package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.UserState;

public class BlockMenu extends MainMenu {
    private Database database;

    public BlockMenu(Database database) {
        this.database = database;
    }

    private void printBlockMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.block management");
        System.out.println(Constant.GREEN + "2.block chief");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void blockMenu(Chief chief) {
        int number = 0;
        while (number != 99) {
            printBlockMenu();
            number = getNumber();
            switch (number) {
                case 1 -> blockManagement();
                case 2 -> blockChief(chief);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");

            }
        }
    }

    private void blockChief(Chief chief) {
        String userName = getUserName();
        Chief newChief = database.findChief(userName);
        if (newChief == null) {
            System.out.println(Constant.RED + "there is no chief with this userName!");
            return;
        }
        if (newChief.getPosition() < chief.getPosition()) {
            System.out.println(Constant.RED + "you can not block your parent chief");
            return;
        }
        setBlockState(newChief);
    }

    private void blockManagement() {
        String userName = getUserName();
        Management management = database.findManagement(userName);;
        if (management == null) {
            System.out.println(Constant.RED + "there is no management with this userName!");
            return;
        }
        setBlockState(management);
    }

    private void setBlockState(Object object) {
        String state = getBlockState();
        if ("y".equals(state)) {
            if (object instanceof Chief chief) {
                chief.setUserState(UserState.BLOCKED);
            } else if (object instanceof Management management) {
                management.setUserState(UserState.BLOCKED);
            }
        } else if ("n".equals(state)) {
            if (object instanceof Chief chief) {
                chief.setUserState(UserState.UNBLOCKED);
            } else if (object instanceof Management management) {
                management.setUserState(UserState.UNBLOCKED);
            }
        } else {
            System.out.println(Constant.RED + "invalid input");
        }
    }
}
