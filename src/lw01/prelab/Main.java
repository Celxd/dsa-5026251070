package lw01.prelab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        List<PrintJob> listPJ = new ArrayList<>();

        int n = 5;

        while (n-- > 0) {
            String printType = sc.next();
            String id = sc.next();
            int pageNum = sc.nextInt();

            if (printType.equals("MONO")) {
                PrintJob mono = new MonoPrint(id, pageNum);
                listPJ.add(mono);
            } else {
                PrintJob colour = new ColourPrint(id, pageNum);
                listPJ.add(colour);
            }
        }

        for (PrintJob print : listPJ) {
            System.out.println(print.summary());
        }

        sc.close();
    }
}
