package mealplanner;

import java.util.*;

enum Category {
    BREAKFAST("breakfast"),

    LUNCH("lunch"), DINNER("dinner");

    private final String text;

    Category(String category) {
        this.text = category;
    }

    public String getCategoryName() {
        return this.text;
    }

    public static Category fromString(String text) {
        for (Category c : Category.values()) {
            if (c.text.equalsIgnoreCase(text)) {
                return c;
            }
        }
        return null;
    }
}

enum Action {
    ADD("add"), SHOW("show"), EXIT("exit");

    private final String text;

    Action(String actionName) {
        this.text = actionName;
    }

    public static Action fromString(String text) {
        for (Action a : Action.values()) {
            if (a.text.equalsIgnoreCase(text)) {
                return a;
            }
        }
        return null;
    }
}

public class Main {
    private static final Map<Category, String> categoryNameMap = new LinkedHashMap<>();
    private static final Map<Category, List<String>> categoryIngredientsMap = new HashMap<>();

    private static void handleAdd(Scanner sc) {
        Category category = null;
        while (category == null) {
            System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");
            category = Category.fromString(sc.nextLine());
            if (category == null) {
                System.out.println("Wrong meal category! Choose from: breakfast, lunch, dinner.");
            }
        }
        System.out.println("Input the meal's name:");
        while (true) {
            String nameInput = sc.nextLine();
            if (nameInput.matches("[a-zA-Z]+\\s*[a-zA-Z\\s*]*")) {
                categoryNameMap.put(category, nameInput);
                break;
            } else {
                System.out.println("Wrong format. Use letters only!");
            }
        }
        List<String> categoryIngredients = categoryIngredientsMap.get(category);
        System.out.println("Input the ingredients:");
        boolean flag = true;
        while (flag) {
            flag = false;
            String ingredientsInput = sc.nextLine();
            String[] ingredientsArray = ingredientsInput.split(",");
            for (String ingredient : ingredientsArray) {
                String trimmedIngredient = ingredient.trim();
                if (!trimmedIngredient.matches("[a-zA-Z]+[a-zA-Z\\s*]*")) {
                    System.out.println("Wrong format. Use letters only!");
                    flag = true;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            for (String ingredient : ingredientsArray) {
                String trimmedIngredient = ingredient.trim();
                categoryIngredients.add(trimmedIngredient);
            }
        }
        System.out.println("The meal has been added!");
    }

    private static void handleShow() {
        if (categoryNameMap.isEmpty()) {
            System.out.println("No meals saved. Add a meal first.");
            return;
        }
        for (var entry : categoryNameMap.entrySet()) {
            System.out.println("Category: " + entry.getKey().getCategoryName());
            System.out.println("Name: " + entry.getValue());
            System.out.println("Ingredients:");
            for (String ingredient : categoryIngredientsMap.get(entry.getKey())) {
                System.out.println(ingredient.trim());
            }
        }
    }

    public static void main(String[] args) {
        categoryIngredientsMap.put(Category.BREAKFAST, new ArrayList<>());
        categoryIngredientsMap.put(Category.LUNCH, new ArrayList<>());
        categoryIngredientsMap.put(Category.DINNER, new ArrayList<>());

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("What would you like to do (add, show, exit)?");
                Action action = Action.fromString(sc.nextLine());
                if (action == null) {
                    continue;
                }
                switch (action) {
                    case ADD -> handleAdd(sc);
                    case SHOW -> handleShow();
                    case EXIT -> {
                        System.out.println("Bye!");
                        return;
                    }
                }
            }
        }
    }
}
