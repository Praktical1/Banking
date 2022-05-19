
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
public class Customer {
    String Name;
    int Age;
    Date DOB;
    int PhoneNumber;
    int MobNumber;
    Address address;

    public Customer(String name, int age, Date DOB, int phoneNumber, int mobNumber, Address address) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

                Main_Program.Accounts.add(new Bank_Accounts(newBankNumber(), newPIN(), 0, bank, accountType, User));
                switch (accountType){
                    case "Business"->{
                        System.out.println("Enter registered business number");
                        int businessnum = in.nextInt();
                        Main_Program.BusinessAccounts.add(new Business(businessnum,Main_Program.Accounts.get(-1)));
                    }
                    case "ISA":{}
                    case "Current":{}
                }
            }
        }while(!ValidBank);
    }
    private int newBankNumber(){
        Random rng = new Random();
        int[] accountdigit = new int[16];
        for (int i = 0; i < 15; i++) {
            accountdigit[i] =rng.nextInt(10);
        }
        //makes sure the first bank account digit is not 0
        if(accountdigit[0] == 0){ accountdigit[0]+= 1;}
        int sum = 0;
        //Luhn algorithm for generating valid bank account numbers
        for (int i = 0; i < 7; i++) {
            int first = 2*accountdigit[2*i]%10;
            sum += first+((2*accountdigit[2*i])-first)/10+accountdigit[2*i+1];
        }
        int first = (accountdigit[15]*2)%10;
        sum += first+(2*accountdigit[15]-first)/10;
        accountdigit[15] = 10-(sum%10);
        //concatenating the account number digits
        int accountnum = 0;
        for(int i:accountdigit){
            accountnum *= 10;
            accountnum += i;
        }
        return accountnum;
    }
    private String newPIN(){
        Random rng = new Random();
        int pin = rng.nextInt(10000);
        String strpin = String.valueOf(pin);
        return strpin;
    }
}
