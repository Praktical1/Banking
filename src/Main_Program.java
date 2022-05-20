import java.util.ArrayList;

public class Main_Program {
    static ArrayList<Bank> Banks = new ArrayList<>();
    static ArrayList<Customer> Users = new ArrayList<>();
    static ArrayList<Business> BusinessAccounts = new ArrayList<>();
    static ArrayList<ISA> ISAAccounts = new ArrayList<>();
    static ArrayList<Current> CurrentAccounts = new ArrayList<>();
    static ArrayList<Bank_Accounts> Accounts = new ArrayList<>();
    String Username;
    public static void main(String[] args) {
        //so this changed
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
}

