import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Storage {
    public static void SaveData() throws IOException {
        String BankFile = "Banks.txt";
        String CurrentFile = "CurrentAccounts.txt";
        String BusinessFile = "BusinessAccounts.txt";
        String ISAFile = "ISAAccounts.txt";
        String CustomerFile = "Customers.txt";
        File Ba = new File(BankFile);
        File Cur = new File(CurrentFile);
        File Bu = new File(BusinessFile);
        File I = new File(ISAFile);
        File Cus = new File(CustomerFile);
        Writer BankWriter = new FileWriter(Ba);
        Writer CustomerWriter = new FileWriter(Cus);
        Writer CurrentWriter = new FileWriter(Cur);
        Writer BusinessWriter = new FileWriter(Bu);
        Writer ISAWriter = new FileWriter(I);
        //writes Banks with format (CurrentSortCode,BusinessSortCode,ISASortCode,Name,Index
        int index = 0;
        for (Bank i : Main_Program.Banks){
            BankWriter.write(i.getCurrentSortCode()+","+i.getBusinessSortCode()+","+i.getISASortCode()+","+i.getName()+","+index+"\n");
            index += 1;
        }
        BankWriter.close();
        index = 0;
        //writes Customers with format (Index,Name,Age,DOB,Phone,Mobile,Address1,Address2,Address3)
        for (Customer i : Main_Program.Users){
            int Addresses = i.getAddress().length;
            String AddressString = "";
            for(int j = 0;j<Addresses;j++) {
                AddressString += ","+i.getAddress()[j].toString();
            }
            CustomerWriter.write(index+","+i.getName()+","+i.getAge()+","+i.getDOB()+","+i.getPhoneNumber()+","+i.getMobNumber()+AddressString+"\n");
            index += 1;
        }
        CustomerWriter.close();
        index = 0;
        //Writes data on each account type with ID numbers instead of Banks and Customers in format (AccountNumber,PIN,Balance,BankID,AccountType,CustomerID(,OtherInfo)
        for (Current i:Main_Program.CurrentAccounts){
            int ID[] = getID(i);
            CurrentWriter.write(i.getBankNumber()+","+i.getPIN()+","+i.getBalance()+","+String.valueOf(ID[0])+","+i.getAccountType()+","+String.valueOf(ID[1]));
        }
        CurrentWriter.close();
        for (Business i:Main_Program.BusinessAccounts){
            int ID[] = getID(i);
            BusinessWriter.write(i.getBankNumber()+","+i.getPIN()+","+i.getBalance()+","+String.valueOf(ID[0])+","+i.getAccountType()+","+String.valueOf(ID[1])+","+i.getBusinessNumber());
        }
        BusinessWriter.close();
        for (ISA i:Main_Program.ISAAccounts){
            int ID[] = getID(i);
            ISAWriter.write(i.getBankNumber()+","+i.getPIN()+","+i.getBalance()+","+String.valueOf(ID[0])+","+i.getAccountType()+","+String.valueOf(ID[1])+","+i.getCurrentAnnualDeposit());
        }
        ISAWriter.close();
    }
    //gets IDs for Bank and Customer for each Account
    private static int[] getID(Bank_Accounts Account){
        int CustomerID = -1;
        int BankID = -1;
        for (int j=0;j<Main_Program.Users.size();j++){
            if (Account.getOwner().equals(Main_Program.Users.get(j))){
                CustomerID = j;
            }
        }
        for (int j=0;j<Main_Program.Banks.size();j++){
            if (Account.getBank().equals(Main_Program.Banks.get(j))){
                BankID = j;
            }
        }
        return new int[]{BankID,CustomerID};
    }
}
