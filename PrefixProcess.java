import java.util.Scanner;

public class PrefixProcess {


    public static Boolean validateTokens(String token, int counter) {

        Scanner scan = new Scanner(token);

        if (scan.hasNext()) {
            String prefixToken = scan.next();
            System.out.println(prefixToken);
            if (prefixToken.equals("1")) {
                return true;
            } else {
                validateTokens(scan.nextLine(),1);
            }
        }
        return false;
    }

    // return true if == (+,-,*,/)
    public static Boolean hasValidOperatorToken(String operatorToken) {
        switch (operatorToken) {
            case "+": case "-": case "*": case "/":
                return true;
            default:
                return false;
        }
    }
}
