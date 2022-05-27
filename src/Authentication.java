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
                myWriter.write(DefaultAdminUsername+"\n");
                myWriter.write(DefaultAdminPassword+"\n");
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
                boolean AdminLoggedIn = true;
                in = new Scanner(System.in);
                while (AdminLoggedIn) {
                    System.out.println(" ");
                    System.out.println("| Admin |");
                    System.out.println(" ");
                    System.out.println("[1] Create User");
                    System.out.println("[2] Delete User");
                    System.out.println("[3] Add Bank");
                    System.out.println("[4] Log Out");
                    System.out.println("Please choose option 1, 2, 3 or 4");
                    String MenuChoice = in.nextLine();
                    //Admin menu option responsible for adding user
                    if (MenuChoice.equals("1")) {
                        boolean Found = false;
                        String NewUser = "";
                        do {
                            Found = false;
                            System.out.println("New Username:");
                            while (NewUser.equals("")) {
                                NewUser = in.nextLine();                                            //New Username input
                                if (NewUser.equals("")) {
                                    System.out.println("Please Enter a Username");
                                }
                            }
                            for (int i = 0; i < StaffLogins.size(); i = i + 2) {                    //for loop containing check to see if new username is already in database
                                if (NewUser.equalsIgnoreCase(StaffLogins.get(i))) {
                                    Found = true;
                                    System.out.println("Please choose another username");           //Once new username is confirmed to already exist returns error message before sending them back to start.
                                    NewUser = "";
                                }
                            }
                        } while (Found);
                        System.out.println("New Password:");
                        String NewPass = in.nextLine();                                             //New Password input
                        System.out.println("Confirm Password");
                        String Passconfirm = in.nextLine();                                         //Password confirm
                        if (NewPass.equals(Passconfirm)) {
                            System.out.println("User Account created");                             //Creates a new user account before sending them to login screen
                            StaffLogins.add(NewUser);
                            StaffLogins.add(NewPass);
                        } else {
                            System.out.println("Passwords do not match, Please try again");         //Error message when new password doesn't match password confirmation
                        }
                        //Admin menu option responsible for deleting users
                    } else if (MenuChoice.equals("2")) {
                        String DeleteUser = "";
                        System.out.println("Username to delete:");
                        while (DeleteUser.equals("")) {
                            DeleteUser = in.nextLine();
                            if (DeleteUser.equals("")) {
                                System.out.println("Please Enter a Username to delete");
                            }
                        }
                        for (int i = 0; i < StaffLogins.size(); i = i + 2) {                                            //for loop containing check to see if username is in database
                            if (DeleteUser.equalsIgnoreCase(StaffLogins.get(i))) {
                                boolean DeleteConfirmed = false;
                                while (!DeleteConfirmed) {
                                    System.out.println("Are you sure you would like to delete user " + DeleteUser);     //Confirmation to delete user
                                    System.out.println("[Y] or [N]?");
                                    String ConfirmDelete = in.nextLine();
                                    if (ConfirmDelete.equalsIgnoreCase("Y")) {
                                        StaffLogins.remove(i);
                                        StaffLogins.remove(i);
                                        DeleteConfirmed = true;
                                    } else if (ConfirmDelete.equalsIgnoreCase("N")) {
                                        DeleteConfirmed = true;
                                    } else {
                                        System.out.println("Invalid input");
                                    }
                                }
                            }
                        }

                    } else if (MenuChoice.equals("3")) {
                        Main_Program.CreateBank();
                    } else if (MenuChoice.equals("4")) {
                        AdminLoggedIn = false;
                        //Catches an input that does not meet requirements of the menu options
                    } else {
                        System.out.println("Invalid input, please choose either \"1\", \"2\" or \"3\"");                //Informs user that input doesn't match requirements
                    }
                    //Saving updated staff login information
                    try {
                        FileWriter myWriter = new FileWriter("StaffLogins.txt");
                        for (int i=0;i<StaffLogins.size(); i++){
                            if (i>0) {
                                myWriter.write(System.getProperty( "line.separator" ));
                            }
                            myWriter.write(StaffLogins.get(i));
                        }
                        myWriter.close();
                    }  catch (IOException g) {
                        System.out.println("Error occurred with saving new staff account");
                        g.printStackTrace();
                    }
                }
            }
        //While loop responsible for allowing admin to login into a normal staff account
        } while (Username.equalsIgnoreCase("admin"));
        return Username;
    }
}