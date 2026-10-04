
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // ========== PROBLEM 1 ==========
        List<String> playlist = new ArrayList<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("playlist.txt"));

        while (sc.hasNext()) {
            String type = sc.next();
            String song;
            int index = playlist.size();

            switch (type) {
                case "ADD":
                    song = sc.nextLine();
                    playlist.add(index, song);
                    break;
                case "INSERT":
                    index = sc.nextInt();
                    song = sc.nextLine();
                    playlist.add(index, song);
                    break;
                case "REMOVE":
                    song = sc.nextLine();
                    playlist.remove(song);
                    break;
            }
        }

        System.out.println("==== Problem 1 ====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(i + 1 + ": " + playlist.get(i));
        }

        // ========== PROBLEM 2 ==========
        Set<String> participants = new LinkedHashSet();

        sc = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        int dupeCounter = 0;
        while (sc.hasNext()) {
            String name = sc.next();

            for (String n : participants) {
                if (n.equals(name)) {
                    dupeCounter++;
                }
            }

            participants.add(name);
        }

        System.out.println("==== Problem 2 ====");
        System.out.println("Unique participants: " + participants.size());
        int numToPrint = 1;
        for (String p : participants) {
            System.out.println(numToPrint + ". " + p);
            numToPrint++;
        }
        System.out.println("Duplicate registrations: " + dupeCounter);

        // ========== PROBLEM 3 ==========
        Map<String, Integer> products = new LinkedHashMap<>();

        sc = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        int failedSales = 0;
        while (sc.hasNext()) {
            String type = sc.next();
            String productName = sc.next();
            int amount = sc.nextInt();

            int availStock = 0;

            if (products.containsKey(productName)) {
                availStock = products.get(productName);
            }

            switch (type) {
                case "ADD":
                    products.put(productName, availStock + amount);
                    break;
                default:
                    if (amount > availStock) {
                        failedSales++;
                    } else {
                        products.put(productName, availStock - amount);
                    }
                    break;
            }
        }

        System.out.println("==== Problem 3 ====");
        products.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
        System.out.println("Failed sales: " + failedSales);
    }
}
