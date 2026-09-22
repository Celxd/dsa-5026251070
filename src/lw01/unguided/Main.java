package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(lw01.unguided.Main.class.getResourceAsStream("washes.txt"));

        int n = sc.nextInt();
        WashService[] services = new WashService[n];
        int[] unitsArr = new int[n];

        int index = 0;

        while (n-- > 0) {
            String type = sc.next();
            String id = sc.next();
            int days = sc.nextInt();
            int units = sc.nextInt();

            WashService currentService;

            if (type.toUpperCase().equals("MOTORCYCLE")) {
                currentService = new MotorycleWash(id, days);
            } else {
                currentService = new CarWash(id, days);
            }

            services[index] = currentService;
            unitsArr[index] = units;
            index++;
        }

        for (int i = 0; i < services.length; i++) {
            System.out.println(services[i].summary(unitsArr[i]));
        }

        sc.close();
    }
}
