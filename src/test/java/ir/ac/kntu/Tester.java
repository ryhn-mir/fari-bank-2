package ir.ac.kntu;

import ir.ac.kntu.database.*;
import ir.ac.kntu.fund.Fund;
import ir.ac.kntu.fund.FundKind;
import ir.ac.kntu.paya.Paya;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.request.Request;
import ir.ac.kntu.request.RequestOption;
import ir.ac.kntu.simcardtransaction.SimCardTransaction;
import ir.ac.kntu.transaction.Transaction;
import ir.ac.kntu.transaction.TransactionKind;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {

    @Test
    void checkManagementDB() {
        Management management = new Management("ali", "alavi", "Aa@1384", "ali123");
        Database database = new Database(new HashSet<>(), new HashSet<>(), new HashSet<>());
        database.getManagementDB().add(management);
        assertEquals(management, database.findManagement("ali123"));
    }

    @Test
    void checkCustomerDB() {
        Customer customer = new Customer("ali", "alavi", "Aa@1384", "12", "09172607040", new SimCardDataBase(new HashSet<>()));
        Database database = new Database(new HashSet<>(), new HashSet<>(), new HashSet<>());
        database.getCustomerDataBase().add(customer);
        assertEquals(customer, database.findCustomer("09172607040"));
    }

    @Test
    void checkTransactionDB() {
        Transaction transaction = new Transaction("ali", "alavi", "123456789", "123456798", TransactionKind.TRANSFER_MONEY);
        TransactionDb transactionDb = new TransactionDb();
        transactionDb.addTransaction(transaction);
        assertEquals(1, transactionDb.getTransactions().size());
    }

    @Test
    void checkFundDB() {
        Fund fund = new Fund("saving",300, FundKind.SAVING);
        FundDataBase fundDataBase = new FundDataBase();
        fundDataBase.addFund(fund);
        assertEquals(fund, fundDataBase.findFund("saving"));
    }

    @Test
    void checkSimTransactionDB() {
        SimCardTransaction simCardTransaction = new SimCardTransaction("ali", "alavi", "09172607040", (long)12);
        SimTransactionDataBase simTransactionDataBase = new SimTransactionDataBase();
        simTransactionDataBase.addSim(simCardTransaction);
        assertEquals(1, simTransactionDataBase.getSimTrans().size());
    }

    @Test
    void checkPayaDB(){
        Customer customer = new Customer("ali", "alavi", "Aa@1384", "12", "09172607040", new SimCardDataBase(new HashSet<>()));
        Customer customer1 = new Customer("alireza", "alavi", "Aa@1383", "13", "09182607040", new SimCardDataBase(new HashSet<>()));
        Paya paya = new Paya(customer, customer1, 1000);
        PayaDataBase payaDataBase = new PayaDataBase(new ArrayList<>());
        payaDataBase.getPayaList().add(paya);
        assertEquals(1, payaDataBase.getPayaList().size());
    }

    @Test
    void checkRequestDB() {
        Request request = new Request("salam", RequestOption.CONTACT, "09102607040");
        Request request1 = new Request("salam", RequestOption.SETTING, "09102607040");
        Request request2 = new Request("salam", RequestOption.TRANSFER, "09112607040");
        RequestDatabase requestDatabase = new RequestDatabase();
        requestDatabase.getRequestList().add(request);
        requestDatabase.getRequestList().add(request1);
        requestDatabase.getRequestList().add(request2);
        assertEquals(3, requestDatabase.getRequestList().size());
    }

    @Test
    void checkBankDB() {
        Customer customer = new Customer("ali", "alavi", "Aa@1384", "12", "09172607040", new SimCardDataBase(new HashSet<>()));
        BankDataBase bankDataBase = new BankDataBase(new  SimCardDataBase(new HashSet<>()), new HashSet<>());
        bankDataBase.getBankList().add(customer);
        assertEquals(customer, bankDataBase.findCustByPhone("09172607040"));
    }

    @Test
    void checkAnswerRequestDB() {
        Request request = new Request("salam", RequestOption.CONTACT, "09102607040");
        Request request1 = new Request("salam??", RequestOption.SETTING, "09112607040");
        Request request2 = new Request("salam!!", RequestOption.TRANSFER, "09152607040");
        AnswerRequestDatabase database = new AnswerRequestDatabase(new ArrayList<>());
        database.add(request);
        database.add(request1);
        database.add(request2);
        assertEquals(3, database.getAnswer().size());
    }

    @Test
    void checkContactDB() {
        Customer customer = new Customer("ali", "alavi", "Aa@1384", "12", "09172607040", new SimCardDataBase(new HashSet<>()));
        Customer customer1 = new Customer("alireza", "alavi", "Aa@1383", "13", "09182607040", new SimCardDataBase(new HashSet<>()));
        ContactDatabase contactDatabase = new ContactDatabase();
        contactDatabase.getContactList().add(customer);
        customer1.getContactDatabase().addContact(customer);
        assertEquals(1, customer1.getContactDatabase().getContactList().size());
    }

}