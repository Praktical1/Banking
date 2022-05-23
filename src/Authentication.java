import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
    //change to Char[] if necessary
    private static String Username;
    private static String Password;
    public static String Login() {
        ArrayList<String> StaffLogins = new ArrayList<>();
        //Loads database
        try {
            File f = new File("StaffLogins.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                StaffLogins.add(myReader.nextLine());
            }
            myReader.close();
        //If database is not discovered creates a new one
        } catch (FileNotFoundException e) {
            System.out.println("Staff login database missing, creating new database");
            //Variables holding default username and password
            String DefaultAdminUsername = "admin";
            String DefaultAdminPassword = "Password123";
            //Creates new database with default admin login included
            try {
                FileWriter myWriter = new FileWriter("StaffLogins.txt");
                myWriter.write(DefaultAdminUsername);
                myWriter.write(System.getProperty( "line.separator" ));
                myWriter.write(DefaultAdminPassword);
                myWriter.close();
                System.out.println("Successfully Written File");
            } catch (IOException g) {
                System.out.println("Error occurred with writing");
                g.printStackTrace();
            }
            //Adds default login information to array for use in authentication
            StaffLogins.add(DefaultAdminUsername);
            StaffLogins.add(DefaultAdminPassword);
        }
        Scanner in = new Scanner(System.in);
        int attempts = 0;
        //Login Screen
        do {
            boolean Lock = true;
            while (Lock) {
                Username="";
                //Responsible for taking in username and password and ensures username is not blank
                while (Username.equals("")){
                    System.out.println("| Login |");
                    System.out.println(" ");
                    System.out.print("Username: ");
                    Username = in.nextLine();
                    System.out.print("Password: ");
                    Password = in.nextLine();
                    System.out.println(" ");
                    if (Username.equals("")) {
                        System.out.println("Please Enter a Username");
                        System.out.println(" ");
                    }
                }

                String Lockpass = null;
                boolean Found = false;
                for (int i=0;i<StaffLogins.size(); i=i+2) {
                    if (Username.equalsIgnoreCase(StaffLogins.get(i))) {                  //Checks if User is in login database
                        Found = true;
                        Lockpass = StaffLogins.get(i+1);                                  //Creates a variable to hold password in database to later compare against entered password
                    }
                }
                if (Found) {
                    //If username is recognised and password is correct for the account
                    if (Password.equals(Lockpass)) {
                        Lock = false;                           //Successful Login
                        System.out.println("Login successful");
                        attempts = 0;
                    //If username is recognised but password is incorrect for the account
                    } else {
                        attempts = attempts + 1;
                        if (attempts == 3){
                            System.out.println("3 attempts used, try again later");     //initial attempts used up message
                            Username = "";
                            Lock = false;
                        } else {
                            System.out.println("Incorrect username/password, Try again ("+(3-attempts)+" attempts left)");   //lets user know login is incorrect (password at fault)
                        }
                    }
                //Responsible for login attempt with unrecognised username
                } else {
                    attempts = attempts + 1;
                    if (attempts == 3){
                        System.out.println("3 attempts used, try again later");     //Attempts used up message
                        Username = "";
                        Lock = false;
                    } else {
                        System.out.println("Incorrect username/password, Try again ("+(3-attempts)+") attempts left)");   //lets user know login is incorrect (Username at fault)
                    }
                }
            }
            //If statement used to catch a login through admin account
            if (Username.equalsIgnoreCase("admin")){
                Admin.AdminMenu();
            }
        //While loop responsible for allowing admin to login into a normal staff account
        } while (Username.equalsIgnoreCase("admin"));
        return Username;
    }
}