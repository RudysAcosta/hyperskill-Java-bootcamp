package src;

import java.util.Scanner;

class MachineInventory {
    private int water, milk, coffeeBeans, cups, earned, cupPrepared;

    public MachineInventory(int water, int milk, int coffeeBeans, int cups, int earned) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.earned = earned;
        this.cupPrepared = 0;
    }

    public void processOrder(CupCoffee coffee) throws InsufficientProductException {
        if (!canPrepareCoffee(coffee)) {
            if (cups == 0) {
                throw new InsufficientProductException("Sorry, not enough cup!");
            }

            if (water < coffee.getWater()) {
                throw new InsufficientProductException("Sorry, not enough water!");
            }

            if (milk < coffee.getMilk()) {
                throw new InsufficientProductException("Sorry, not enough milk!");
            }

            if (coffeeBeans < coffee.getCoffeeBeans()) {
                throw new InsufficientProductException("Sorry, not enough coffee!");
            }
        }

        water -= coffee.getWater();
        milk -= coffee.getMilk();
        coffeeBeans -= coffee.getCoffeeBeans();
        cups--;
        earned += coffee.getCosts();
        cupPrepared++;
        System.out.println("I have enough resources, making you a coffee!");
    }

    private boolean canPrepareCoffee(CupCoffee coffee) {
        return cups >= 1 && water >= coffee.getWater() && milk >= coffee.getMilk() && coffeeBeans >= coffee.getCoffeeBeans();
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n", earned);
        earned = 0;
    }

    public void fill(Scanner scanner) {
        water += getSupply(scanner, "Write how many ml of water you want to add:");
        milk += getSupply(scanner, "Write how many ml of milk you want to add:");
        coffeeBeans += getSupply(scanner, "Write how many grams of coffee beans you want to add:");
        cups += getSupply(scanner, "Write how many disposable cups you want to add:");
    }

    public void displayStatus() {
        System.out.printf("The coffee machine has:\n%d ml of water\n%d ml of milk\n%d g of coffee beans\n%d disposable cups\n$%d of money\n", water, milk, coffeeBeans, cups, earned);
    }

    public void clear() {
        cupPrepared = 0;
        System.out.println("I have been cleaned!");
    }

    public boolean needCleaning() {
        return (cupPrepared >= 10);
    }

    private int getSupply(Scanner scanner, String message) {
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next();
        }
        int supply = scanner.nextInt();
        scanner.nextLine();
        return supply;
    }
}