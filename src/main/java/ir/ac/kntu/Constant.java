package ir.ac.kntu;

public class Constant {

    private static long fari = 0;
    private static long otherCart = 300;
    private static long fariPole = 2;
    private static long fariPaya = 2000;
    private static long profit = 10;
    private static long sim = 200;

    public static final int VALUE = 3;
    private static long mileSecond = 1000L * 3600 * 24 * 30;
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN


    public static long getFari() {
        return fari;
    }

    public static void setFari(long fari) {
        Constant.fari = fari;
    }

    public static long getOtherCart() {
        return otherCart;
    }

    public static void setOtherCart(long otherCart) {
        Constant.otherCart = otherCart;
    }

    public static long getFariPole() {
        return fariPole;
    }

    public static void setFariPole(long fariPole) {
        Constant.fariPole = fariPole;
    }

    public static long getFariPaya() {
        return fariPaya;
    }

    public static void setFariPaya(long fariPaya) {
        Constant.fariPaya = fariPaya;
    }

    public static long getProfit() {
        return profit;
    }

    public static void setProfit(long profit) {
        Constant.profit = profit;
    }

    public static long getSim() {
        return sim;
    }

    public static void setSim(long sim) {
        Constant.sim = sim;
    }

    public static long getMileSecond() {
        return mileSecond;
    }

    public static void setMileSecond(long mileSecond) {
        Constant.mileSecond = mileSecond;
    }
}
