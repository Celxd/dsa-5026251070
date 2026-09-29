package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        Queue<String[]> ordersToProcessed = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        String[] bakso = { "Bakso", "2" };
        String[] sate = { "Sate", "1" };
        String[] soto = { "Soto", "2" };

        foodStock.add(bakso);
        foodStock.add(sate);
        foodStock.add(soto);

        String[] teh = { "EsTeh", "4" };
        String[] jeruk = { "EsJeruk", "2" };

        drinkStock.add(teh);
        drinkStock.add(jeruk);

        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        while (sc.hasNext()) {
            String[] order = { sc.next(), sc.next(), sc.next(), sc.next() };
            orders.add(order);
        }

        sc.close();

        ordersToProcessed.addAll(orders);

        while (!ordersToProcessed.isEmpty()) {
            String[] currentOrder = ordersToProcessed.poll();
            String food = currentOrder[1];
            String drink = currentOrder[2];

            String[] currentFood = null;
            String[] currentDrink = null;

            if (!food.equals("-")) {
                for (String[] checkingFood : foodStock) {
                    if (checkingFood[0].equals(food)) {
                        currentFood = checkingFood;
                    }
                }

                if (Integer.parseInt(currentFood[1]) - 1 < 0) {
                    failedOrders.add(currentOrder);
                    continue;
                } else {
                    currentFood[1] = String.valueOf(Integer.parseInt(currentFood[1]) - 1);
                }
            }

            if (!drink.equals("-")) {
                for (String[] checkingDrink : drinkStock) {
                    if (checkingDrink[0].equals(drink)) {
                        currentDrink = checkingDrink;
                    }
                }

                if (Integer.parseInt(currentDrink[1]) - 1 < 0) {
                    failedOrders.add(currentOrder);
                    continue;
                } else {
                    currentDrink[1] = String.valueOf(Integer.parseInt(currentDrink[1]) - 1);
                }
            }

            successOrders.add(currentOrder);
        }

        System.out.println("=== Successfully Processed Orders === ");
        for (String[] order : successOrders) {
            for (String word : order) {
                System.out.print(word + " ");
            }
            System.out.println();
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] food : foodStock) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] drink : drinkStock) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] fail = failedOrders.pop();
            for (String word : fail) {
                System.out.print(word + " ");
            }
            System.out.println();
        }

    }

}
