package src;

import java.util.Map;
import java.util.HashMap;

class CoffeeFactory {
    private static final Map<Integer, CupCoffee> coffeeOptions = new HashMap<>();
    static {
        coffeeOptions.put(1, new CupEspresso());
        coffeeOptions.put(2, new CupLatte());
        coffeeOptions.put(3, new CupCappuccino());
    }

    public static CupCoffee create(int choice) {
        return coffeeOptions.getOrDefault(choice, null);
    }
}