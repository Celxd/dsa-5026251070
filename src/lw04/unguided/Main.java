package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        Bubble(parcels, 0, 2);
        int[] twoPassRes = parcels.clone();

        Bubble(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        for (int num : twoPassRes) {
            System.out.print(num + " ");
        }
        System.out.println("");

        System.out.println("==== Fully Sorted ====");
        for (int num : parcels) {
            System.out.print(num + " ");
        }
        System.out.println("");

        System.out.println("==== Search Results ====");
        for (int target : targets) {
            int steps = 0;
            int foundIndex = -1;

            int low = 0;
            int high = parcels.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                steps++;

                if (parcels[mid] == target) {
                    foundIndex = mid;
                    break;
                } else if (parcels[mid] < target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (foundIndex == -1) {
                System.out.println(target + ": NOT FOUND (Steps: " + steps + ")");
            } else {
                System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
            }

        }

    }

    public static void Bubble(int[] a, int startPass, int endPass) {
        for (int i = 0; i < endPass; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
        
}