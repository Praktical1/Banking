
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class Customer {
    String Name;
    int Age;
    Date DOB;
    int PhoneNumber;
    int MobNumber;
    Address[] address;

    public Customer(String name, int age, Date DOB, int phoneNumber, int mobNumber, Address[] address) {
        Name = name;
        Age = age;
        this.DOB = DOB;
        PhoneNumber = phoneNumber;
        MobNumber = mobNumber;
        this.address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getMobNumber() {
        return MobNumber;
    }

    public void setMobNumber(int mobNumber) {
        MobNumber = mobNumber;
    }

    public Address[] getAddress() {
        return address;
    }

    public void setAddress(Address[] address) {
        this.address = address;
    }

    //CreateBankAccount: gives the user a choice of new bank account
    public void CreateBankAccount (){
        //checks if the account creator is over 16
        if(Age >= 16){
            Scanner in = new Scanner(System.in);
            System.out.print("""
                    What type of bank account would you like to open?
                    A: Current account: a versatile account for everyday use
                    B: ISA account: a savings account for keeping your investments on par with interest
                    C: Business account: a premium bank account for your business needs""");
            String Choice = in.nextLine();
            //determines the type of bank account created
            switch(Choice){
                case "A" -> newBankAccount("Current");
                case "B" -> newBankAccount("ISA");
                case "C" -> newBankAccount("Business");
                default -> System.out.println("Error: Invalid bank account type selected");
            }
        }
    }

    //newBankAccount: Creates a new bank account and the relevant account type object
    private void newBankAccount (String accountType){
        boolean ValidBank = false;
        do {
            //determines the bank the account will be with
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the name of the Bank you want to open an account with");
            String BankName = in.nextLine();
            //gets the bank by the bank name
            Bank bank = Main_Program.FindBank(BankName);
            if (bank.getCurrentSortCode() == 0) {
                System.out.println("Error: Bank name does not match any bank in our records");
            } else {
                ValidBank = true;
                switch (accountType){
                    case "Business"->{
                        System.out.println("Enter registered business number");
                        int businessnum = in.nextInt();
                        Main_Program.BusinessAccounts.add(new Business(newBankNumber(), newPIN(), 0, bank, accountType, this,businessnum));
                        System.out.println("Your Chequebook will be sent to your current address");
                    }
                    case "ISA"-> Main_Program.ISAAccounts.add(new ISA(newBankNumber(),newPIN(),0,bank,accountType,this,0));

                    case "Current"-> Main_Program.CurrentAccounts.add(new Current(newBankNumber(), newPIN(), 0, bank, accountType, this));

                }
            }
        }while(!ValidBank);
    }
    //newBankNumber: Generates a new valid bank number using the Luhn algorithm
    private int newBankNumber(){
        Random rng = new Random();
        int accountnum;
        boolean validaccount = true;
        do {
            do {
                accountnum = rng.nextInt(100000000);
            } while (accountnum < 10000000);
            for(Bank_Accounts i:Main_Program.Accounts){
                if(accountnum == i.getBankNumber()){
                    validaccount = false;
                }
            }
        }while(!validaccount);
        return accountnum;
    }
    //newPIN: generates a new randomised PIN number
    public String newPIN(){
        Random rng = new Random();
        //formats pin as ####
        int pin = rng.nextInt(10000);
        String strpin = "";
        if(pin<10) {
            strpin = "000" ;
        } else if (pin<100) {
            strpin = "00";
        }else if (pin < 1000){
            strpin = "0";
        }
        strpin += String.valueOf(pin);
        //Displays pin
        System.out.println("""
                               *****IMPORTANT*****
                               REMEMBER YOUR PIN
                if you forget your PIN, we cannot recover it
                         Your PIN is:        """ +strpin);
        Scanner in = new Scanner(System.in);
        in.nextLine();
        return strpin;
    }

    //removes the customer from the system
    public void removeCustomer(){
        //misinput checking
        System.out.println("Are you sure you want to be removed from our System? (Y/N)");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine().toLowerCase();
        switch(response){
            case "y" -> {
                int totbal = 0;
                for(Bank_Accounts i :Main_Program.Accounts) {
                    if(i.getOwner().equals(this)) {
                        switch(i.getAccountType()) {
                            case "Current", "Business" -> totbal += i.getBalance();
                            case "ISA" -> totbal += i.getBalance()*0.75;
                        }
                        i.setBalance(0);
                    }
                }
                System.out.println("Your total remaining balance was £"+ (float)(totbal/100)+"\nWould you like to withdraw all of it? (Y/N)");
                response = in.nextLine().toLowerCase();
                switch(response) {
                    case "y" -> System.out.println("Customer must collect their money within the collection period");
                    default ->{}
                }
            }
            default -> {}
            }
        }
    }
