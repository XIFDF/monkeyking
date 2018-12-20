import java.util.Stack;

public class ShewHeap<T extends Comparable<T>> {
//    根节点最大
    public ShewHeap(){
        root = null;
    }

    public class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;

        Node(T key, Node<T> left, Node<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        Node(T key) {
            this(key, null, null);
        }
    }

    private Node<T> root;

    public boolean isEmpty() {
        return root == null;
    }
//  迭代
    private Node<T> merge(Node<T> root1,Node<T> root2){
        if(root1 == null) return root2;
        if(root2 == null) return root1;

        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> r1 = root1;
        Node<T> r2 = root2;
        while(r1 != null && r2 != null){
            if(r1.key.compareTo(r2.key) > 0){
                stack.push(r1);
                r1 = r1.right;
            }else{
                stack.push(r2);
                r2 = r2.right;
            }
        }

        Node<T> r = (r1 != null) ? r1 : r2;

        while(!stack.isEmpty()){
            Node<T> node = stack.pop();
            node.right = node.left;
            node.left = r;
            r = node;
        }
        return r;
    }
//    递归 (JVM容易栈溢出,弃用)
//    private Node<T> merge(Node<T> n1, Node<T> n2) {
//        if (n1 == null) return n2;
//        if (n2 == null) return n1;
//
//        if (n1.key.compareTo(n2.key) <= 0) {
//            Node<T> tmp = n1;
//            n1 = n2;
//            n2 = tmp;
//        }
//
//        Node<T> tmp = merge(n1.right, n2);
//        n1.right = n2.left;
//        n1.left = tmp;
//
//        return n1;
//    }

    public int merge(ShewHeap<T> other) {
        if(this == other) return -1;

        this.root = merge(this.root, other.root);
        other.root = null;
        return 1;
    }

    public void insert(T key) {
        root = merge(new Node<T>(key), root);
    }

    public T deleteMax() {
        if (isEmpty()) {
            throw new Error();
        }

        T maxItem = root.key;
        root = merge(root.right, root.left);
        return maxItem;
    }

    public T getMax() {
        return root.key;
    }

}
