import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;

    }
    public static int getInt(Scanner pipe, String prompt) {
        System.out.print(prompt);
        while (!pipe.hasNextInt()) {
            String trash = pipe.nextLine();
            System.out.print(prompt);
        }
        int value = pipe.nextInt();
        pipe.nextLine();
        return value;
    }
    public static double getDouble(Scanner pipe, String prompt) {
        System.out.print(prompt);
        while (!pipe.hasNextDouble()) {
            String trash = pipe.nextLine();
            System.out.print(prompt);
        }
        double value = pipe.nextDouble();
        pipe.nextLine();
        return value;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        String fullPrompt = prompt + " [" + low + " - " + high + "]: ";
        System.out.print(fullPrompt);
        while (!pipe.hasNextInt()) {
            String trash = pipe.nextLine();
            System.out.print(fullPrompt);
        }
        int value = pipe.nextInt();
        pipe.nextLine();
        while (value < low || value > high) {
            System.out.print(fullPrompt);
            while (!pipe.hasNextInt()) {
                String trash = pipe.nextLine();
                System.out.print(fullPrompt);
            }
            value = pipe.nextInt();
            pipe.nextLine();
        }
        return value;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result = 0.0;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                if (result >= low && result <= high) {
                    inputValid = true;
                } else {
                    System.out.println("Error: Input must be within [" + low + " - " + high + "].");
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("Error: Invalid input. Please enter a valid decimal number.");
            }
        }


        pipe.nextLine();

        return result;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean result = false;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print(prompt + " [Y/N]: ");
            String input = pipe.nextLine();
            if (input.equalsIgnoreCase("y")) {
                result = true;
                inputValid = true;
            } else if (input.equalsIgnoreCase("n")) {
                result = false;
                inputValid = true;
            } else {
                System.out.println("Error: Invalid input. Please enter Y or N.");
            }
        }

        return result;
    }
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input;
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher;

        do {
            System.out.print(prompt);
            input = pipe.nextLine().trim();
            matcher = pattern.matcher(input);
        } while (!matcher.matches());

        return input;
    }
    public static void prettyHeader(String msg) {
        int totalLength = 60;
        int msgLength = msg.length();
        int asteriskLength = (totalLength - msgLength - 6) / 2;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalLength; i++) {
            sb.append("*");
        }
        sb.append("\n");

        sb.append("***");
        for (int i = 0; i < asteriskLength; i++) {
            sb.append(" ");
        }
        sb.append(msg);
        for (int i = 0; i < asteriskLength; i++) {
            sb.append(" ");
        }
        if (msgLength % 2 != 0) {
            sb.append(" ");
        }
        sb.append("***\n");

        for (int i = 0; i < totalLength; i++) {
            sb.append("*");
        }
        System.out.println(sb.toString());
    }

}
