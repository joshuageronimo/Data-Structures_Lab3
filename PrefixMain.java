import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Joshua Geronimo
 * CSC-236-01
 * LAB-3
 *
 * This is the demo class for PrefixProcess.java
 */

public class PrefixMain {

    static private final String USER_PROMPT = "Enter the name of the prefix file you want to scan: ";
    static private Scanner keyboard = new Scanner(System.in);
    static private String userInput;

    public static void main(String[] args) throws IOException {

        System.out.print(USER_PROMPT); /* prompt the user to enter the file name. */
        userInput = keyboard.nextLine(); /* get the user input. */
        validatePrefix(userInput); // validate the file
        keyboard.close(); // close scanner
    }

    public static void validatePrefix(String fileName) throws FileNotFoundException {
        try {
            Scanner prefixFile = new Scanner(new FileReader(fileName)); /* Scanner object that will read the file. */
            PrintWriter outFile = new PrintWriter("prefix.out");
            while (prefixFile.hasNextLine()) {
                String prefixExpression = prefixFile.nextLine();
                if (PrefixProcess.validatePrefixExpression(prefixExpression,1)) {
                    outFile.println("Valid Expression: " + prefixExpression);
                    String outputValue = PrefixProcess.evaluatePrefixExpression(prefixExpression);
                    outFile.println("Output Value: " + outputValue);
                    outFile.println("--------------");
                } else {
                    outFile.println("Invalid Expression: " + prefixExpression);
                }
            }

            // close scanners
            prefixFile.close();
            outFile.close();

        } catch (Exception e) {
            System.out.println("Error...");
        }
    }
}
