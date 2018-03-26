import java.util.Scanner;
import java.util.Stack;

/**
 * Joshua Geronimo
 * CSC-236-01
 * LAB-3
 */

public class PrefixProcess {

    // counter variable will be used to reference our
    // counter in validatePrefixExpression function.
    static private int counter = 1;

    static private Stack<String> prefixTokenStack = new Stack<>();
    static private int operandCounterStreak = 0;
    static private boolean isDoneEvaluating = false;
    static private String calculatedOutput = "";

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
                    // add an empty string before tokenScanner.nextLine() to parse it into a String.
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
                        validatePrefixExpression(tokenScanner.nextLine(), counter += 1);
                    } else {
                        // if there is no next value, pass an empty string instead of
                        // "scan.nextLine()" in token parameter to avoid error.
                        validatePrefixExpression("", counter += 1);
                    }
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
     * A recursive function that will evaluate a valid prefix expression.
     *
     * @param token represents the token character
     * @return the output of the prefix equation
     */
    public static String evaluatePrefixExpression(String token) {
        Scanner tokenScanner = new Scanner(token); /* will be used to scan the token string. */

        try {
            double currentOperand = tokenScanner.nextDouble(); // points to the current character in the token (operand)
            String operand = "" + currentOperand; // create a String copy of the current character.

            // if the prefixStack size is more than 2, then it's possible to do a calculation.
            if (prefixTokenStack.size() > 2) {
                // if the top of the stack is not an operator, that means we can do a calculation (2 numbers in a row)
                if (!isValidOperatorToken(prefixTokenStack.peek())) {
                    double secondOperand = currentOperand;
                    double firstOperand = Double.parseDouble(prefixTokenStack.pop());
                    // if the top of stack is an operator, do the calculation.
                    if (isValidOperatorToken(prefixTokenStack.peek())) {
                        String operator = prefixTokenStack.pop();
                        operand = calculate(operator,firstOperand,secondOperand);
                    } else { // if the top of the stack is not an operator, that means we can chain a calculation.
                        double firstChainOperand = Double.parseDouble(prefixTokenStack.pop());
                        double secondChainOperand = firstOperand;
                        String operator = prefixTokenStack.pop();
                        prefixTokenStack.push(calculate(operator,firstChainOperand,secondChainOperand));
                    }
                    calculatedOutput = operand; // calculated output to operand
                }
            }
            prefixTokenStack.push(operand); // push operand to stack


        } catch (Exception e) {
            String operator = tokenScanner.next();
            prefixTokenStack.push(operator); // push operator to stack
        }

        // if tokenScanner is not empty then do another recursive call.
        if (tokenScanner.hasNext()) {
            evaluatePrefixExpression(tokenScanner.nextLine()); // send the rest of the string
        }

        // finish doing the calculation here.
        if (!prefixTokenStack.isEmpty()) {
            double secondOperand = Double.parseDouble(prefixTokenStack.pop());
            double firstOperand = Double.parseDouble(prefixTokenStack.pop());
            String operator = prefixTokenStack.pop();
            String calculatedValue = calculate(operator,firstOperand,secondOperand);
            calculatedOutput = calculatedValue;
            if (prefixTokenStack.size() >= 2) {
                prefixTokenStack.push(calculatedValue);
            }
        } else {
            return calculatedOutput; // the answer for the prefix expression.
        }
        return "";
    }

    /**
     * This function checks if the operator
     * token is valid.
     *
     * @param operatorToken represents the token character
     * @return true if the token is equal to (+,-,*,/)
     */
    private static Boolean isValidOperatorToken(String operatorToken) {
        switch (operatorToken) {
            case "+": case "-": case "*": case "/":
                return true;
            default:
                return false;
        }
    }

    // Helper Method for evaluatePrefixExpression
    private static String calculate(String operator, Double firstOperand, Double secondOperand) {
        switch (operator) {
            case "+":
                return add(firstOperand, secondOperand);
            case "-":
                return subtract(firstOperand, secondOperand);
            case "*":
                return multiply(firstOperand, secondOperand);
            case "/":
                return divide(firstOperand, secondOperand);
            default:
                return operator;
        }
    }

    // Helper Method for calculate method
    private static String add(Double firstOperand, Double secondOperand) {
        return "" + (firstOperand + secondOperand);

    }

    // Helper Method for calculate method
    private static String subtract(Double firstOperand, Double secondOperand) {
        return "" + (firstOperand - secondOperand);
    }

    // Helper Method for calculate method
    private static String multiply(Double firstOperand, Double secondOperand) {
        return "" + (firstOperand * secondOperand);
    }

    // Helper Method for calculate method
    private static String divide(Double firstOperand, Double secondOperand) {
        return "" + (firstOperand / secondOperand);
    }
}
