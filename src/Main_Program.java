import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
public class Main_Program {
    static ArrayList<Bank> Banks = new ArrayList<>();
    public static int CustomerIndex;
    public static int BankIndex;
    public static void PopulateBanks(){
        try {
            File f = new File("Banks.txt");
            Scanner myReader = new Scanner(f);
            BankIndex = Integer.parseInt(myReader.nextLine());
            while (myReader.hasNextLine()) {
                String bank = myReader.nextLine();
                String[] bankparts = bank.split("/");
                Banks.add(new Bank(Integer.parseInt(bankparts[0]),Integer.parseInt(bankparts[1]),Integer.parseInt(bankparts[2]),bankparts[3],Integer.parseInt(bankparts[4])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {                                         //If database is not discovered
            System.out.println("Banks database missing, creating new empty database");
        }
    }
    static ArrayList<Customer> Users = new ArrayList<>();
    public static void PopulateUsers(){
        try {
            File f = new File("Customers.txt");
            Scanner myReader = new Scanner(f);
            CustomerIndex = Integer.parseInt(myReader.nextLine());
            while (myReader.hasNextLine()) {
                String user = myReader.nextLine();
                String[] userparts = user.split("/");
                userparts[3]=userparts[3].replace(" BST","");
                userparts[3]=userparts[3].replace(" GMT","");
                String[] addresses = userparts[5].split(";");
                Address[] home = new Address[addresses.length];
                for (int i = 0;i<addresses.length;i++) {
                    String[] temp = addresses[i].split("_");
                    home[i] = new Address(temp[0],temp[1],temp[2],temp[3],temp[4]);
                }
                Users.add(new Customer(Integer.parseInt(userparts[0]),userparts[1],userparts[2],userparts[3],userparts[4], home));
            }
            myReader.close();
        } catch (FileNotFoundException e) {                                         //If database is not discovered
            System.out.println("Customers database missing, creating new empty database");
        }
    }
    static ArrayList<Business> BusinessAccounts = new ArrayList<>();
    public static void PopulateBusinessAccounts(){
        try {
            File f = new File("BusinessAccounts.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String businessaccount = myReader.nextLine();
                String[] businessparts = businessaccount.split("/");
                BusinessAccounts.add(new Business(Integer.parseInt(businessparts[0]),businessparts[1],Integer.parseInt(businessparts[2]),Integer.parseInt(businessparts[3]),businessparts[4],Integer.parseInt(businessparts[5]),Integer.parseInt(businessparts[6])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {                                         //If database is not discovered
            System.out.println("Business Accounts database missing, creating new empty database");
        }
    }
    static ArrayList<ISA> ISAAccounts = new ArrayList<>();
    public static void PopulateISAAccounts(){
        try {
            File f = new File("ISAAccounts.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String isaaccount = myReader.nextLine();
                String[] isaparts = isaaccount.split("/");
                ISAAccounts.add(new ISA(Integer.parseInt(isaparts[0]),isaparts[1],Integer.parseInt(isaparts[2]),Integer.parseInt(isaparts[3]),isaparts[4],Integer.parseInt(isaparts[5]),Integer.parseInt(isaparts[6])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {                                         //If database is not discovered
            System.out.println("ISA Accounts database missing, creating new empty database");
        }
    }
    static ArrayList<Current> CurrentAccounts = new ArrayList<>();
    public static Customer LookupCustomer(int index) {
        for(Customer i:Users){
            if(index==i.getIndex()){
                return i;
            }
        }
        return null;
    }
    public static void PopulateCurrentAccounts(){
        try {
            File f = new File("CurrentAccounts.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String currentaccount = myReader.nextLine();
                String[] currentparts = currentaccount.split("/");
                CurrentAccounts.add(new Current(Integer.parseInt(currentparts[0]),currentparts[1],Integer.parseInt(currentparts[2]),Integer.parseInt(currentparts[3]),currentparts[4],Integer.parseInt(currentparts[5])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {                                         //If database is not discovered
            System.out.println("Current Accounts database missing, creating new empty database");
        }
    }
    static String Username;
    private static final SimpleDateFormat Year = new SimpleDateFormat("yyyy");
    private static void AnnualTick(){
        Timestamp CurrentTime = new Timestamp(System.currentTimeMillis());
        String LastAccessedYear = Year.format(CurrentTime);
        try {
            File LogData = new File("LastAccessed.txt");
            Scanner myReader = new Scanner(LogData);
            LastAccessedYear = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Last accessed file is missing, Using current year as default");
        }
        int currentyear = Integer.parseInt(Year.format(CurrentTime));
        int difference = currentyear - Integer.parseInt(LastAccessedYear);
        //Call for interest add function in ISA
        //Call for business annual charge  function
        //Reset current annual deposit of all ISA accounts to zero

        try {
            FileWriter myWriter = new FileWriter("LastAccessed.txt");
            myWriter.write(Integer.toString(currentyear));
            myWriter.close();
        } catch (IOException g) {
            System.out.println("Error occurred with writing");
            g.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        PopulateBanks();
        Username = Authentication.Login();
        boolean Exit = Username.equals("");
        PopulateUsers();
        PopulateBusinessAccounts();
        PopulateCurrentAccounts();
        PopulateISAAccounts();
        AnnualTick();
        while(!Exit){
            System.out.println("\n\nHello " + Username + """
                                    
                    Welcome to BankApp
                    Choose an operation by entering 1, 2, or 3:
                                    
                    1: Enroll Customer
                                    
                    2: Manage Customer and Customer Assets
                                    
                    3: Exit
                    """);
            Scanner in = new Scanner(System.in);
            String answer =  in.nextLine();
            switch (answer) {
                case "1" -> CreateCustomer();
                case "2" -> ManageCustomer();
                case "3" -> Exit = true;
                default -> System.out.println("Please choose either option 1, 2 or 3");
            }
            Storage.SaveData("Customers");
        }
    }

    public static Bank FindBank(String BankName){
        for(Bank i:Banks){
            if(BankName.equals(i.getName())){
                return i;
            }
        }
        return new Bank(0,0,0,"",0);
    }
    public static Bank FindBank(int index){
        for(Bank i:Banks){
            if(index==i.getIndex()){
                return i;
            }
        }
        return new Bank(0,0,0,"",0);
    }

    //For future use: FindBusinessAccount will find the business account object related to a bank account object

    public static Business FindBusinessAccount(Bank_Accounts account){
        for (Business businessAccount : BusinessAccounts) {
            if (businessAccount.getAccount().equals(account)) {
                return businessAccount;
            }
        }
        System.out.println("Error, Could not find this Business account");
        return null;
    }

    //FindISAAccount: Finds the ISA account object related to a bank account object
    public static ISA FindISAAccount(Bank_Accounts account){
        for (ISA i : ISAAccounts) {
            if (i.getAccount().getBankNumber() == account.getBankNumber()) {
                return i;
            }
        }
        System.out.println("Error, Could not find this ISA account");
        return null;
    }

    //For future use: FindCurrentAccount will find the current account object related to a bank account object

    public static Current FindCurrentAccount(Bank_Accounts account){
        for (Current currentAccount : CurrentAccounts) {
            if (currentAccount.getAccount().equals(account)) {
                return currentAccount;
            }
        }
        System.out.println("Error, Could not find this Current account");
        return null;
    }
    public static Bank_Accounts[] FindAccount() {
        Bank_Accounts[] Accounts = new Bank_Accounts[CurrentAccounts.size() + BusinessAccounts.size() + ISAAccounts.size()];
        int index = 0;
        for (Business businessAccount : BusinessAccounts) {
            Accounts[index] = businessAccount;
            index++;
        }
        for (Current currentAccount : CurrentAccounts) {
            Accounts[index] = currentAccount;
            index++;
        }
        for (ISA isaAccount : ISAAccounts) {
            Accounts[index] = isaAccount;
            index++;
        }
        return Accounts;
    }
    //For finding bank accounts from the account number and sort code
    public static Bank_Accounts FindBankAccount(int AccountNum, int SortCode){
        for (Bank_Accounts i : FindAccount()){
            if(i.getBankNumber() == AccountNum & ((FindBank(i.getBank()).getCurrentSortCode() == SortCode) || (FindBank(i.getBank()).getISASortCode() == SortCode) || (FindBank(i.getBank()).getBusinessSortCode() == SortCode))){
                return i;
            }
        }
        System.out.println("Error: Could not find bank account with these numbers");
        return null;
    }
    public static void CreateCustomer() {
        //Staff enters details of a new customer
        Scanner in = new Scanner(System.in);
        System.out.println("\nPlease enter the new customer's details:\nFull Name:");

        String name = in.nextLine();
        boolean datecheck = false;
        String DOBString;
        do{
            System.out.println("\nDate of Birth (dd mm yyyy):");
            DOBString = in.nextLine();
            DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
            //Date and time is a nightmare. Calendar type is different from Date type yet both represent a date
            //also, most features of Date are being depreciated so Calendar is used here
            Calendar DOB = Calendar.getInstance();
            try {
                DOB.setTime(formatter.parse(DOBString));
                datecheck=true;
            } catch (ParseException e) {
                System.out.println("Please enter Date of Birth in format \"dd mm yyyy\"");

            }
        } while (!datecheck);


        in = new Scanner(System.in);
        System.out.println("\nPhone Number:");
        String Phone = in.nextLine();
        //Optional mobile number requires a string to feed into an integer so leaving it blank is possible
        System.out.println("\nMobile Number (Optional: Leave blank if none):");
        String Mobile = in.nextLine();

        System.out.println("""
                Address:
                We need:
                        Your current address if you have lived there for the last 3 years
                        Your previous address if you have changed address in the last 3 years
                        Your next most recent address if you lived at your previous 2 addresses for less than 3 years combined""");
        //gets all 3 addresses from the last 3 years, counting tenancy to the nearest month
        int months = 0;
        int index = 0;
        ArrayList<Address> temphome = new ArrayList<>();

        do{
            in = new Scanner(System.in);
            System.out.println("For Address #"+(index+1)+"\n  House Name/Number:");
            String House = in.nextLine();
            System.out.println("  Road:");
            String Road = in.nextLine();
            System.out.println("  Town/Village/City:");
            String Town = in.nextLine();
            System.out.println("  County/Province:");
            String County = in.nextLine();
            System.out.println("  PostCode/ZipCode(no gaps):");
            String Postcode = in.nextLine();
            temphome.add(new Address(House,Road,Town,County,Postcode));
            System.out.println("How many months has the customer lived at this address? (to the closest month rounded up)");
            while (!in.hasNextInt()) {
                System.out.println("Please enter number of months in numerical form, rounded up to nearest month");
                in.next();
            }
            int addmonths = in.nextInt();
            months=months+addmonths;
            index += 1;
        }while(months<36&index<3);
        Address[] home = new Address[temphome.size()];
        for (int i = 0;i<temphome.size();i++) {
            home[i] = temphome.get(i);
        }
        //Checks if the current year's birthday has happened - needs to be removed currently
        /*Calendar Birthday = DOB;
        Birthday.set(Calendar.YEAR,LocalDate.now().getYear());
        int addyear = 0;
        if(Birthday.getTimeInMillis() > System.currentTimeMillis()){
            addyear = -1;
        }*/
        //Creates a new customer with all the info (getting an age based upon current time is a nightmare)
        Users.add(new Customer(CustomerIndex,name, DOBString,Phone,Mobile,home));
        CustomerIndex++;
        System.out.println("Customer "+name+" Enrolled");
    }

    public static void ManageCustomer() throws IOException {
        System.out.println("Enter the Customer's name:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Enter your Current Post code (e.g AA1 1AA");
        String postcode = in.nextLine();
        System.out.println("Enter your house name/number");
        String hname = in.nextLine();
        boolean validUser = false;
        for (Customer i : Users) {
            if (i.getName().equalsIgnoreCase(name) & i.getAddress()[0].getPostCode().equalsIgnoreCase(postcode) & i.getAddress()[0].getHouse().equalsIgnoreCase(hname)) {
                validUser = true;
                boolean MenuStay = true;
                do {
                    System.out.println("""
                                Choose a customer operation:
                                1:    Create bank account
                                2:    Manage bank accounts
                                3:    Change customer details
                                4:    Remove customer
                                5:    Exit to Main Menu""");
                    String choice = in.nextLine();
                    switch (choice) {
                        case "1" -> {
                            i.CreateBankAccount();
                            Storage.SaveData("Accounts");
                        }
                        case "2" -> ManageAccount(i.getIndex());
                        case "3" -> ChangeCustomerDetails(i);
                        case "4" -> {
                            i.removeCustomer();
                            Users.remove(i);
                            Storage.SaveData("Customers");
                        }
                        case "5" -> MenuStay = false;
                        default -> System.out.println("Error: Invalid choice");
                    }
                } while (MenuStay);
            }
        }
        if(!validUser){
            System.out.println("Error, No user found");
        }
    }

    //Allows the details of a customer to be changed
    public static void ChangeCustomerDetails(Customer User) throws IOException {
        boolean validChoice = true;
        do {
            System.out.println("""
                    What details require change?:
                    1:    Change name
                    2:    Change phone number
                    3:    Change mobile number
                    4:    Add new address""");
            Scanner in = new Scanner(System.in);
            String Choice = in.nextLine();
            switch (Choice) {

                //changes the name
                case "1" -> {
                    System.out.println("Change name to:");
                    User.setName(in.nextLine());
                }

                //changes the phone number
                case "2" -> {
                    System.out.println("Change phone number to:");
                    User.setPhoneNumber(in.nextLine());
                }

                //changes or adds a mobile number
                case "3" -> {
                    System.out.println("Change mobile number to:");
                    User.setMobNumber(in.nextLine());
                }

                //adds a new address (and removes oldest address if there are 3 addresses already)
                case "4" -> {
                    Address address = new Address("","","","","");
                    System.out.println("Enter new address:\n   House name/number:");
                    address.setHouse(in.nextLine());
                    System.out.println("   Road name:");
                    address.setRoad(in.nextLine());
                    System.out.println("   Town/city/village name:");
                    address.setTown(in.nextLine());
                    System.out.println("   County/Province name:");
                    address.setCounty(in.nextLine());
                    System.out.println("   Post Code:");
                    address.setPostCode(in.nextLine());
                    User.setAddress(new Address[]{address,User.getAddress()[0],User.getAddress()[1]});
                }
                //Error message
                default -> {
                    System.out.println("Error: invalid choice");
                    validChoice = false;
                }
            }
            Storage.SaveData("Customers");
        }while(!validChoice);
    }
    public static void CreateBank(){
        System.out.println("Please input sort code for Current Accounts");
        Scanner in = new Scanner(System.in);
        int current = in.nextInt();
        System.out.println("Please input sort code for Business Accounts");
        int business = in.nextInt();
        System.out.println("Please input sort code for ISA Accounts");
        int ISA = in.nextInt();
        System.out.println("Input the name of the bank");
        String Name = in.next();
        Banks.add(new Bank(current,ISA,business,Name,BankIndex));
        BankIndex++;
    }
    private static Bank_Accounts AutoFindBankAccount(){
        boolean AccountFound = false;
        Bank_Accounts Account;
        int attempt = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter the account number:");
            while (!in.hasNextInt()) {
                System.out.println("Please enter the account number");
                in.next();
            }
            int AccountNumber = in.nextInt();
            System.out.print("Please enter the sort code:");
            while (!in.hasNextInt()) {
                System.out.println("Please enter the sort code");
                in.next();
            }
            int SortCode = in.nextInt();
            Account = FindBankAccount(AccountNumber,SortCode);
            if (Account != null) {
                AccountFound = true;
            } else {
                attempt++;
            }
        } while (!AccountFound & attempt<3);
        if (attempt==3) {
            System.out.println("Please confirm the account number and sort code before continuing");
        }
        return Account;
    }
    public static void ManageAccount(int customerindex) throws IOException {
        boolean AccountFound = false;
        Bank_Accounts Account;
        int AccountNumber;
        int SortCode;
        int attempt = 0;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter the account number:");
            while (!in.hasNextInt()) {
                System.out.println("Please enter the account number");
                in.next();
            }
            AccountNumber = in.nextInt();
            System.out.print("Please enter the sort code:");
            while (!in.hasNextInt()) {
                System.out.println("Please enter the sort code");
                in.next();
            }
            SortCode = in.nextInt();
            Account = FindBankAccount(AccountNumber,SortCode);
            if (Account.getOwner()!=customerindex) {
                Account = null;
            }
            if (Account != null) {
                AccountFound = true;
            } else {
                attempt++;
            }
        } while (!AccountFound & attempt<3);
        if (attempt==3) {
            System.out.println("Please confirm the account number and sort code before continuing");
        }
        Scanner in = new Scanner(System.in);
        if (Account != null) {
            boolean PinCheck  = false;
            int attempts = 0;
            do {
                System.out.print("Please enter PIN:");
                while (!in.hasNextInt()) {
                    System.out.println("Please enter your pin");
                    in.next();
                }
                String Pin = in.nextLine();
                if (Pin.equals(Account.getPIN())) {
                    PinCheck = true;
                    boolean MenuStay = true;
                    do {
                        System.out.println("| Account Menu |");
                        System.out.println(" ");
                        System.out.println(" [0] View Balance");
                        System.out.println(" [1] Deposit");
                        System.out.println(" [2] Withdraw");
                        System.out.println(" [3] Pay");
                        System.out.println(" [4] Transfer");
                        System.out.println(" [5] Change PIN");
                        System.out.println(" [6] View Log");
                        if (Account.getAccountType().equals("Business")) {
                            System.out.println(" [7] Update Business Number");
                        } else if (Account.getAccountType().equals("ISA")) {
                            System.out.println(" [7] View Current Annual Deposit");
                        }
                        System.out.println(" [9] Exit to Customer Operations");
                        in = new Scanner(System.in);
                        while (!in.hasNextInt()) {
                            System.out.println("Please enter a menu option");
                            in.next();
                        }
                        int Choice = in.nextInt();
                        boolean ChoiceCheck = true;
                        do {
                            switch (Choice) {
                                //View Balance
                                case 0 -> System.out.println(Account.getBalance());
                                //Deposit
                                case 1 -> Deposit(Account);
                                //Withdraw
                                case 2 -> Withdraw(Account);
                                //Pay
                                case 3 -> {
                                    Bank_Accounts RecipientAccount = AutoFindBankAccount();
                                    if (RecipientAccount != null) {
                                        Pay(Account,RecipientAccount);
                                    }
                                }
                                //Transfer
                                case 4 -> {
                                    Bank_Accounts RecipientAccount = AutoFindBankAccount();
                                    if (RecipientAccount != null) {
                                        Transfer(Account,RecipientAccount);
                                    }
                                }
                                //Change PIN
                                case 5 -> {
                                    boolean check = true;
                                    do {
                                        System.out.println("Would you like to change pin (Y/N)?");
                                        String Choicepin = in.nextLine();
                                        if (Choicepin.equalsIgnoreCase("Y")) {
                                            Customer customer = LookupCustomer(Account.getOwner());
                                            Account.setPIN(customer.newPIN());
                                            check = false;
                                        } else if (Choicepin.equalsIgnoreCase("N")) {
                                            check = false;
                                        }
                                    } while (check);
                                }
                                //View Log
                                case 6 -> {
                                    try {
                                        File LogData = new File("Log"+AccountNumber+SortCode+".txt");
                                        Scanner myReader = new Scanner(LogData);
                                        while (myReader.hasNextLine()) {
                                            System.out.println((myReader.nextLine()));
                                        }
                                        myReader.close();
                                    } catch (FileNotFoundException e) {
                                        System.out.println("Log for account is missing, New log will be created");
                                    }
                                }
                                //Update Business Number
                                case 7 -> {
                                    if (Account.getAccountType().equals("Business")) {
                                        Business account = FindBusinessAccount(Account);
                                        System.out.println("Current Business Number: "+ account.getBusinessNumber());
                                        System.out.print("New Business Number: ");
                                        while (!in.hasNextInt()) {
                                            System.out.println("Please enter your new business number");
                                            in.next();
                                        }
                                        int NewBusinessNumber = in.nextInt();
                                        System.out.println("Please confirm your new business number is "+NewBusinessNumber+ " [Y/N]");
                                        String Confirm = in.nextLine();
                                        if (Confirm.equalsIgnoreCase("Y")) {
                                            account.setBusinessNumber(NewBusinessNumber);
                                            System.out.println("Business number updated");
                                        } else {
                                            System.out.println("Business number not changed");
                                            System.out.println(" ");
                                        }
                                    } else if (Account.getAccountType().equals("ISA")) {
                                        ISA account = FindISAAccount(Account);
                                        System.out.println("Your Current Annual Deposit is: "+(double)account.getCurrentAnnualDeposit()/100+" pounds / " + (double)ISA.MaxAnnualDeposit/100 + " pounds");
                                    } else {
                                        ChoiceCheck = false;
                                        System.out.println("Please choose either option 1,2,3,4,5 or 6");
                                    }
                                }
                                case 9 -> MenuStay = false;
                                //Error of incorrect choice
                                default -> {
                                    ChoiceCheck = false;
                                    System.out.println("Please choose either option 1,2,3,4,5 or 6");
                                }
                            }
                            Storage.SaveData("Accounts");
                        } while (!ChoiceCheck);
                    } while (MenuStay);
                } else if (attempts == 2){
                    boolean check = true;
                    do {
                        System.out.println("Would you like to change pin (Y/N)?");
                        String Choice = in.nextLine();
                        if (Choice.equalsIgnoreCase("Y")) {
                            Customer customer = LookupCustomer(Account.getOwner());
                            Account.setPIN(customer.newPIN());
                            check = false;

                        } else if (Choice.equalsIgnoreCase("N")) {
                            System.out.println("Exiting account");
                            check = false;
                        }
                        PinCheck = true;
                    } while (check);
                } else {
                    attempts ++;
                    System.out.println("Wrong Pin (Attempts Left: "+(3-attempts)+")");
                }
            } while (!PinCheck);
        }
    }
    private static void Deposit(Bank_Accounts Account) {
        boolean check = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("How much would you like to deposit \bin pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int Depositvalue = in.nextInt();
            boolean check2 = true;
            do {
                in = new Scanner(System.in);
                System.out.println("Are you sure you want to deposit " + ((double) Depositvalue) / 100 + " pounds?   [Y/N]");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (Account.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.deposit(Depositvalue);
                    } else if (Account.getAccountType().equals("Current")) {
                        Current account = FindCurrentAccount(Account);
                        account.Deposit(Depositvalue);
                    } else {
                        Business account = FindBusinessAccount(Account);
                        account.Deposit(Depositvalue);
                    }
                } else if (confirm.equalsIgnoreCase("N")) {
                    check2 = false;
                    System.out.println("Do you still want to deposit money? [Y/N]");
                    confirm = in.nextLine();
                    boolean check3 = true;
                    do {
                        if (confirm.equalsIgnoreCase("Y")) {
                            check3 = false;
                        } else if (confirm.equalsIgnoreCase("N")) {
                            check3 = false;
                            check = false;
                        } else {
                            System.out.println("Please choose either Y or N");
                        }
                    } while (check3);
                } else {
                    System.out.println("Please choose either Y or N");
                }
            } while (check2);
        } while (check);
    }
    private static void Withdraw(Bank_Accounts Account) {
        boolean check = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("How much would you like to withdraw in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int Withdrawvalue = in.nextInt();
            boolean check2 = true;
            do {
                in = new Scanner(System.in);
                System.out.println("Are you sure you want to withdraw " + ((double) Withdrawvalue) / 100 + " pounds?   [Y/N]");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (Account.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.withdraw(Withdrawvalue);
                    } else if (Account.getAccountType().equals("Current")) {
                        Current account = FindCurrentAccount(Account);
                        account.Withdraw(Withdrawvalue);
                    } else {
                        Business account = FindBusinessAccount(Account);
                        account.Withdraw(Withdrawvalue);
                    }
                } else if (confirm.equalsIgnoreCase("N")) {
                    check2 = false;
                    System.out.println("Do you still want to withdraw money? [Y/N]");
                    confirm = in.nextLine();
                    boolean check3 = true;
                    do {
                        if (confirm.equalsIgnoreCase("Y")) {
                            check3 = false;
                        } else if (confirm.equalsIgnoreCase("N")) {
                            check3 = false;
                            check = false;
                        } else {
                            System.out.println("Please choose either Y or N");
                        }
                    } while (check3);
                } else {
                    System.out.println("Please choose either Y or N");
                }
            } while (check2);
        } while (check);
    }
    private static void Pay(Bank_Accounts Account, Bank_Accounts RecipientAccount){
        boolean check = true;
        do {
            Scanner in = new Scanner(System.in);
            String name = LookupCustomer(RecipientAccount.getOwner()).getName();
            System.out.println("How much would you like to pay to "+ name +" in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int PayValue = in.nextInt();
            boolean check2 = true;
            do {
                in = new Scanner(System.in);
                System.out.println("Are you sure you want to pay "+ name +" " + ((double) PayValue) / 100 + " pounds?   [Y/N]");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (Account.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.pay(PayValue,RecipientAccount);
                    } else if (Account.getAccountType().equals("Current")) {
                        Current account = FindCurrentAccount(Account);
                        account.Pay(PayValue,RecipientAccount);
                    } else {
                        Business account = FindBusinessAccount(Account);
                        account.Pay(PayValue,RecipientAccount);
                    }
                } else if (confirm.equalsIgnoreCase("N")) {
                    check2 = false;
                    System.out.println("Do you still want to pay someone? [Y/N]");
                    confirm = in.nextLine();
                    boolean check3 = true;
                    do {
                        if (confirm.equalsIgnoreCase("Y")) {
                            check3 = false;
                        } else if (confirm.equalsIgnoreCase("N")) {
                            check3 = false;
                            check = false;
                        } else {
                            System.out.println("Please choose either Y or N");
                        }
                    } while (check3);
                } else {
                    System.out.println("Please choose either Y or N");
                }
            } while (check2);
        } while (check);
    }
    private static void Transfer(Bank_Accounts Account, Bank_Accounts RecipientAccount) {
        boolean check = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("How much would you like to transfer to "+ RecipientAccount.getBankNumber() +" in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int PayValue = in.nextInt();
            boolean check2 = true;
            do {
                in = new Scanner(System.in);
                System.out.println("Are you sure you want to transfer "+ ((double)PayValue) / 100 + " pounds to " + RecipientAccount.getBankNumber() + "?   [Y/N]");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (Account.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.transfer(PayValue,RecipientAccount);
                    } else if (Account.getAccountType().equals("Current")) {
                        Current account = FindCurrentAccount(Account);
                        account.Transfer(PayValue,RecipientAccount);
                    } else {
                        Business account = FindBusinessAccount(Account);
                        account.Transfer(PayValue,RecipientAccount);
                    }
                } else if (confirm.equalsIgnoreCase("N")) {
                    check2 = false;
                    System.out.println("Do you still want to transfer money between accounts? [Y/N]");
                    confirm = in.nextLine();
                    boolean check3 = true;
                    do {
                        if (confirm.equalsIgnoreCase("Y")) {
                            check3 = false;
                        } else if (confirm.equalsIgnoreCase("N")) {
                            check3 = false;
                            check = false;
                        } else {
                            System.out.println("Please choose either Y or N");
                        }
                    } while (check3);
                } else {
                    System.out.println("Please choose either Y or N");
                }
            } while (check2);
        } while (check);
    }
}

