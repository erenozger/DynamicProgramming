import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static String inputJsonFile;
    public static Assignment[] assignments;

    public static void main(String[] args) throws IOException {
        try{
            PrintStream o = new PrintStream(new File("output.txt"));
            System.setOut(o);
        }catch (IOException ex){
            System.out.println("Error writing file '" + "output.txt" + "'");
        }
        try {
            inputJsonFile = args[0];

        } catch (Exception e) {
            System.out.println(e);
        }
        if(inputJsonFile != null){
            assignments = readFile(inputJsonFile);
            if(assignments != null){
                Arrays.sort(assignments);
                for(Assignment assignment:assignments){
                    System.out.println(assignment);
                }

                Scheduler scheduler = new Scheduler(assignments);



            }

        }

    }

    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();

        try {
            /* The json file is read and saved into the array. Gson library can save objects as ready. */
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Assignment[] assignmentArray = gson.fromJson(bufferedReader, Assignment[].class);
            bufferedReader.close();
            return assignmentArray;

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + filename + "'");
        }
        return null;
    }

    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {

    }
}
