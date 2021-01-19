package learn.hotel.ui;

import learn.hotel.data.DataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleIO {

    private static final String INVALID_NUMBER
            = "[INVALID] Enter a valid number.";
    private static final String NUMBER_OUT_OF_RANGE
            = "[INVALID] Enter a number between %s and %s.";
    private static final String REQUIRED
            = "[INVALID] Value is required.";
    private static final String INVALID_DATE
            = "[INVALID] Enter a date in MM/dd/yyyy format.";

    private final Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void printf(String format, Object... values) {
        System.out.printf(format, values);
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine().replace(",", " ");

    }

    public LocalDate readLocalDate(String prompt) {
        while (true) {
            String input = readString(prompt);
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ex) {
                println(INVALID_DATE);
            }
        }
    }

    public LocalDate readLocalDateUpdate(String prompt) {
        while (true) {
            String input = readString(prompt);
            if(input.isBlank() || input == null) {
                return null;
            }
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ex) {
                println(INVALID_DATE);
            }
        }
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readRequiredString(prompt));
            } catch (NumberFormatException ex) {
                println(INVALID_NUMBER);
            }
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result >= min && result <= max) {
                return result;
            }
            println(String.format(NUMBER_OUT_OF_RANGE, min, max));
        }
    }

    public String readRequiredString(String prompt) {
        while (true) {
            String result = readString(prompt);
            if (!result.isBlank()) {
                return result;
            }
            println(REQUIRED);
        }
    }

    public void enterContinue() {
        System.out.println("Press ok to Continue");
        scanner.nextLine();
        System.out.flush();
    }
}