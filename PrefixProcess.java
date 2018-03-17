import java.util.Scanner;

/**
 * Joshua Geronimo
 * CSC-236-01
 * LAB-3
 */

public class PrefixProcess {

    // counter variable will be used to reference our
    // counter in validatePrefixExpression function.
    static int counter = 1;

    /**
     * A recursive function that will check whether
     * a prefix expression is valid or not.
     *
     * @param token represents the token character
     * @param number is the counter (will be used to get out of the recursion)
     * @return true if the counter is equal to zero.
     */
    public static Boolean validatePrefixExpression(String token, int number) {

        counter = number; /* will keep track of the counter */
        Scanner tokenScanner = new Scanner(token); /* will be used to scan the token string. */

        // Goes through the prefix expression.
        if (tokenScanner.hasNext()) {
            try {
                // if next is a double, then we know it's an operand.
                tokenScanner.nextDouble();
                if (tokenScanner.hasNext()) {
                    validatePrefixExpression("" + tokenScanner.nextLine(), counter -= 1);
                } else {
                    // if there is no next value, pass an empty string instead of
                    // "scan.nextLine()" in token parameter to avoid error.
                    validatePrefixExpression("", counter -= 1);
                }

            } catch (Exception e) {
                // Since it through an error, it's safe to say that this is an operator.
                String operator = tokenScanner.next();
                // validate operator to continue validation.
                if (isValidOperatorToken(operator)) {
                    if (tokenScanner.hasNext()) {
                        validatePrefixExpression("" + tokenScanner.nextLine(), counter += 1);
                    } else {
                        // if there is no next value, pass an empty string instead of
                        // "scan.nextLine()" in token parameter to avoid error.
                        validatePrefixExpression("", counter += 1);
                    }
                } else {
                    return false; /* because the operator was no valid. */
                }
            }
        }

        // If the counter is equal to 0, then the prefix is valid.
        if (counter == 0 && !tokenScanner.hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This function checks if the operator
     * token is valid.
     *
     * @param operatorToken represents the token character
     * @return true if the token is equal to (+,-,*,/)
     */
    public static Boolean isValidOperatorToken(String operatorToken) {
        switch (operatorToken) {
            case "+": case "-": case "*": case "/":
                return true;
            default:
                return false;
        }
    }
}
