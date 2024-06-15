package ir.ac.kntu.menu.customermenu;

import ir.ac.kntu.Constant;
import ir.ac.kntu.cellphone.CellPhone;
import ir.ac.kntu.database.BankDataBase;
import ir.ac.kntu.database.Database;
import ir.ac.kntu.database.SimCardDataBase;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.simcardtransaction.SimCardTransaction;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionKind;

public class SimCardMenu extends MainMenu {
    private Database database;
    private SimCardDataBase simCardDataBase;
    private BankDataBase bankDataBase;

    public SimCardMenu(Database database, SimCardDataBase simCardDataBase, BankDataBase bankDataBase) {
        this.database = database;
        this.simCardDataBase = simCardDataBase;
        this.bankDataBase = bankDataBase;
    }

    private void printSimCardMenu() {
        System.out.println(Constant.BLUE + "choose one of the following options : ");
        System.out.println(Constant.GREEN + "1.by contact");
        System.out.println(Constant.GREEN + "2.by cellNumber");
        System.out.println(Constant.GREEN + "3.to yourself");
        System.out.println(Constant.GREEN + "5.show recent simCard transaction");
        System.out.println(Constant.GREEN + "4.show charge");
        System.out.println(Constant.GREEN + "99.back");
    }

    public void simCardMenu(Customer customer) {
        int number = 0;
        while (number != 99) {
            printSimCardMenu();
            number = getNumber();
            switch (number) {
                case 1 -> byContact(customer);
                case 2 -> byCellNumber(customer);
                case 3 -> toYourself(customer);
                case 4 -> showCharge(customer);
                case 5 -> showSimCardTransaction(customer);
                case 99 -> {
                    return;
                }
                default -> System.out.println(Constant.RED + "invalid number!");
            }
        }
    }

    private void showSimCardTransaction(Customer customer) {
        customer.getSimTransactionDataBase().printTransactions();
    }

    private void showCharge(Customer customer) {
        System.out.println(Constant.PURPLE + customer.getCellPhone().getCharge());
    }

    private void byContact(Customer customer) {
        int count = 1;
        for (Customer cust : customer.getContactDatabase().getContactList()) {
            System.out.println(Constant.PURPLE + count + "." + cust.getFirstName() + " " + cust.getLastName());
            count++;
        }
        int number = getNumber();
        count = 1;
        for (Customer cust : customer.getContactDatabase().getContactList()) {
            if (number == count) {
                long charge = getCharge();
                if (charge + Constant.getSimWage() > customer.getAccount().getBalance()) {
                    System.out.println(Constant.RED + "your balance is not enough!");
                    return;
                }
                customer.getAccount().withdraw(charge + Constant.getSimWage());
                Customer contact = database.findCustomer(cust.getCellNumber());
                contact.getCellPhone().increaseCharge(charge);
                SimCardTransaction simCardTransaction = new SimCardTransaction(contact.getFirstName(), contact.getLastName(), contact.getCellNumber(), charge);
                customer.getSimTransactionDataBase().addSim(simCardTransaction);
            }
            count ++;
        }
    }

    private void byCellNumber(Customer customer) {
        String cellNumber = getCellNumber();
        CellPhone cellPhone = simCardDataBase.findCellPhone(cellNumber);
        if (cellPhone == null) {
            System.out.println(Constant.RED + "the cellNumber does not exist");
            return;
        }
        long charge = getCharge();
        if (charge + Constant.getSimWage() > customer.getAccount().getBalance()) {
            System.out.println(Constant.RED + "your balance is not enough!");
            return;
        }
        customer.getAccount().withdraw(charge + Constant.getSimWage());
        cellPhone.increaseCharge(charge);
        Customer cust = bankDataBase.findCustByPhone(cellNumber);
        SimCardTransaction simCardTransaction = new SimCardTransaction(cust.getFirstName(), cust.getLastName(), cust.getCellNumber(), charge);
        customer.getSimTransactionDataBase().addSim(simCardTransaction);
    }

    private void toYourself(Customer customer) {
        long charge = getCharge();
        if (charge + Constant.getSimWage() > customer.getAccount().getBalance()) {
            System.out.println(Constant.RED + "your balance is not enough!");
            return;
        }
        customer.getAccount().withdraw(charge + Constant.getSimWage());
        customer.getCellPhone().increaseCharge(charge);
        SimCardTransaction simCardTransaction = new SimCardTransaction(customer.getFirstName(), customer.getLastName(), customer.getCellNumber(), charge);
        customer.getSimTransactionDataBase().addSim(simCardTransaction);
    }
}
