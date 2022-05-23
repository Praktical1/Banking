import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Boolean Exit = false;
        if (Username.equals("")){Exit = true;}
        while(!Exit){
            System.out.println("\n\nHello " + Username + """
                                    
                    Welcome to BankApp
                    Choose an operation by entering 1, 2, or 3:
                                    
                    1: Enroll Customer
                                    
                    2: Manage Customer and Customer Assets
                                    
                    3: Exit
                    """);
            Scanner in = new Scanner(System.in);
            int answer = 0;
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

    /*public static Business FindBusinessAccount(Bank_Accounts account){
        for (int i = 0; i < BusinessAccounts.size(); i++) {
            if (BusinessAccounts.get(i).getAccount().equals(account)){
                return BusinessAccounts.get(i);
            }
        }
        System.out.println("Error, Could not find this Business account");
        return null;
    }*/

    //FindISAAccount: Finds the ISA account object related to a bank account object
    public static ISA FindISAAccount(Bank_Accounts account){
        for (int i = 0; i < ISAAccounts.size(); i++) {
            if (ISAAccounts.get(i).getAccount().equals(account)){
                return ISAAccounts.get(i);
            }
        }
        System.out.println("Error, Could not find this ISA account");
        return null;
    }

    //For future use: FindCurrentAccount will find the current account object related to a bank account object

    /*public static Current FindCurrentAccount(Bank_Accounts account){
        for (int i = 0; i < CurrentAccounts.size(); i++) {
            if (CurrentAccounts.get(i).getAccount().equals(account)){
                return CurrentAccounts.get(i);
            }
        }
        System.out.println("Error, Could not find this Current account");
        return null;
    }*/
    public static void CreateCustomer() throws ParseException {
        //Staff enters details of a new customer
        Scanner in = new Scanner(System.in);
        System.out.println("\nPlease enter the new customer's details:\nFull Name:");

        String name = in.nextLine();
        System.out.println("\nDate of Birth (dd mm yyyy):");

        String DOBString = in.nextLine();
        DateFormat formatter = new SimpleDateFormat("dd mm yyyy");
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
            index ++;
            System.out.println("For Address #"+index+"\n  House Name/Number:");
            home[index].House = in.nextLine();
            System.out.println("  Road:");
            home[index].Road = in.nextLine();
            System.out.println("  Town/Village/City:");
            home[index].Town = in.nextLine();
            System.out.println("  County/Province:");
            home[index].County = in.nextLine();
            System.out.println("  PostCode/ZipCode:");
            home[index].PostCode = in.nextLine();
            System.out.println("How many months has the customer lived at this address? (to the closest month rounded up)");
            months += in.nextInt();
        }while(months<36&index<3);
        //Creates a new customer with all the info (getting an age based upon current time is a nightmare)
        Users.add(new Customer(name,LocalDate.now().getYear()-DOB.get(Calendar.YEAR), DOB.getTime(),Phone,Mobile,home));
    }
    public static void ManageCustomer(){

    }
}

