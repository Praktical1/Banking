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
        boolean AdminLoggedIn = true;
        Scanner in = new Scanner(System.in);
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
                System.out.println("Please input Bank name, branch in the following format \"Bank/Branch\"");
                System.out.println("Please input sort code for Current Accounts");
            //Meets condition required to exit the loop for admin menu
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
}
