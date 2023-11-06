package mealplanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
      String s1 = ask();
      String s2 = askName();
      String s3 = askForIngredients();
      output(s1, s2, s3);
    }

    public static String ask() {

      Scanner scanner = new Scanner(System.in);
      System.out.println("Which meal do you want to add (breakfast, lunch, dinner)?");

        return scanner.nextLine();
    }

    public static String askName() {

      Scanner scanner = new Scanner(System.in);
      System.out.println("Input the meal's name:");

        return scanner.nextLine();
    }

    public static String askForIngredients() {

      Scanner scanner = new Scanner(System.in);
      System.out.println("Input the ingredients:");

        return scanner.nextLine();
    }

    public static void ingredients(String listOfIngredients) {

      String[] ingredients = listOfIngredients.split(",");
      for (String w : ingredients) {
        System.out.println(w);
      }

    }

    public static void output(String s1, String s2, String s3) {

      System.out.println("Category: " + s1);
      System.out.println("Name: " + s2);
      System.out.println("Ingredients:");
      ingredients(s3);
      System.out.println("The meal has been added!");
    }
}
