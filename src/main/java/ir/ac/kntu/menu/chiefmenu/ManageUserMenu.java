package ir.ac.kntu.menu.chiefmenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.Permission;

public class ManageUserMenu extends MainMenu {
    private Database database;

    public ManageUserMenu(Database database) {
        this.database = database;
    }

    private void printManageUserMenu() {
        System.out.println(Constant.BLUE + "choose one the following options : ");
        System.out.println(Constant.GREEN + "1.show list of user");
        System.out.println(Constant.GREEN + "2.search list of uer");
        System.out.println(Constant.GREEN + "3.add user");
        System.out.println(Constant.GREEN + "4.block user");
        System.out.println(Constant.GREEN + "5.edit user");
        System.out.println(Constant.GREEN + "6.allocate management");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void manageUserMenu(Chief chief) {
        int number = 0;
        while (number != 99) {
            printManageUserMenu();
            number = getNumber();
            switch (number) {
                case 1 -> showListOfUser();
                case 2 -> searchListOfUser();
                case 3 -> addUser(chief);
                case 4 -> blockUser(chief);
                case 5 -> editUser();
                case 6 -> allocateManagement();
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!!");
            }
        }
    }

    private void showListOfUser() {
        int count = 1;
        System.out.println(Constant.YELLOW + "chiefs : ");
        for (Chief chief : database.getChiefDB()) {
            System.out.println(Constant.PURPLE + count + "." + chief);
            count++;
        }
        System.out.println(Constant.YELLOW + "managements : ");
        for (Management management : database.getManagementDB()) {
            System.out.println(Constant.PURPLE + count + "." + management);
            count++;
        }
        System.out.println(Constant.YELLOW + "customers : ");
        for (Customer customer : database.getCustomerDataBase()) {
            System.out.println(Constant.PURPLE + count + "." + customer);
            count++;
        }
    }

    private void searchListOfUser() {
        SearchUserMenu searchUserMenu = new SearchUserMenu(database);
        searchUserMenu.searchUserMenu();
    }

    private void addUser(Chief chief) {
        AddUserMenu addUserMenu = new AddUserMenu(database);
        addUserMenu.addUserMenu(chief);
    }

    private void blockUser(Chief chief) {
        BlockMenu blockMenu = new BlockMenu(database);
        blockMenu.blockMenu(chief);
    }

    private void editUser() {
        EditUserMenu editUserMenu = new EditUserMenu(database);
        editUserMenu.editUserMenu();
    }


    private void allocateManagement() {
        database.printManagement();
        int number = getNumber();
        int count = 1;
        if (!(0 < number && number <= database.getManagementDB().size())) {
            System.out.println(Constant.RED + "number out of range");
            return;
        }
        for (Management management : database.getManagementDB()) {
            if (number == count) {
                permission(management);
            }
            count++;
        }
    }

    private void permissionVerify(Management management) {
        Permission permission = management.getPermission();
        int number = getVerifyAllocate();
        if (number == 1) {
            permission.setVerify(true);
        } else if (number == 0) {
            permission.setVerify(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionRequest(Management management) {
        Permission permission = management.getPermission();
        int number = getRequestAllocate();
        if (number == 1) {
            permission.setRequest(true);
        } else if (number == 0) {
            permission.setRequest(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionUserAccess(Management management) {
        Permission permission = management.getPermission();
        int number = getUserAccessAllocate();
        if (number == 1) {
            permission.setUserAccess(true);
        } else if (number == 0) {
            permission.setUserAccess(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionContact(Management management) {
        Permission permission = management.getPermission();
        int number = getContactAllocate();
        if (number == 1) {
            permission.setContact(true);
        } else if (number == 0) {
            permission.setContact(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionSetting(Management management) {
        Permission permission = management.getPermission();
        int number = getSettingAllocate();
        if (number == 1) {
            permission.setSetting(true);
        } else if (number == 0) {
            permission.setSetting(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionReport(Management management) {
        Permission permission = management.getPermission();
        int number = getReportAllocate();
        if (number == 1) {
            permission.setReport(true);
        } else if (number == 0) {
            permission.setReport(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionTransfer(Management management) {
        Permission permission = management.getPermission();
        int number = getTransferAllocate();
        if (number == 1) {
            permission.setTransfer(true);
        } else if (number == 0) {
            permission.setTransfer(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionUser(Management management) {
        Permission permission = management.getPermission();
        int number = getUserAllocate();
        if (number == 1) {
            permission.setUser(true);
        } else if (number == 0) {
            permission.setUser(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permissionState(Management management) {
        Permission permission = management.getPermission();
        int number = getStateAllocate();
        if (number == 1) {
            permission.setState(true);
        } else if (number == 0) {
            permission.setState(false);
        } else {
            System.out.println(Constant.RED + "invalid number");
        }
    }

    private void permission(Management management) {
        permissionVerify(management);
        permissionRequest(management);
        permissionUserAccess(management);
        permissionUser(management);
        permissionState(management);
        permissionContact(management);
        permissionTransfer(management);
        permissionReport(management);
        permissionSetting(management);
    }
}
