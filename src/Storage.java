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
        BankWriter.write(Integer.toString(Main_Program.BankIndex)+"\n");
        for (Bank i : Main_Program.Banks){
            BankWriter.write(i.getCurrentSortCode()+"/"+i.getBusinessSortCode()+"/"+i.getISASortCode()+"/"+i.getName()+"/"+i.getIndex()+"\n");
        }
        BankWriter.close();
        //writes Customers with format (Index,Name,Age,DOB,Phone,Mobile,Address1,Address2,Address3)
        CustomerWriter.write(Integer.toString(Main_Program.CustomerIndex)+"\n");
        for (Customer i : Main_Program.Users){
            int AddressLength = (i.getAddress()).length;
            String AddressString = "";
            for(int j = 0;j<AddressLength;j++) {
                if (j>0){
                    AddressString += ";";
                }
                AddressString += (i.getAddress())[j].toString();
            }
            CustomerWriter.write(i.getIndex()+"/"+i.getName()+"/"+"/"+i.getDOB()+"/"+i.getPhoneNumber()+"/"+i.getMobNumber()+"/"+AddressString+"\n");
        }
        CustomerWriter.close();
        //Writes data on each account type with ID numbers instead of Banks and Customers in format (AccountNumber,PIN,Balance,BankID,AccountType,CustomerID(,OtherInfo)
        for (Current i:Main_Program.CurrentAccounts){
            CurrentWriter.write(i.getBankNumber()+"/"+i.getPIN()+"/"+i.getBalance()+"/"+i.getBank()+"/"+i.getAccountType()+"/"+i.getOwner()+"\n");
        }
        CurrentWriter.close();
        for (Business i:Main_Program.BusinessAccounts){
            BusinessWriter.write(i.getBankNumber()+"/"+i.getPIN()+"/"+i.getBalance()+"/"+i.getBank()+"/"+i.getAccountType()+"/"+i.getOwner()+"/"+i.getBusinessNumber()+"\n");
        }
        BusinessWriter.close();
        for (ISA i:Main_Program.ISAAccounts){
            ISAWriter.write(i.getBankNumber()+"/"+i.getPIN()+"/"+i.getBalance()+"/"+i.getBank()+"/"+i.getAccountType()+"/"+i.getOwner()+"/"+i.getCurrentAnnualDeposit()+"\n");
        }
        ISAWriter.close();
    }
}
