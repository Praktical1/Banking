import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Business_Tests {
    Address Address1 = new Address("1","this road", "this town","this county","OV343RE");
    DateFormat Format = new SimpleDateFormat("dd MM yyyy");
    Date dob = Format.parse("20 10 2001");
    Customer User1 = new Customer("Alex",19,dob,0,0, new Address[]{Address1});
    Customer User2 = new Customer("James",27,Format.parse("20 10 1993"),0,0, new Address[]{Address1});
    Bank Bank1 = new Bank(0,1,2,"newbank");
    Business Account1 = new Business(12345678,"1234",1000,Bank1,"Business",User1,0);
    Business Account2 = new Business(87654321,"1234",1000,Bank1,"Business",User2,0);
    Current Account3 = new Current(11223344,"4231",1000,Bank1,"Current",User1);
    ISA Account4 = new ISA(44332211,"4231",1000,Bank1,"ISA",User2,0);

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

}
