
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("jobs.txt"));

        List<PrintJob> listPJ = new ArrayList<>();

        int n = 5;

        while (n-- > 0) {
            String line = sc.nextLine();
            String[] words = line.split(" ");

            if (words[0].equals("MONO")) {
                PrintJob mono = new MonoPrint(words[1], Integer.parseInt(words[2]));
                listPJ.add(mono);
            } else {
                PrintJob colour = new ColourPrint(words[1], Integer.parseInt(words[2]));
                listPJ.add(colour);
            }
        }

        for (PrintJob print : listPJ) {
            System.out.println(print.summary());
        }
    }
}
