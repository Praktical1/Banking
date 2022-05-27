import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Business_Tests {
    Address Address1 = new Address("1","this road", "this town","this county","OV343RE");
    DateFormat Format = new SimpleDateFormat("dd MM yyyy");
    String dob = "20 10 2001";
    Customer User1 = new Customer(1,"Alex",dob,"0","0", new Address[]{Address1});
    Customer User2 = new Customer(2,"James","20 10 1993","0","0", new Address[]{Address1});
    Bank Bank1 = new Bank(0,1,2,"newbank",1);
    Business Account1 = new Business(12345678,"1234",1000,Bank1.getIndex(),"Business",User1.getIndex(),0);
    Business Account2 = new Business(87654321,"1234",1000,Bank1.getIndex(),"Business",User2.getIndex(),0);
    Current Account3 = new Current(11223344,"4231",1000,Bank1.getIndex(),"Current",User1.getIndex());
    ISA Account4 = new ISA(44332211,"4231",1000,Bank1.getIndex(),"ISA",User2.getIndex(),0);

    public Business_Tests() throws ParseException {
    }
    @Test
    void WithdrawMoney(){
        var value = 100;
        Account1.Withdraw(value);
        assertEquals(900,Account1.getBalance());
    }
    @Test
    void WithdrawTooMuch(){
        var value = 1001;
        Account2.Withdraw(value);
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void WithdrawNegative(){
        var value = -25;
        Account2.Withdraw(value);
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void DepositMoney(){
        var value = 100;
        Account1.Deposit(value);
        assertEquals(1100,Account1.getBalance());
    }
    @Test
    void DepositNegative(){
        var value = -25;
        Account2.Deposit(value);
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void TransferMoney(){
        var value = 100;
        Account1.Transfer(value,Account3);
        assertEquals(900,Account1.getBalance());
        assertEquals(1100,Account3.getBalance());
    }
    @Test
    void TransferTooMuch(){
        var value = 1001;
        Account1.Transfer(value,Account3);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account3.getBalance());
    }
    @Test
    void TransferNegative(){
        var value = -25;
        Account1.Transfer(value,Account3);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account3.getBalance());
    }
    @Test
    void TransferNotSame(){
        var value = 100;
        Account1.Transfer(value,Account2);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void TransferToISA(){
        var value = 100;
        Main_Program.ISAAccounts.add(Account4);
        Account2.Transfer(value,Account4);
        assertEquals(900,Account2.getBalance());
        assertEquals(1100,Account4.getBalance());
        Main_Program.ISAAccounts.remove(0);
    }
    @Test
    void TransferToISAOverLimit(){
        var value = 100;
        Account4.setCurrentAnnualDeposit(2000000);
        Main_Program.ISAAccounts.add(Account4);
        Account2.Transfer(value,Account4);
        assertEquals(1000,Account2.getBalance());
        assertEquals(1000,Account4.getBalance());
        Main_Program.ISAAccounts.remove(0);
    }
    @Test
    void TransferToNotSameOwner(){
        var value = 100;
        Account1.Transfer(value,Account2);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void PayMoney(){
        var value = 100;
        Account1.Pay(value,Account2);
        assertEquals(900,Account1.getBalance());
        assertEquals(1100,Account2.getBalance());
    }
    @Test
    void PayTooMuch(){
        var value = 1001;
        Account1.Pay(value,Account2);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account2.getBalance());
    }
    @Test
    void PayNegative(){
        var value = -1;
        Account1.Pay(value,Account2);
        assertEquals(1000,Account1.getBalance());
        assertEquals(1000,Account2.getBalance());
    }
}
