
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
    public void CreateBankAccount (Customer User){
        //checks if the account creator is over 16
        if(User.Age >= 16){
            Scanner in = new Scanner(System.in);
            System.out.print("""
                    What type of bank account would you like to open?
                    A: Current account: a versatile account for everyday use
                    B: ISA account: a savings account for keeping your investments on par with interest
                    C: Business account: a premium bank account for your business needs""");
            String Choice = in.nextLine();
            //determines the type of bank account created
            switch(Choice){
                case "A" -> newBankAccount("Current", User);
                case "B" -> newBankAccount("ISA", User);
                case "C" -> newBankAccount("Business", User);
                default -> System.out.println("Error: Invalid bank account type selected");
            }
        }
    }

    //newBankAccount: Creates a new bank account and the relevant account type object
    private void newBankAccount (String accountType, Customer User){
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
                        Main_Program.BusinessAccounts.add(new Business(newBankNumber(), newPIN(), 0, bank, accountType, User,businessnum));
                        System.out.println("Your Chequebook will be sent to your current address");
                    }
                    case "ISA"->{
                        Main_Program.ISAAccounts.add(new ISA((Main_Program.Accounts.get(-1)),0));
                    }
                    case "Current"->{
                        Main_Program.CurrentAccounts.add(new Current(newBankNumber(), newPIN(), 0, bank, accountType, User));
                    }
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
    private String newPIN(){
        Random rng = new Random();
        int pin = rng.nextInt(10000);
        String strpin = String.valueOf(pin);
        System.out.println("""
                               *****IMPORTANT*****
                               REMEMBER YOUR PIN
                if you forget your PIN, we cannot recover it
                         Your PIN is:        """+strpin);
        Scanner in = new Scanner(System.in);
        in.nextLine();
        return strpin;
    }
}