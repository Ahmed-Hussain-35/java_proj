import java.util.Scanner;

public class AgeDOBCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for today's date
        System.out.println("Enter today's date (use format YYYY-MM-DD, DD-MM-YYYY, or MM-DD-YYYY):");
        String todayInput = scanner.nextLine();

        // Ask for the format of the input date
        System.out.println("Enter the format of today's date (1 for YYYY-MM-DD, 2 for DD-MM-YYYY, 3 for MM-DD-YYYY):");
        int formatOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Prompt for the operation (DOB or AGE)
        System.out.println("Choose your option: 1 for DOB or 2 for AGE:");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Prompt for the input date or age based on the chosen option
        System.out.println("Enter the date or age as per the option selected (same format as today's date):");
        String input = scanner.nextLine();

        String[] todayParts = parseDate(todayInput, formatOption);
        String[] inputParts = parseDate(input, formatOption);

        if (todayParts == null || inputParts == null || todayParts.length != 3 || inputParts.length != 3) {
            System.out.println("Invalid date format provided.");
            return;
        }

        int todayYear = Integer.parseInt(todayParts[0]);
        int todayMonth = Integer.parseInt(todayParts[1]);
        int todayDay = Integer.parseInt(todayParts[2]);

        int inputYear = Integer.parseInt(inputParts[0]);
        int inputMonth = Integer.parseInt(inputParts[1]);
        int inputDay = Integer.parseInt(inputParts[2]);

        if (option == 2) {
            int years = todayYear - inputYear;
            int months = todayMonth - inputMonth;
            int days = todayDay - inputDay;

            if (months < 0) {
                years--;
                months += 12;
            }
            if (days < 0) {
                months--;
                days += getDaysInMonth(todayMonth - 1, todayYear);
            }

            System.out.println("Age: " + years + " Years, " + months + " Months, " + days + " Days");
        } else if (option == 1) {
            int years = inputYear;
            int months = todayMonth;
            int days = todayDay;

            System.out.printf("Date of Birth: %02d-%02d-%04d\n", days, months, todayYear - years);
        } else {
            System.out.println("Invalid option");
        }

        scanner.close();
    }

    private static String[] parseDate(String date, int format) {
        String[] dateParts = date.split("-");
        if (dateParts.length != 3) return null;

        switch (format) {
            case 1: // YYYY-MM-DD
                return dateParts; // Already in correct format
            case 2: // DD-MM-YYYY
                return new String[]{dateParts[2], dateParts[1], dateParts[0]}; // Rearranged
            case 3: // MM-DD-YYYY
                return new String[]{dateParts[2], dateParts[0], dateParts[1]}; // Rearranged
            default:
                return null; // Invalid format
        }
    }

    private static int getDaysInMonth(int month, int year) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return daysInMonths[month];
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
