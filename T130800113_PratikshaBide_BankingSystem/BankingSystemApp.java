import java.time.LocalDateTime;
import java.util.*;

// ========================= ENTITIES =========================
class Customer {
    private int customerID;
    private String name;
    private String address;
    private String contact;

    public Customer(int customerID, String name, String address, String contact) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + ", Name: " + name + ", Address: " + address + ", Contact: " + contact;
    }
}

class Account {
    private int accountID;
    private int customerID;
    private String type; // Saving / Current
    private double balance;

    public Account(int accountID, int customerID, String type, double balance) {
        this.accountID = accountID;
        this.customerID = customerID;
        this.type = type;
        this.balance = balance;
    }

    public int getAccountID() { return accountID; }
    public int getCustomerID() { return customerID; }
    public String getType() { return type; }
    public double getBalance() { return balance; }

    public void setAccountID(int accountID) { this.accountID = accountID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setType(String type) { this.type = type; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account ID: " + accountID + ", Customer ID: " + customerID + ", Type: " + type + ", Balance: " + balance;
    }
}

class Transaction {
    private int transactionID;
    private int accountID;
    private String type; // Deposit / Withdrawal / Transfer-In / Transfer-Out
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(int transactionID, int accountID, String type, double amount, LocalDateTime timestamp) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Specified constructor signature in case study
    public Transaction(int accountID, String type, double amount) {
        this(0, accountID, type, amount, LocalDateTime.now());
    }

    public int getTransactionID() { return transactionID; }
    public int getAccountID() { return accountID; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
    public void setAccountID(int accountID) { this.accountID = accountID; }
    public void setType(String type) { this.type = type; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionID + ", Account ID: " + accountID + ", Type: " + type + ", Amount: " + amount + ", Timestamp: " + timestamp;
    }
}

class Beneficiary {
    private int beneficiaryID;
    private int customerID;
    private String name;
    private String accountNumber;
    private String bankDetails;

    public Beneficiary(int beneficiaryID, int customerID, String name, String accountNumber, String bankDetails) {
        this.beneficiaryID = beneficiaryID;
        this.customerID = customerID;
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankDetails = bankDetails;
    }

    public int getBeneficiaryID() { return beneficiaryID; }
    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getAccountNumber() { return accountNumber; }
    public String getBankDetails() { return bankDetails; }

    public void setBeneficiaryID(int beneficiaryID) { this.beneficiaryID = beneficiaryID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setName(String name) { this.name = name; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public void setBankDetails(String bankDetails) { this.bankDetails = bankDetails; }

    @Override
    public String toString() {
        return "Beneficiary ID: " + beneficiaryID + ", Customer ID: " + customerID + ", Name: " + name + ", Account No.: " + accountNumber + ", Bank: " + bankDetails;
    }
}

// ========================= SERVICE LAYER =========================
interface BankingService {
    void addCustomer(Customer customer);
    void addAccount(Account account);
    void addTransaction(Transaction transaction); // deposit/withdraw will be modeled here
    void addBeneficiary(Beneficiary beneficiary);

    Customer findCustomerById(int id);
    Account findAccountById(int id);
    Transaction findTransactionById(int id);
    Beneficiary findBeneficiaryById(int id);

    List<Account> getAccountsByCustomerId(int customerId);
    List<Transaction> getTransactionsByAccountId(int accountId);
    List<Beneficiary> getBeneficiariesByCustomerId(int customerId);

    Collection<Account> getAllAccounts();
    Collection<Customer> getAllCustomers();
    Collection<Transaction> getAllTransactions();
    Collection<Beneficiary> getAllBeneficiaries();

    // Extra helpers to satisfy "money transfer"
    boolean deposit(int accountId, double amount);
    boolean withdraw(int accountId, double amount);
    boolean transfer(int fromAccountId, int toAccountId, double amount);
}

class BankingServiceImpl implements BankingService {
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Account> accounts = new HashMap<>();
    private Map<Integer, Transaction> transactions = new HashMap<>();
    private Map<Integer, Beneficiary> beneficiaries = new HashMap<>();

    private int nextTransactionId = 1;

    @Override
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }

    @Override
    public void addAccount(Account account) {
        if (!customers.containsKey(account.getCustomerID())) {
            throw new IllegalArgumentException("Customer ID " + account.getCustomerID() + " does not exist.");
        }
        accounts.put(account.getAccountID(), account);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        // assign ID and timestamp if needed
        if (transaction.getTransactionID() == 0) {
            transaction.setTransactionID(nextTransactionId++);
        }
        if (transaction.getTimestamp() == null) {
            transaction.setTimestamp(LocalDateTime.now());
        }
        transactions.put(transaction.getTransactionID(), transaction);

        // update balance based on type
        Account acc = accounts.get(transaction.getAccountID());
        if (acc == null) {
            throw new IllegalArgumentException("Account ID " + transaction.getAccountID() + " does not exist.");
        }
        switch (transaction.getType().toLowerCase()) {
            case "deposit":
            case "transfer-in":
                acc.setBalance(acc.getBalance() + transaction.getAmount());
                break;
            case "withdrawal":
            case "transfer-out":
                acc.setBalance(acc.getBalance() - transaction.getAmount());
                break;
            default:
                // ignore unknown type
                break;
        }
    }

    @Override
    public void addBeneficiary(Beneficiary beneficiary) {
        if (!customers.containsKey(beneficiary.getCustomerID())) {
            throw new IllegalArgumentException("Customer ID " + beneficiary.getCustomerID() + " does not exist.");
        }
        beneficiaries.put(beneficiary.getBeneficiaryID(), beneficiary);
    }

    @Override
    public Customer findCustomerById(int id) { return customers.get(id); }

    @Override
    public Account findAccountById(int id) { return accounts.get(id); }

    @Override
    public Transaction findTransactionById(int id) { return transactions.get(id); }

    @Override
    public Beneficiary findBeneficiaryById(int id) { return beneficiaries.get(id); }

    @Override
    public List<Account> getAccountsByCustomerId(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account a : accounts.values()) {
            if (a.getCustomerID() == customerId) result.add(a);
        }
        return result;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            if (t.getAccountID() == accountId) result.add(t);
        }
        result.sort(Comparator.comparing(Transaction::getTimestamp)); // chronological
        return result;
    }

    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary b : beneficiaries.values()) {
            if (b.getCustomerID() == customerId) result.add(b);
        }
        return result;
    }

    @Override
    public Collection<Account> getAllAccounts() { return accounts.values(); }

    @Override
    public Collection<Customer> getAllCustomers() { return customers.values(); }

    @Override
    public Collection<Transaction> getAllTransactions() { return transactions.values(); }

    @Override
    public Collection<Beneficiary> getAllBeneficiaries() { return beneficiaries.values(); }

    @Override
    public boolean deposit(int accountId, double amount) {
        if (amount <= 0) return false;
        Account acc = accounts.get(accountId);
        if (acc == null) return false;
        addTransaction(new Transaction(0, accountId, "Deposit", amount, LocalDateTime.now()));
        return true;
    }

    @Override
    public boolean withdraw(int accountId, double amount) {
        if (amount <= 0) return false;
        Account acc = accounts.get(accountId);
        if (acc == null) return false;
        if (acc.getBalance() < amount) return false;
        addTransaction(new Transaction(0, accountId, "Withdrawal", amount, LocalDateTime.now()));
        return true;
    }

    @Override
    public boolean transfer(int fromAccountId, int toAccountId, double amount) {
        if (amount <= 0) return false;
        Account from = accounts.get(fromAccountId);
        Account to = accounts.get(toAccountId);
        if (from == null || to == null) return false;
        if (from.getBalance() < amount) return false;

        // Record as two transactions to satisfy many-to-many via operations
        addTransaction(new Transaction(0, fromAccountId, "Transfer-Out", amount, LocalDateTime.now()));
        addTransaction(new Transaction(0, toAccountId, "Transfer-In", amount, LocalDateTime.now()));
        return true;
    }
}

// ========================= DRIVER / MENU =========================
public class BankingSystemApp {
    private static final Scanner sc = new Scanner(System.in);
    private final BankingService service = new BankingServiceImpl();

    public static void main(String[] args) {
        new BankingSystemApp().run();
    }

    private void run() {
        while (true) {
            System.out.println();
            System.out.println("Banking System");
            System.out.println("1. Add Customers");
            System.out.println("2. Add Accounts");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Add Transaction (Deposit/Withdrawal)");
            System.out.println("5. Find Customer by Id");
            System.out.println("6. List all Accounts of specific Customer");
            System.out.println("7. List all transactions of specific Account");
            System.out.println("8. List all beneficiaries of specific customer");
            System.out.println("9. Transfer Money");
            System.out.println("10. Exit");
            System.out.print("Enter your choice : ");

            int choice = readInt();
            switch (choice) {
                case 1: addCustomerFlow(); break;
                case 2: addAccountFlow(); break;
                case 3: addBeneficiaryFlow(); break;
                case 4: addTransactionFlow(); break;
                case 5: findCustomerFlow(); break;
                case 6: listAccountsOfCustomerFlow(); break;
                case 7: listTransactionsOfAccountFlow(); break;
                case 8: listBeneficiariesOfCustomerFlow(); break;
                case 9: transferMoneyFlow(); break;
                case 10:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addCustomerFlow() {
        System.out.println("\nEnter Customer Details");
        System.out.print("Customer Id : ");
        int id = readInt();
        System.out.print("Name : ");
        String name = readLine();
        System.out.print("Address : ");
        String addr = readLine();
        System.out.print("Contact No. : ");
        String contact = readLine();
        service.addCustomer(new Customer(id, name, addr, contact));
    }

    private void addAccountFlow() {
        System.out.println("\nEnter Account Details");
        System.out.print("Account Id : ");
        int accId = readInt();
        System.out.print("Customer Id : ");
        int custId = readInt();
        System.out.print("Account Type Saving/ Current : ");
        String type = readLine();
        System.out.print("Balance : ");
        double bal = readDouble();

        try {
            service.addAccount(new Account(accId, custId, type, bal));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void addBeneficiaryFlow() {
        System.out.println("\nEnter Beneficiary Details");
        System.out.print("Customer Id : ");
        int custId = readInt();
        System.out.print("Beneficiary Id : ");
        int benId = readInt();
        System.out.print("Beneficiary Name : ");
        String name = readLine();
        System.out.print("Beneficiary Account No. : ");
        String accNo = readLine();
        System.out.print("Beneficiary Bank details : ");
        String bank = readLine();

        try {
            service.addBeneficiary(new Beneficiary(benId, custId, name, accNo, bank));
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void addTransactionFlow() {
        System.out.println("\nEnter Transaction Details");
        System.out.print("Account Id : ");
        int accId = readInt();
        System.out.print("Type (Deposit/Withdrawal) : ");
        String type = readLine().trim();
        System.out.print("Amount : ");
        double amount = readDouble();

        boolean ok = false;
        if (type.equalsIgnoreCase("Deposit")) {
            ok = service.deposit(accId, amount);
        } else if (type.equalsIgnoreCase("Withdrawal")) {
            ok = service.withdraw(accId, amount);
        } else {
            System.out.println("Invalid type.");
            return;
        }

        if (ok) System.out.println("Transaction successful.");
        else System.out.println("Transaction failed. Check account and balance.");
    }

    private void findCustomerFlow() {
        // Show all, then prompt for one (matches sample flow)
        for (Customer c : service.getAllCustomers()) {
            System.out.println("Cutomer ID: " + c.getCustomerID() + ", Name: " + c.getName());
        }
        System.out.print("\nCustomer Id : ");
        int id = readInt();
        Customer c = service.findCustomerById(id);
        if (c == null) System.out.println("Customer not found.");
        else {
            System.out.println("\nCustomer: " + c.getName());
        }
    }

    private void listAccountsOfCustomerFlow() {
        // Show all first (as in sample)
        for (Account a : service.getAllAccounts()) {
            System.out.println("Account ID: " + a.getAccountID() + ", Customer ID : " + a.getCustomerID() + ", Balance: " + a.getBalance());
        }
        System.out.print("\nCustomer Id : ");
        int id = readInt();
        System.out.println("\nAccounts for Customer ID :" + id);
        List<Account> list = service.getAccountsByCustomerId(id);
        if (list.isEmpty()) System.out.println("(none)");
        for (Account a : list) {
            System.out.println("Account ID: " + a.getAccountID() + ", Balance: " + a.getBalance());
        }
    }

    private void listTransactionsOfAccountFlow() {
        System.out.print("\nAccount Id : ");
        int id = readInt();
        System.out.println("\nTransactions for Account ID :" + id);
        List<Transaction> list = service.getTransactionsByAccountId(id);
        if (list.isEmpty()) System.out.println("(none)");
        for (Transaction t : list) {
            System.out.println(t);
        }
    }

    private void listBeneficiariesOfCustomerFlow() {
        System.out.print("\nCustomer Id : ");
        int id = readInt();
        System.out.println("\nBeneficiaries for Customer ID :" + id);
        List<Beneficiary> list = service.getBeneficiariesByCustomerId(id);
        if (list.isEmpty()) System.out.println("(none)");
        for (Beneficiary b : list) {
            System.out.println("Beneficiary ID: " + b.getBeneficiaryID() + ", Name: " + b.getName());
        }
    }

    private void transferMoneyFlow() {
        System.out.println("\nTransfer Money");
        System.out.print("From Account Id : ");
        int from = readInt();
        System.out.print("To Account Id : ");
        int to = readInt();
        System.out.print("Amount : ");
        double amt = readDouble();

        boolean ok = service.transfer(from, to, amt);
        if (ok) System.out.println("Transfer successful.");
        else System.out.println("Transfer failed. Check accounts and balance.");
    }

    // ------------------ IO Helpers ------------------
    private static int readInt() {
        while (true) {
            String s = sc.nextLine();
            try { return Integer.parseInt(s.trim()); }
            catch (Exception e) { System.out.print("Please enter a valid integer: "); }
        }
    }

    private static double readDouble() {
        while (true) {
            String s = sc.nextLine();
            try { return Double.parseDouble(s.trim()); }
            catch (Exception e) { System.out.print("Please enter a valid number: "); }
        }
    }

    private static String readLine() {
        String s = sc.nextLine();
        return s == null ? "" : s.trim();
    }
}
