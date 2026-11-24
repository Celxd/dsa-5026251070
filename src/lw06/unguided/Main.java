package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        // Write your main code here
        Scanner line = new Scanner(read.nextLine());

        BinarySearchTree bst = new BinarySearchTree();
        while (line.hasNextInt()) {
            bst.insert(line.nextInt());
        }

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        int search = read.nextInt();
        System.out.println("Search " + search + " : " + bst.search(search));
        System.out.println("Nodes : " + bst.countNodes());
        System.out.println("Leaves : " + bst.countLeaves());

        read.close();
    }
}