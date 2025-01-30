package src;

import java.util.Scanner;


public class CoffeeMachine {
    private static final MachineInventory inventory = new MachineInventory(400, 540, 120, 9, 550);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            switch (promptAction(scanner)) {
                case "buy":
                    processPurchase(scanner);
                    break;
                case "fill":
                    inventory.fill(scanner);
                    break;
                case "take":
                    inventory.takeMoney();
                    break;
                case "remaining":
                    inventory.displayStatus();
                    break;
                case "clean":
                    inventory.clear();
                    break;
                case "exit":
                    running = false;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    private static String promptAction(Scanner scanner) {
        System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
        return scanner.nextLine().trim().toLowerCase();
    }

    private static void processPurchase(Scanner scanner) {
        if (inventory.needCleaning()) {
            System.out.println("I need cleaning!");
            return;
        }

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("back")) return;

        try {
            int option = Integer.parseInt(choice);
            CupCoffee coffee = CoffeeFactory.create(option);
            inventory.processOrder(coffee);
        } catch (NumberFormatException | InsufficientProductException e) {
            System.out.println(e.getMessage());
        }
    }
}



