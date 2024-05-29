package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageAccountMenu extends MainMenu {

    public void printManageAccountMenu() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.increase your credit");
        System.out.println(Constant.GREEN + "2.show your account balance");
        System.out.println(Constant.GREEN + "3.show list of transaction");
        System.out.println(Constant.GREEN + "99.back");

    }

    public void manageAccountMenu(Customer customer, Database database) {
        int number = 0;
        while (number != 99) {
            try {
                printManageAccountMenu();
                number = getNumber();
                switch (number) {
                    case 1:
                        increaseBalance(customer, database);
                        break;
                    case 2:
                        showAccountBalance(customer);
                        break;
                    case 3:
//                        showListOfTransactions(customer);
                        requestTransaction(customer);
                        break;
                    case 99:
                        break;
                    default:
                        throw new RuntimeException("invalid number");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

//    private void showListOfTransactions(Customer customer) {
//        for (Transaction transaction : customer.getAccount().getTransactionDb().getTransactions()) {
//            System.out.println(Constant.PURPLE + transaction);
//        }
//    }

    private void showAccountBalance(Customer customer) {
        System.out.println(Constant.PURPLE + customer.getAccount().getBalance());
        System.out.println(Constant.PURPLE + "accountNo : " + customer.getAccount().getAccountNumber());
    }

    private void increaseBalance(Customer customer, Database database) {
        long money = getInputMoney();
        customer.getAccount().increaseCredit(money, database);
    }

    public void printRequestTransactions() {
        System.out.println(Constant.BLUE + "choose one of the following option");
        System.out.println(Constant.GREEN + "1.get transactions by number");
        System.out.println(Constant.GREEN + "2.get transactions by time");
        System.out.println(Constant.GREEN + "3.get all transactions");
        System.out.println(Constant.GREEN + "99.back");

    }

    public void requestTransaction(Customer customer) {
        int number = 0;
        while (number != 99) {
            try {
                printRequestTransactions();
                number = getNumber();
                switch (number) {
                    case 1:
                        getTransactionsByNumber(customer);
                        break;
                    case 2:
                        getTransactionsByTime(customer);
                        break;
                    case 3:
                        getAllTransactions(customer);
                        break;
                    case 99:
                        break;
                    default:
                        throw new RuntimeException("invalid number");
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void getTransactionsByNumber(Customer customer) {
        try {
            System.out.println(Constant.PURPLE + "enter the number of transactions you want");
            int size = customer.getAccount().getTransactionDb().getTransactions().size();
            if (size == 0) {
                throw new RuntimeException("there is no transactions to show!!");
            }
            int number = getNumber();
            for (int i = size - 1; i >= size - number; i--) {
                System.out.println(Constant.PURPLE + customer.getAccount().getTransactionDb().getTransactions().get(i));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void getTransactionsByTime(Customer customer) throws ParseException {
        int size = customer.getAccount().getTransactionDb().getTransactions().size();
        if (size == 0) {
            System.out.println(Constant.RED + "there is no transaction to show");
            return;
        }
        try {
            String firstNumber = getDate();
            String secondNumber = getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy / MM / dd hh : mm : ss");
            Date firstDate = simpleDateFormat.parse(firstNumber);
            Date secondDate = simpleDateFormat.parse(secondNumber);

            for (int i = size - 1; i >= 0; i--) {
                long time = customer.getAccount().getTransactionDb().getTransactions().get(i).getDate().getTime();
                if (firstDate.getTime() <= time && time <= secondDate.getTime()) {
                    System.out.println(Constant.PURPLE + customer.getAccount().getTransactionDb().getTransactions().get(i));
                }
            }
        } catch (Exception e) {
            System.out.println(Constant.RED + "invalid input!");
        }
    }

    private void getAllTransactions(Customer customer) {
        try {
            int size = customer.getAccount().getTransactionDb().getTransactions().size();
            if (size == 0) {
                throw new RuntimeException("there is no transactions to show!!");
            }
            for (int i = 0; i <= size - 1; i++) {
                System.out.println(Constant.PURPLE + customer.getAccount().getTransactionDb().getTransactions().get(i));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
