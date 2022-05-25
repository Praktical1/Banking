import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.time.LocalDate;
public class Main_Program {
    static ArrayList<Bank> Banks = new ArrayList<>();
    static ArrayList<Customer> Users = new ArrayList<>();
    static ArrayList<Business> BusinessAccounts = new ArrayList<>();
    static ArrayList<ISA> ISAAccounts = new ArrayList<>();
    static ArrayList<Current> CurrentAccounts = new ArrayList<>();
    static ArrayList<Bank_Accounts> Accounts = new ArrayList<>();
    static String Username;
    public static void main(String[] args) throws ParseException {
        Username = Authentication.Login();
        boolean Exit = Username.equals("");
        while(!Exit){
            System.out.println("\n\nHello " + Username + """
                                    
                    Welcome to BankApp
                    Choose an operation by entering 1, 2, or 3:
                                    
                    1: Enroll Customer
                                    
                    2: Manage Customer and Customer Assets
                                    
                    3: Exit
                    """);
            Scanner in = new Scanner(System.in);
            int answer;
            answer = in.nextInt();
            switch (answer) {
                case 1 -> CreateCustomer();
                case 2 -> ManageCustomer();
                case 3 -> Exit = true;
            }
        }
    }

    public static Bank FindBank(String BankName){
        for(Bank i:Banks){
            if(BankName.equals(i.getName())){
                return i;
            }
        }
        return new Bank(0,0,0,"");
    }

    //For future use: FindBusinessAccount will find the business account object related to a bank account object

    public static Business FindBusinessAccount(Bank_Accounts account){
        for (int i = 0; i < BusinessAccounts.size(); i++) {
            if (BusinessAccounts.get(i).getAccount().equals(account)){
                return BusinessAccounts.get(i);
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
        for (int i = 0; i < CurrentAccounts.size(); i++) {
            if (CurrentAccounts.get(i).getAccount().equals(account)){
                return CurrentAccounts.get(i);
            }
        }
        System.out.println("Error, Could not find this Current account");
        return null;
    }

    //For finding bank accounts from the account number and sort code
    public static Bank_Accounts FindBankAccount(int AccountNum, int SortCode){
        for (Bank_Accounts i : Accounts){
            if(i.getBankNumber() == AccountNum & ((i.getBank().getCurrentSortCode() == SortCode) || (i.getBank().getISASortCode() == SortCode) || (i.getBank().getBusinessSortCode() == SortCode))){
                return i;
            }
        }
        System.out.println("Error: Could not find bank account with these numbers");
        return null;
    }
    public static void CreateCustomer() throws ParseException {
        //Staff enters details of a new customer
        Scanner in = new Scanner(System.in);
        System.out.println("\nPlease enter the new customer's details:\nFull Name:");

        String name = in.nextLine();
        System.out.println("\nDate of Birth (dd mm yyyy):");

        String DOBString = in.nextLine();
        DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        //Date and time is a nightmare. Calendar type is different from Date type yet both represent a date
        //also, most features of Date are being depreciated so Calendar is used here
        Calendar DOB = Calendar.getInstance();
        DOB.setTime(formatter.parse(DOBString));

        System.out.println("\nPhone Number:");
        int Phone = in.nextInt();
        //Optional mobile number requires a string to feed into an integer so leaving it blank is possible
        System.out.println("\nMobile Number (Optional: Leave blank if none):");
        String Mob = in.nextLine();
        int Mobile = 0;
        if(!Mob.equals("")) {
            Scanner mobs = new Scanner(Mob);
            Mobile = mobs.nextInt();
        }

        System.out.println("""
                Address:
                We need:
                        Your current address if you have lived there for the last 3 years
                        Your previous address if you have changed address in the last 3 years
                        Your next most recent address if you lived at your previous 2 addresses for less than 3 years combined""");
        //gets all 3 addresses from the last 3 years, counting tenancy to the nearest month
        int months = 0;
        int index = 0;
        Address[] home = new Address[3];

        do{
            index += 1;
            System.out.println("For Address #"+index+"\n  House Name/Number:");
            home[index].setHouse(in.nextLine());
            System.out.println("  Road:");
            home[index].setRoad(in.nextLine());
            System.out.println("  Town/Village/City:");
            home[index].setTown(in.nextLine());
            System.out.println("  County/Province:");
            home[index].setCounty(in.nextLine());
            System.out.println("  PostCode/ZipCode:");
            home[index].setPostCode(in.nextLine());
            System.out.println("How many months has the customer lived at this address? (to the closest month rounded up)");
            months += in.nextInt();
        }while(months<36&index<3);

        //Checks if the current year's birthday has happened
        Calendar Birthday = DOB;
        Birthday.set(Calendar.YEAR,LocalDate.now().getYear());
        int addyear = 0;
        if(Birthday.getTimeInMillis() > System.currentTimeMillis()){
            addyear = -1;
        }

        //Creates a new customer with all the info (getting an age based upon current time is a nightmare)
        Users.add(new Customer(name,LocalDate.now().getYear()-DOB.get(Calendar.YEAR) + addyear, DOB.getTime(),Phone,Mobile,home));
    }

    public static void ManageCustomer(){
        System.out.println("Enter the Customer's name:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Enter your Current Post code (e.g AA1 1AA");
        String postcode = in.nextLine();
        System.out.println("Enter your house name/number");
        String hname = in.nextLine();
        boolean validUser = false;
        do {
            for (Customer i : Users) {
                if (i.getName().equals(name) & i.getAddress()[0].getPostCode().equals(postcode) & i.getAddress()[0].getHouse().equals(hname)) {
                    validUser = true;
                    boolean validChoice = true;
                    do {
                        System.out.println("""
                                Choose a customer operation:
                                1:    Create bank account
                                2:    Manage bank accounts
                                3:    Change customer details
                                4:    Remove customer""");
                        String choice = in.nextLine();
                        switch (choice) {
                            case "1" -> i.CreateBankAccount();
                            case "2" -> ManageAccount();
                            case "3" -> ChangeCustomerDetails(i);
                            case "4" -> {
                                i.removeCustomer();
                                Users.remove(i);
                            }
                            default -> {
                                System.out.println("Error: Invalid choice");
                                validChoice = false;
                            }
                        }
                    } while (!validChoice);
                }
            }
            if(!validUser){
                System.out.println("Error, No user found");
            }
        }while(!validUser);
    }

    //Allows the details of a customer to be changed
    public static void ChangeCustomerDetails(Customer User){
        boolean validChoice = true;
        do {
            System.out.println("""
                    What details require change?:
                    1:    Change name
                    2:    Change phone number
                    3:    Change mobile number
                    4:    Add new address""");
            Scanner in = new Scanner(System.in);
            int Choice = in.nextInt();
            switch (Choice) {

                //changes the name
                case 1 -> {
                    System.out.println("Change name to:");
                    User.setName(in.nextLine());
                }

                //changes the phone number
                case 2 -> {
                    System.out.println("Change phone number to:");
                    User.setPhoneNumber(in.nextInt());
                }

                //changes or adds a mobile number
                case 3 -> {
                    System.out.println("Change mobile number to:");
                    User.setMobNumber(in.nextInt());
                }

                //adds a new address (and removes oldest address if there are 3 addresses already)
                case 4 -> {
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
        }while(!validChoice);
    }
    public static void CreateBank(){
        System.out.println("Please input sort code for Current Accounts");
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
    public static void ManageAccount(){
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
                System.out.print("Please enter PIN");
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
                        System.out.println(" [9] Exit");
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
                                            Customer customer = Account.getOwner();
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
                        } while (!ChoiceCheck);
                    } while (MenuStay);
                } else if (attempts == 2){
                    boolean check = true;
                    do {
                        System.out.println("Would you like to change pin (Y/N)?");
                        String Choice = in.nextLine();
                        if (Choice.equalsIgnoreCase("Y")) {
                            Customer customer = Account.getOwner();
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
            System.out.println("How much would you like to deposit in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int Depositvalue = in.nextInt();
            System.out.println("Are you sure you want to deposit " + ((double) Depositvalue) / 100 + " pound(s)?   [Y/N]");
            String confirm = in.nextLine();
            boolean check2 = true;
            do {
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
            System.out.println("Are you sure you want to withdraw " + ((double) Withdrawvalue) / 100 + " pound(s)?   [Y/N]");
            String confirm = in.nextLine();
            boolean check2 = true;
            do {
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
            System.out.println("How much would you like to pay to "+ RecipientAccount.getOwner() +" in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int PayValue = in.nextInt();
            System.out.println("Are you sure you want to pay "+ RecipientAccount.getOwner() + ((double) PayValue) / 100 + " pound(s)?   [Y/N]");
            String confirm = in.nextLine();
            boolean check2 = true;
            do {
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (RecipientAccount.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.pay(PayValue,RecipientAccount);
                    } else if (RecipientAccount.getAccountType().equals("Current")) {
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
            System.out.println("How much would you like to transfer to "+ RecipientAccount.getOwner() +" in pence?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter only integers");
                in.next();
            }
            int PayValue = in.nextInt();
            System.out.println("Are you sure you want to transfer "+ RecipientAccount.getOwner() + ((double) PayValue) / 100 + " pound(s)?   [Y/N]");
            String confirm = in.nextLine();
            boolean check2 = true;
            do {
                if (confirm.equalsIgnoreCase("Y")) {
                    check = false;
                    check2 = false;
                    if (RecipientAccount.getAccountType().equals("ISA")) {
                        ISA account = FindISAAccount(Account);
                        account.transfer(PayValue,RecipientAccount);
                    } else if (RecipientAccount.getAccountType().equals("Current")) {
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

