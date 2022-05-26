import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /*public static ArrayList Populate(String type, ArrayList list) {
        switch (type) {
            case "ISA" -> {
                try {
                    int limit = Limit(type);
                    File f = new File(type + ".txt");
                    Scanner myReader = new Scanner(f);
                    int index = 0;
                    ArrayList<ISA> Temp = new ArrayList<>();
                    while (myReader.hasNextLine()) {
                        Temp.add(new ISA(Integer.valueOf(myReader.nextLine()), myReader.nextLine(), Integer.valueOf(myReader.nextLine()),new Bank(Integer.valueOf(myReader.nextLine()),Integer.valueOf(myReader.nextLine()),Integer.valueOf(myReader.nextLine()),myReader.nextLine()),myReader.nextLine(),));

                    }


                    index++;
                    if (index == limit) {
                        index = 0;
                        list.add(Temp);
                        Temp = new ArrayList<>();
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {                                         //If database is not discovered creates a new one
                    System.out.println(type + " database missing, creating new empty database");
                    //Variables holding sample?

                    //Creates new database with default admin login included
                    try {
                        FileWriter myWriter = new FileWriter(type + ".txt");
                        myWriter.close();
                        System.out.println("Successfully Written File");
                    } catch (IOException g) {
                        System.out.println("Error occurred with writing");
                        g.printStackTrace();
                    }
                    //Adds samples?

                }
            }
        }
    }
    public static void Save(String type, ArrayList list) {
        try {
            FileWriter myWriter = new FileWriter(type+".txt");
            for (int i=0;i<list.size(); i++){
                ArrayList Temp = new ArrayList();
                if (i>0) {
                    myWriter.write(System.getProperty( "line.separator" ));
                }
                for (int j=0;j<Temp.size(); j++) {
                    if (j>0) {
                        myWriter.write(System.getProperty( "line.separator" ));
                    }
                    if (!(Temp.get(j)).getClass().getName().equals("String")){
                        String TempOfTemp = Temp.get(j).toString();
                        myWriter.write(TempOfTemp);
                    } else if ((Temp.get(j)).getClass().getName().equals("Integer")){
                        String TempOfTemp = Temp.get(j).toString();
                        myWriter.write(TempOfTemp);
                    } else if ((Temp.get(j)).getClass().getName().equals("String[]")){
                        String[] TempOfTemp = (String[])Temp.get(j);
                        for (int z=0;z<3;z++) {
                            myWriter.write(TempOfTemp[z]);
                        }
                    } else {
                        System.out.println("Error with saving, Help!!!");
                    }
                }
            }
            myWriter.close();
        }  catch (IOException g) {
            System.out.println("Error occurred with saving new staff account");
            g.printStackTrace();
        }
    }
    public static int Limit(String type) {
        switch (type) {
            case "ISA", "Business" -> {
                return 7;
            }
            case "Current", "Customer" -> {
                return 6;
            }
            default -> {
                return 0;
            }
        }
    }
    public static ArrayList Arraycreator(String type) {

    }*/
}
