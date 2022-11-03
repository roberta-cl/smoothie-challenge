import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ingredients {
    private static final Scanner input = new Scanner(System.in);

    private static ArrayList<Smoothie> menu;

    public static void main(String[] args) {
        menu = new ArrayList<>();

        menu.add(new Smoothie("Classic", Stream.of("strawberry", "banana", "pineapple", "mango", "peach", "honey").collect(Collectors.toList())));
        menu.add(new Smoothie("Freezie", Stream.of("blackberry", "blueberry", "black currant", "grape juice", "frozen yogurt").collect(Collectors.toList())));
        menu.add(new Smoothie("Greenie", Stream.of("green apple" , "lime", "avocado", "spinach", "ice", "apple juice").collect(Collectors.toList())));
        menu.add(new Smoothie("Just Desserts", Stream.of("banana", "ice cream", "chocolate", "peanut", "cherry").collect(Collectors.toList())));

        menuOptions();
        String request = input.nextLine();
        getOrder(request, convertArrayListToTreeMap(menu));
    }

    private static TreeMap<String, List<String>> convertArrayListToTreeMap(ArrayList<Smoothie> arrayList) {
        TreeMap<String, List<String>> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(Smoothie smoothie : arrayList) {
            treeMap.put(smoothie.getName(), smoothie.getIngredients());
        }
        return treeMap;
    }

    public static void menuOptions() {
        System.out.println("""
                Welcome to Functional Smoothies Inc.!
                To remove an ingredient from your order, you can write a - (minus) sign before them. If that's the case, please separate your request with , (comma) signs.
                Example: Classic,-strawberry,-banana (a Classic smoothie without strawberry and banana)
                """);
        for(Smoothie s : menu) {
            System.out.println(s.getId() + "- " + s.getName() + ": " + String.join(", ", s.getIngredients()));
        }
        System.out.println("\n" + "Place your order: ");
    }

    public static void getOrder(String input, TreeMap<String, List<String>> menu) {
        String[] inputSplit = input.toLowerCase().trim().split(",");
        String inputSmoothieName = inputSplit[0];
        int inputLength = inputSplit.length;

        List<String> removeIngredients = Arrays.stream(inputSplit).filter(x -> x.startsWith("-"))
                .map(x -> x.replace("-", "")).toList();

        if(menu.containsKey(inputSmoothieName)) {
            List<String> menuSmoothieIngredients = menu.get(inputSmoothieName);
            menuSmoothieIngredients.removeAll(removeIngredients);
            Collections.sort(menuSmoothieIngredients);

            if(inputLength - 1 > removeIngredients.size()) {
                throw new IllegalArgumentException("You can't add items to your smoothie!");
            } else if(menuSmoothieIngredients.size() == 0) {
                throw new IllegalArgumentException("You removed all of the ingredients from your smoothie!");
            }
            else {
                System.out.println("You ordered the " + inputSmoothieName.toUpperCase() + " smoothie with: " + String.join(", ", menuSmoothieIngredients));
            }
        }
        else {
            throw new IllegalArgumentException("Your order is either empty or not on the Menu!");
        }
    }
}
