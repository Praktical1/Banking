import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
    public static void AdminMenu(){
        ArrayList<String> StaffLogins = new ArrayList<>();
        try {
            File f = new File("StaffLogins.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                StaffLogins.add(myReader.nextLine());
            }
            myReader.close();
            //If database is not discovered creates a new one
        } catch (FileNotFoundException e) {

        }

    }
}
