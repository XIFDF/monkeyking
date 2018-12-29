
//解决问题的运用的类，一个节点即一个猴子
public class Node {
    double key;
    Node father;
    Node left;
    Node right;

    Node(double key, Node father, Node left, Node right) {
        this.key = key;
        this.father = father;
        this.left = left;
        this.right = right;
    }

    Node(double key) {
        this(key, null, null, null);
    }

    public Node findRoot() {
        Node root = this;
        while (root.father != null) {
            root = root.father;
        }
        return root;
    }

    public Node merge(Node n1, Node n2) {
        if (n1 == null) return n2;
        if (n2 == null) return n1;

        if (n1.key > n2.key) {
            return mergel(n1, n2);
        }else return mergel(n2, n1);
    }

    private Node mergel(Node n1, Node n2) {
        if (n1.left == null) {
            n1.left = n2;
            n2.father = n1;
        } else {
            Node m = merge(n1.right, n2);
            n1.right = m;
            m.father = n1;
            swapChildren(n1);
        }
        return n1;
    }

    private void swapChildren(Node n) {
        Node temp = n.left;
        n.left = n.right;
        n.right = temp;
    }

    public double fight(Node node) {
        Node root1 = this.findRoot();
        Node root2 = node.findRoot();
        double result;
        if(root1 == root2) return -1;
        else {
                result = merge(root1, root2).deleteMaxAndInsert();
             }
        return result;
    }

    public double deleteMaxAndInsert() {
        Node mergeRoot = merge(this.left, this.right);
        if(mergeRoot != null) mergeRoot.father = null;

        this.key /= 2;
        this.father = null;
        this.left = null;
        this.right = null;
        mergeRoot.insert(this);
        return this.key; //返回被删除的节点
    }

    public void insert(Node n) {
        merge(this, n);
    }
}