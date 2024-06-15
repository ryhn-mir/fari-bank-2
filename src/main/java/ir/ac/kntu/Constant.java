package ir.ac.kntu;

public class Constant {
    public static long WAGE = 500;

    private static long FARI_FARI_WAGE = 0;
    private static long FARI_ANOTHER_CART_WAGE = 300;
    private static long FARI_POLE = 2;
    private static long FARI_PAYA = 2000;
    private static long PROFIT = 10;
    private static long SIM_WAGE = 200;
    public static long mileSecond = 1000L * 3600 * 24 * 30;
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN

    public static long getWAGE() {
        return WAGE;
    }

    public static void setWAGE(long WAGE) {
        Constant.WAGE = WAGE;
    }

    public static long getFariFariWage() {
        return FARI_FARI_WAGE;
    }

    public static void setFariFariWage(long fariFariWage) {
        FARI_FARI_WAGE = fariFariWage;
    }

    public static long getFariAnotherCartWage() {
        return FARI_ANOTHER_CART_WAGE;
    }

    public static void setFariAnotherCartWage(long fariAnotherCartWage) {
        FARI_ANOTHER_CART_WAGE = fariAnotherCartWage;
    }

    public static long getFariPole() {
        return FARI_POLE;
    }

    public static void setFariPole(long fariPole) {
        FARI_POLE = fariPole;
    }

    public static long getFariPaya() {
        return FARI_PAYA;
    }

    public static void setFariPaya(long fariPaya) {
        FARI_PAYA = fariPaya;
    }

    public static long getPROFIT() {
        return PROFIT;
    }

    public static void setPROFIT(long PROFIT) {
        Constant.PROFIT = PROFIT;
    }

    public static long getSimWage() {
        return SIM_WAGE;
    }

    public static void setSimWage(long simWage) {
        SIM_WAGE = simWage;
    }
}
