package tree.binary_search_tree;

/**
 * 泛型
 */
public class BSTree02<K extends Comparable<K>, V> {
    static class BSTNode<K, V> {
        K key;
        V val;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public BSTNode(K key, V val, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public BSTNode(K key) {
            this.key = key;
        }
    }

    BSTNode<K, V> root;

    public V get(K key) {
        if (key == null) {
            return null;
        }
        BSTNode<K, V> p = root;
        while (p != null) {
            int n = key.compareTo(p.key);//n==> -1:key < p.key  0:key = p.key 1:key > p.key
            if (n > 0) {
                p = p.right;
            } else if (n < 0) {
                p = p.left;
            } else {
                return p.val;
            }
        }
        return null;
    }
}
