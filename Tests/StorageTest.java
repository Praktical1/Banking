import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    Address Address1 = new Address("1","this road", "this town","this county","OV343RE");
    DateFormat Format = new SimpleDateFormat("dd MM yyyy");
    Date dob = Format.parse("20 10 2001");
    Customer User1 = new Customer(1,"Alex",19,dob,0,0, new Address[]{Address1});
    Customer User2 = new Customer(2,"James",27,Format.parse("20 10 1993"),0,0, new Address[]{Address1});
    Bank Bank1 = new Bank(0,1,2,"newbank",1);
    Business Account1 = new Business(12345678,"1234",1000,Bank1.getIndex(),"Business",User1.getIndex(),0);
    Business Account2 = new Business(87654321,"1234",1000,Bank1.getIndex(),"Business",User2.getIndex(),0);
    Current Account3 = new Current(11223344,"4231",1000,Bank1.getIndex(),"Current",User1.getIndex());
    ISA Account4 = new ISA(44332211,"4231",1000,Bank1.getIndex(),"ISA",User2.getIndex(),0);

    public StorageTest() throws ParseException {
        Main_Program.Banks.add(Bank1);
        Main_Program.Users.add(User1);
        Main_Program.Users.add(User2);
        Main_Program.CurrentAccounts.add(Account3);
        Main_Program.BusinessAccounts.add(Account1);
        Main_Program.BusinessAccounts.add(Account2);
        Main_Program.ISAAccounts.add(Account4);
    }
    @Test
    void SaveData() throws IOException {
        Storage.SaveData();
        File Bank = new File("Banks.txt");
        File Current = new File("Current.txt");
        File Business = new File("Business.txt");
        File ISA = new File("ISA.txt");
        File Customer = new File("Customer.txt");
        Scanner in = new Scanner(Bank);
        String Banks = in.nextLine();
        Scanner in2 = new Scanner(Customer);
        String Customer1 = in2.nextLine();
        String Customer2 = in2.nextLine();
        Scanner in3 = new Scanner(Current);
        String Current1 = in3.nextLine();
        Scanner in4 = new Scanner(Business);
        String Business1 = in4.nextLine();
        String Business2 = in4.nextLine();
        Scanner in5 = new Scanner(ISA);
        String ISA1 = in5.nextLine();
        assertEquals("0,1,2,newbank",Banks);
        assertEquals("Alex,19,20 10 2001,0,0,1,this road,this town,this county,OV343RE",Customer1);
        assertEquals("James,27,20 10 1993,0,0,1,this road,this town,this county,OV343RE",Customer2);
        assertEquals("11223344,4231,1000,0,Current,0",Current1);
        assertEquals("12345678,1234,1000,0,Business,0,0",Business1);
        assertEquals("87654321,1234,1000,0,Business,1,0",Business2);
        assertEquals("44332211,4231,1000,0,ISA,1,0",ISA1);
    }
}
