package lw03.unguided;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> regis = new LinkedHashSet<>();

        Scanner sc = new Scanner(Main.class.getResourceAsStream("registrations.txt"));

        while (sc.hasNext()) {
            regis.add(sc.nextLine());
        }

        sc = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        int rejected = 0;
        Set<String> checkedIns = new LinkedHashSet<>();

        System.out.println("===== Event Check-In Results =====");

        while (sc.hasNext()) {
            String checkIn = sc.nextLine();

            if (!regis.contains(checkIn)) {
                System.out.println(checkIn + ": Rejected (not registered)");
                rejected++;
                continue;
            }

            if (checkedIns.contains(checkIn)) {
                System.out.println(checkIn + ": Rejected (already checked in)");
                rejected++;
                continue;
            }

            checkedIns.add(checkIn);
            System.out.println(checkIn + ": Checked in");

        }
        sc.close();

        System.out.println("");
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + regis.size());
        System.out.println("Successful check-ins: " + checkedIns.size());
        System.out.println("Absent students: " + (regis.size() - checkedIns.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}