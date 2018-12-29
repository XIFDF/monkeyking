import java.util.Stack;

//较为完整的斜堆类定义，问题解决无需使用
public class ShewHeap<T extends Comparable<T>> {
//    根节点最大
    public ShewHeap(){
        root = null;
    }

    private Node<T> root;

    public class Node<T> {
        T key;
        Node father;
        Node left;
        Node right;

        Node(T key, Node father, Node left, Node right) {
            this.key = key;
            this.father = father;
            this.left = left;
            this.right = right;
        }

        Node(T key) {
            this(key, null, null, null);
        }

        public Node findRoot() {
            Node root = this;
            while (root.father != null) {
                root = root.father;
            }
            return root;
        }
    }
    public boolean isEmpty() {
        return root == null;
    }
//  迭代
//    private Node<T> merge(Node<T> root1,Node<T> root2){
//        if(root1 == null) return root2;
//        if(root2 == null) return root1;
//
//        Stack<Node<T>> stack = new Stack<Node<T>>();
//        Node<T> r1 = root1;
//        Node<T> r2 = root2;
//        while(r1 != null && r2 != null){
//            if(r1.key.compareTo(r2.key) > 0){
//                stack.push(r1);
//                r1 = r1.right;
//            }else{
//                stack.push(r2);
//                r2 = r2.right;
//            }
//        }
//
//        Node<T> r = (r1 != null) ? r1 : r2;
//
//        while(!stack.isEmpty()){
//            Node<T> node = stack.pop();
//            node.right = node.left;
//            node.left = r;
//            r = node;
//        }
//        return r;
//    }
//    递归
    public Node<T> merge(Node<T> n1, Node<T> n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;

        if (n1.key.compareTo(n2.key) <= 0) {
            return mergel(n1, n2);
        }else return mergel(n2, n1);
    }

    public int merge(ShewHeap<T> other) {
        if(this == other) return -1;

        this.root = merge(this.root, other.root);
        other.root = null;
        return 1;
    }

    private Node<T> mergel(Node<T> n1, Node<T> n2) {
        if (n1.left == null) {
            n1.left = n2;
            n2.father = n1;
        } else {
            Node<T> m = merge(n1.right, n2);
            n1.right = m;
            m.father = n1;
            swapChildren(n1);
        }
        return n1;
    }

    private void swapChildren(Node<T> n) {
        Node<T> temp = n.left;
        n.left = n.right;
        n.right = temp;
    }

    public void insert(T key) {
        root = merge(new Node<T>(key), root);
    }

    public Node<T> deleteMax() { //返回被删除的根结点
        if (isEmpty()) {
            throw new Error();
        }

        Node<T> maxItem = root;
        root = merge(root.right, root.left);
        return maxItem;
    }

    public T getMax() {
        return root.key;
    }


}
