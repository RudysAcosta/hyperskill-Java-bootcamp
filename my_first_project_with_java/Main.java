import java.util.Scanner;

public class Main {

    private static final String[] PRODUCT_NAMES = {
        "Bubblegum", "Toffee", "Ice cream", "Milk chocolate", "Doughnut", "Pancake"
    };

    private static final int[] EARNED_AMOUNTS = {
        202, 118, 2250, 1680, 1075, 80
    };

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        printEarnings(PRODUCT_NAMES, EARNED_AMOUNTS);
        System.out.println();

        int totalEarned = calulateTotal(EARNED_AMOUNTS);
        System.out.printf("Income: $%d\n", totalEarned);
        
        int staffExpenses = getExpense(scanner, "Staff expenses:");
        int otherExpenses = getExpense(scanner, "Other expenses:");

        int netIncome = totalEarned - (staffExpenses + otherExpenses);

        System.out.printf("Net income: $%d\n", netIncome);

    }
 
    private static int calulateTotal(int[] amounts) {
        int total = 0;

        for(int amount : amounts) {
            total += amount;
        }

        return total;
    }

    private static void printEarnings(String[] names, int[] amounts) {
        System.out.println("Earned amount:");

        for (int i = 0; i < PRODUCT_NAMES.length; i++) {
            System.out.printf("%s: $%d\n", names[i], amounts[i]);
        }
    }

    private static int getExpense(Scanner scanner, String message) {
        System.out.println(message);

        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }

        return scanner.nextInt();
    }
}