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
                int res = ReduceStock(foodStock, food, currentFood, currentOrder, failedOrders);
                if (res < 0)
                    continue;
            }

            if (!drink.equals("-")) {
                int res = ReduceStock(drinkStock, drink, currentDrink, currentOrder, failedOrders);
                if (res < 0)
                    continue;
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

    public static int ReduceStock(LinkedList<String[]> genericStock, String generic, String[] currentGeneric,
            String[] currentOrder,
            Stack<String[]> failedOrders) {
        for (String[] checkingDrink : genericStock) {
            if (checkingDrink[0].equals(generic)) {
                currentGeneric = checkingDrink;
            }
        }

        if (Integer.parseInt(currentGeneric[1]) - 1 < 0) {
            failedOrders.add(currentOrder);
            return -1;
        } else {
            currentGeneric[1] = String.valueOf(Integer.parseInt(currentGeneric[1]) - 1);
            return 0;
        }
    }
}
