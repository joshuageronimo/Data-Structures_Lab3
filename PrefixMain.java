import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PrefixMain {

    static private final String USER_PROMPT = "Enter the name of the prefix file you want to scan: ";
    static private Scanner keyboard = new Scanner(System.in);
    static private String userInput;

    public static void main(String[] args) throws IOException {

//        System.out.print(USER_PROMPT); /* prompt the user to enter the file name. */
//        userInput = keyboard.nextLine(); /* get the user input. */
        validatePrefix("prefix.txt");


    }


    public static void validatePrefix(String fileName) throws FileNotFoundException {
        Scanner prefixFile = new Scanner(new FileReader(fileName)); /* Scanner object that will read the file. */
        while (prefixFile.hasNextLine()) {
            PrefixProcess.validateTokens(prefixFile.nextLine(),1);
        }
    }
}
