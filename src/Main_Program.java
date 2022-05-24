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
            if (i.getAccount().equals(account)) {
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
        boolean validUser = false;
        for(Customer i:Users){
            if(i.getName().equals(name)){
                validUser = true;
                boolean validChoice = true;
                do {
                    System.out.println("""
                            Choose a customer operation:
                            1:    Create bank account
                            2:    Manage bank accounts
                            3:    Change customer details
                            4:    Remove customer""");
                    int choice = in.nextInt();
                    switch (choice) {
                        case 1 -> i.CreateBankAccount();
                        //case 2 -> i.ManageAccount()
                        case 3 -> ChangeCustomerDetails(i);
                        case 4 -> {
                            i.removeCustomer();
                            Users.remove(i);
                        }
                        default -> {
                            System.out.println("Error: Invalid choice");
                            validChoice = false;
                        }
                    }
                }while(!validChoice);
            }else{
                System.out.println("Error, No user found");
            }
        }
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
    public static void ManageAccount(){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the account number:");
        while (!in.hasNextInt()) {
            System.out.println("Please enter your account number");
            in.next();
        }
        int AccountNumber = in.nextInt();
        System.out.print("Please enter the sort code:");
        while (!in.hasNextInt()) {
            System.out.println("Please enter your sort code");
            in.next();
        }
        int SortCode = in.nextInt();
        Bank_Accounts Account = FindBankAccount(AccountNumber,SortCode);
        boolean ChoiceCheck = true;
        do {
            System.out.print("Please enter PIN");
            while (!in.hasNextInt()) {
                System.out.println("Please enter your pin");
                in.next();
            }
            String Pin = in.nextLine();
            if (Pin.equals(Account.getPIN())) {
                System.out.println("| Account Menu |");
                System.out.println(" ");
                System.out.println(" [0] View Balance");
                System.out.println(" [1] Deposit");
                System.out.println(" [2] Withdraw");
                System.out.println(" [3] Pay");
                System.out.println(" [4] Transfer");
                System.out.println(" [5] Change PIN");
                System.out.println(" [6] View Log");
                while (!in.hasNextInt()) {
                    System.out.println("Please enter your pin");
                    in.next();
                }
                int Choice = in.nextInt();
                switch (Choice) {
                    //View Balance
                    case 0 -> {
                        System.out.println(Account.getBalance());
                    }
                    //Deposit
                    case 1 -> {
                        if (Account.getAccountType().equals("ISA")) {
                        }
                    }
                    //Withdraw
                    case 2 -> {

                    }
                    //Pay
                    case 3 -> {

                    }
                    //Transfer
                    case 4 -> {

                    }
                    //Change PIN
                    case 5 -> {

                    }
                    //View Log
                    case 6 -> {

                    }
                    //Error of incorrect choice
                    default -> {

                    }
                }
            } else {
                boolean check = true;
                do {
                    System.out.println("Would you like to change pin (Y/N)?");
                    String Choice = in.nextLine();
                    if (Choice.equalsIgnoreCase("Y")) {
                        Customer customerpin = newPIN();
                        Account.setPIN(Customer.newPIN());
                        check = false;
                    } else if (Choice.equalsIgnoreCase("N")) {
                        System.out.println("Exiting account");
                        check = false;
                    }
                } while (check);
            }
        } while (ChoiceCheck);
    }
}

