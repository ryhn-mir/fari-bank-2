package ir.ac.kntu.faribank;

import ir.ac.kntu.Constant;
import ir.ac.kntu.cellphone.CellPhone;
import ir.ac.kntu.database.*;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.paya.Paya;
import ir.ac.kntu.person.Chief;
import ir.ac.kntu.person.Customer;
import ir.ac.kntu.person.Management;
import ir.ac.kntu.person.RegistrationStatus;
import ir.ac.kntu.request.Request;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FariBank {

    private Database database;
    private PayaDataBase payaDataBase;
    private BankDataBase bankDataBase;
    private AnswerRequestDatabase answerDB;
    private SimCardDataBase simCardDataBase;

    public void start() {
        initialize();
//        Customer customer1 = new Customer("a", "a", "Mm@1383", "12", "09102607040", simCardDataBase);
//        Customer customer2 = new Customer("b", "b", "Rr@1384", "13", "09112607040", simCardDataBase);
//        Customer customer3 = new Customer("c", "c", "Cc@1383", "14", "09122607040", simCardDataBase);
//
//        customer1.setStatus(RegistrationStatus.ACCEPTED);
//        customer2.setStatus(RegistrationStatus.ACCEPTED);
//        customer3.setStatus(RegistrationStatus.ACCEPTED);
//
//        database.addCustomer(customer1);
//        database.addCustomer(customer2);
//        database.addCustomer(customer3);

        Menu menu = new Menu(answerDB, database, simCardDataBase, bankDataBase, payaDataBase);
//        while (true) {
//            if (menu.mainMenu() == 99) {
//                break;
//            }
//        }
        finish();
    }

    private void finish() {
        writeAnswer();
        writeBank();
        writeChief();
        writeCustomer();
        writeManagement();
        writePaya();
        writeSimCard();
    }

    private void initialize() {
        database = new Database(readCustomer(), readManagement(), readChief());
        simCardDataBase = new SimCardDataBase(readSimCard());
        payaDataBase = new PayaDataBase(readPaya());
        bankDataBase = new BankDataBase(simCardDataBase, readBank());
        answerDB = new AnswerRequestDatabase(readAnswer());
//        database = new Database(new HashSet<>(), new HashSet<>(), new HashSet<>());
//        simCardDataBase = new SimCardDataBase(new HashSet<>());
//        payaDataBase = new PayaDataBase(new ArrayList<>());
//        bankDataBase = new BankDataBase(simCardDataBase, new HashSet<>());
//        answerDB = new AnswerRequestDatabase(new ArrayList<>());
    }

    private Set<Customer> readCustomer() {
        Set<Customer> customers = new HashSet<>();
        File file = new File("CustomerDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Customer customer = (Customer) input.readObject();
                    customers.add(customer);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return customers;
    }

    private void writeCustomer() {
        File file = new File("CustomerDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Customer customer : database.getCustomerDataBase()) {
                try {
                    output.writeObject(customer);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private Set<Management> readManagement() {
        Set<Management> managements = new HashSet<>();
        File file = new File("ManagementDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Management management = (Management) input.readObject();
                    managements.add(management);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return managements;
    }

    private void writeManagement() {
        File file = new File("ManagementDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Management management : database.getManagementDB()) {
                try {
                    output.writeObject(management);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private Set<Chief> readChief() {
        Set<Chief> chiefs = new HashSet<>();
        File file = new File("ChiefDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Chief chief = (Chief) input.readObject();
                    chiefs.add(chief);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return chiefs;
    }

    private void writeChief() {
        File file = new File("ChiefDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Chief chief : database.getChiefDB()) {
                try {
                    output.writeObject(chief);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private Set<CellPhone> readSimCard() {
        Set<CellPhone> cellPhones = new HashSet<>();
        File file = new File("CellPhoneDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    CellPhone cellPhone = (CellPhone) input.readObject();
                    cellPhones.add(cellPhone);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return cellPhones;
    }

    private void writeSimCard() {
        File file = new File("CellPhoneDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (CellPhone cellPhone : simCardDataBase.getCellPhoneList()) {
                try {
                    output.writeObject(cellPhone);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private List<Request> readAnswer() {
        List<Request> requests = new ArrayList<>();
        File file = new File("RequestDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Request request = (Request) input.readObject();
                    requests.add(request);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return requests;
    }

    private void writeAnswer() {
        File file = new File("RequestDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Request request : answerDB.getAnswer()) {
                try {
                    output.writeObject(request);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private List<Paya> readPaya() {
        List<Paya> payas = new ArrayList<>();
        File file = new File("PayaDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Paya paya = (Paya) input.readObject();
                    payas.add(paya);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return payas;
    }

    private void writePaya() {
        File file = new File("PayaDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Paya paya : payaDataBase.getPayaList()) {
                try {
                    output.writeObject(paya);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }

    private Set<Customer> readBank() {
        Set<Customer> customers = new HashSet<>();
        File file = new File("BankDataBase.txt");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream input = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Customer customer = (Customer) input.readObject();
                    customers.add(customer);
                } catch (EOFException e) {
                    break;
                } catch (Exception e) {
                    System.out.println(Constant.RED + "something went wrong");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong" + e.getMessage());
        }
        return customers;
    }

    private void writeBank() {
        File file = new File("BankDataBase.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutputStream);
            for (Customer customer : bankDataBase.getBankList()) {
                try {
                    output.writeObject(customer);
                } catch (IOException e) {
                    System.out.println(Constant.RED + "something went wrong" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(Constant.RED + "something went wrong");
        }
    }


}
