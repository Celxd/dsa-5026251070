package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> listTransactions;
        LinkedList<String[]> custData;
        Queue<String[]> queueTransactions;
        Stack<String[]> failedTransactions;

        try (Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"))) {
            listTransactions = new LinkedList<>();
            custData = new LinkedList<>();
            queueTransactions = new LinkedList<>();
            failedTransactions = new Stack<>();

            while (sc.hasNext()) {
                String name = sc.next();
                String type = sc.next();
                String num = sc.next();
                String[] transaction = { name, type, num };
                listTransactions.add(transaction);

                // Saves new cust
                boolean customerExists = false;
                for (String[] newCust : custData) {
                    if (newCust[0].equals(name)) {
                        customerExists = true;
                        break;
                    }
                }

                if (!customerExists) {
                    String[] custToBeAdded = { name, "0" };
                    custData.add(custToBeAdded);
                }
            }
        }

        // move transactions to queue
        for (String[] transactionData : listTransactions) {
            queueTransactions.add(transactionData);
        }

        // processing transactions
        while (!queueTransactions.isEmpty()) {
            String[] currentTransaction = queueTransactions.poll();
            String[] currentCust = new String[2];

            for (String[] cust : custData) {
                if (cust[0].equals(currentTransaction[0])) {
                    currentCust = cust;
                }
            }

            int total = 0;

            if (currentTransaction[1].equalsIgnoreCase("DEPOSIT")) {
                total = Integer.parseInt(currentCust[1]) +
                        Integer.parseInt(currentTransaction[2]);

            } else if (currentTransaction[1].equalsIgnoreCase("WITHDRAW")) {
                total = Integer.parseInt(currentCust[1]) -
                        Integer.parseInt(currentTransaction[2]);
            }

            if (total < 0) {
                failedTransactions.add(currentTransaction);
            } else {
                currentCust[1] = String.valueOf(total);
            }
        }

        // print everything
        System.out.println("=== Final Balances ===");
        for (String[] custToPrint : custData) {
            System.out.println(custToPrint[0] + ": " + custToPrint[1]);
        }

        System.out.println();
        System.out.println("=== Failed Transactions ===");
        for (String[] transactionsToPrint : failedTransactions) {
            System.out.println(transactionsToPrint[0] + " " + transactionsToPrint[1] + " " + transactionsToPrint[2]);
        }
    }
}
