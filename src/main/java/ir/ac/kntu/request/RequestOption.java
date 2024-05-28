package ir.ac.kntu.request;

import ir.ac.kntu.Constant;

public enum RequestOption {
    CONTACT,
    SETTING,
    TRANSFER,
    REPORT;

    public static void print() {
        System.out.println(Constant.BLUE + "choose one the following option : ");
        System.out.println(Constant.GREEN + "1.contact");
        System.out.println(Constant.GREEN + "2.setting");
        System.out.println(Constant.GREEN + "3.transfer");
        System.out.println(Constant.GREEN + "4.report");
        System.out.println(Constant.GREEN + "99.back");
    }
}
