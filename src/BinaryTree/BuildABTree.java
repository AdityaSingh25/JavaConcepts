package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BuildABTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {

        static int idx = -1;

        public static Node buildTree(int nodes[]) {

            idx++;

            // base case
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        public static void preorder(Node root) {
            if (root == null) {
                System.out.print(-1 + " ");
                return;
            }
            assert root != null;
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void inorder(Node root) {
            if (root == null) {
                System.out.print(-1 + " ");
                return;
            }
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }

        public static void levelOrder(Node root) {
            if (root == null) {
                return;
            }

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {

                Node currentNode = q.remove();

                if (currentNode == null) {
                    //print new line
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null); // again add null for the next line to print
                    }
                } else {
                    System.out.print(currentNode.data + " ");
                    if (currentNode.left != null) {
                        q.add(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        q.add(currentNode.right);
                    }
                }
            }
        }

        public static int printNumberOfNodes(Node root){
            if(root == null){
                return 0;
            }

            int lstNodes = printNumberOfNodes(root.left);
            int rstNodes = printNumberOfNodes(root.right);
            return lstNodes + rstNodes +1;
        }

        public static void main(String args[]) {
            int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

            Node root = BinaryTree.buildTree(nodes);

            assert root != null;
            //System.out.print(root.data);

            BinaryTree.preorder(root);

            System.out.println("Inorder is : ");
            BinaryTree.inorder(root);

            System.out.println();
            System.out.println("Level order is : ");
            BinaryTree.levelOrder(root);

            System.out.println("no. of nodes : ");
            System.out.print(BinaryTree.printNumberOfNodes(root));
        }
    }
}
