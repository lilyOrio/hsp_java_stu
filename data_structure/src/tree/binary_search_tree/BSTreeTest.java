package tree.binary_search_tree;

public class BSTreeTest {
    public static void main(String[] args) {
        BSTree01 tree = new BSTree01();
        tree.put(3,"3");
        tree.put(1,"1");
        tree.put(2,"2");
        tree.put(4,"4");

        System.out.println(tree.root);
        System.out.println(tree.get(2));
        tree.put(2,"22");
        System.out.println(tree.get(2));
        System.out.println(tree.min());
    }
}
