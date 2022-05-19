import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Log {
    static void Log(int AccountNumber, int SortCode, int RecipientAccountNumber, int RecipientSortCode, int Payment) {
        ArrayList<String> LogStorage = new ArrayList<>();    //Temporary arraylist responsible for holding information currently within log file to allow for additions

        //Convert Payment to pounds
        String PaymentPounds = "£"+(Payment/100)+"."+(Payment%100);

        //Date and Time
        Timestamp CurrentTime = new Timestamp(System.currentTimeMillis());

        //Reading Log for Payer
        try {
            File LogData = new File("Log"+AccountNumber+SortCode+".txt");
            Scanner myReader = new Scanner(LogData);
            while (myReader.hasNextLine()) {
                LogStorage.add(myReader.nextLine());         //Stores current log data in array
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Log for account is missing, New log will be created");
        }

        //Adding new entry into Log for Payer
        LogStorage.add(0,CurrentTime + "   " + RecipientAccountNumber + "   "+ RecipientSortCode + "    Payment " + PaymentPounds);

        //Writing Log for Payer
        try {
            FileWriter myWriter = new FileWriter("Log"+AccountNumber+SortCode+".txt");
            for (int i=0; i<LogStorage.size(); i++){
                if (i>0) {
                    myWriter.write(System.getProperty( "line.separator" ));
                }
                myWriter.write(LogStorage.get(i));
            }
            myWriter.close();
        }  catch (IOException g) {
            System.out.println("Error occurred with saving account log");
            g.printStackTrace();
        }

        LogStorage = new ArrayList<>();                 //Reset Array

        //Reading Log for Paid
        try {
            File LogData = new File("Log"+RecipientAccountNumber+RecipientSortCode+".txt");
            Scanner myReader = new Scanner(LogData);
            while (myReader.hasNextLine()) {
                LogStorage.add(myReader.nextLine());         //Stores current log data in array
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Log for Recipient account is missing, New log will be created");
        }
        //Adding new entry into Log for Paid
        LogStorage.add(0,CurrentTime + "   " + AccountNumber + "   "+ SortCode + "   Received " + PaymentPounds);
        //Writing Log for Paid
        try {
            FileWriter myWriter = new FileWriter("Log"+RecipientAccountNumber+RecipientSortCode+".txt");
            for (int i=0; i<LogStorage.size(); i++){
                if (i>0) {
                    myWriter.write(System.getProperty( "line.separator" ));
                }
                myWriter.write(LogStorage.get(i));
            }
            myWriter.close();
        }  catch (IOException g) {
            System.out.println("Error occurred with saving recipient account log");
            g.printStackTrace();
        }
    }
    static void
}
