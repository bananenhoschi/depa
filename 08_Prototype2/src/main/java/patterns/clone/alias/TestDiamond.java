package patterns.clone.alias;

// This program demonstrates, that the Java-Cloning mechanism does not work
// for diamond structures, i.e. for alias references.
public class TestDiamond {

    static class Node implements Cloneable {
        private Node left, right;
        private int val;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(Node orig) {
            this.val = orig.val;
            this.left = orig.left;
            this.right = orig.left;
        }

        @Override
        public Node clone() {
            return new Node(this);
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getVal() {
            return val;
        }
    }

    public static void main(String[] args) {
        Node n4 = new Node(4, null, null);
        Node n2 = new Node(2, null, n4);
        Node n3 = new Node(3, n4, null);
        Node n1 = new Node(1, n2, n3);

        System.out.println(n1.getLeft().getRight());
        System.out.println(n1.getRight().getLeft());
        System.out.println(n1.getLeft().getRight() == n1.getRight().getLeft());

        Node c = n1.clone();

        System.out.println(c.getLeft().getRight());
        System.out.println(c.getRight().getLeft());
        System.out.println(c.getLeft().getRight() == c.getRight().getLeft());
    }
}
