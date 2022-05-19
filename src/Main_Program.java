import java.util.ArrayList;

public class Main_Program {
    static ArrayList<Bank> Banks = new ArrayList<>();
    static ArrayList<Customer> Users = new ArrayList<>();
    static ArrayList<Bank_Accounts> Accounts = new ArrayList<>();
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
}
