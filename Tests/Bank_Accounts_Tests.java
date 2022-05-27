import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class Bank_Accounts_Tests {
    Address Address1 = new Address("1","this road", "this town","this county","OV343RE");
    DateFormat Format = new SimpleDateFormat("dd MM yyyy");
    String dob = "20 10 2001";
    Customer User1 = new Customer(1,"Alex",dob,"0","0", new Address[]{Address1});
    Bank Bank1 = new Bank(0,1,2,"newbank",1);
    Bank_Accounts Account = new Current(12345678,"1234",0,Bank1.getIndex(),"Current",User1.getIndex());

    Bank_Accounts_Tests() throws ParseException {
    }

    @Test
    void VerifyEqualsZero() {
        var value = 0;
        assertEquals(0,Account.VerifyPayment(value));
    }
    @Test
    void VerifyLessThanZero(){
        var Value = -5;
        assertEquals(0,Account.VerifyPayment(Value));
    }
    @Test
    void VerifyNormalPayment(){
        var Value = 26;
        assertEquals(26,Account.VerifyPayment(Value));
    }
}

