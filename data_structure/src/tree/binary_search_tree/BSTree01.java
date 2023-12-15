package tree.binary_search_tree;

public class BSTree01 {
    BSTNode root;//根节点

    //节点类
    static class BSTNode {
        int key;
        Object val;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object val, BSTNode left, BSTNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public BSTNode(int key, Object val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "[" + key + "=" + val + "]";
        }
    }

    public Object get(int key) {
        return doGet(root, key);
    }

    private Object doGet(BSTNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.key) {
            return doGet(root.right, key);
        } else if (key < root.key) {
            return doGet(root.left, key);
        } else {
            return root.val;
        }
    }

    public Object get2(int key) {
        BSTNode n = root;
        while (n != null) {
            if (key > root.key) {
                n = n.right;
            } else if (key < root.key) {
                n = n.left;
            } else {
                return root.val;
            }
        }
        return null;
    }

    public Object min() {
        return min(root);
//        doMin(root);//递归方法
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }

    private Object doMin(BSTNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.val;
        }
        return doMin(root.left);
    }

    public Object max() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.val;
    }

    public void put(int key, Object val) {
        //1.key 已经存在 更新值
        BSTNode p = root;
        BSTNode parent = null;//新节点的父节点
        while (p != null) {
            parent = p;
            if (key > root.key) {
                p = p.right;
            } else if (key < root.key) {
                p = p.left;
            } else {
                p.val = val;
                return;
            }
        }
        //2.key 不存在 添加
        BSTNode newNode = new BSTNode(key, val);
        if (parent == null) {//树为空
            root = newNode;
            return;
        }
        if (newNode.key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    //查找关键字的前驱值
    public Object successor(int key) {
        //如果有左孩子，就是以左孩子为根节点的最大值
        //如果没有左孩子，就是自左而来的第一个父辈节点
        BSTNode p = root;
        BSTNode parentFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                parentFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {//没有找到目标值
            return null;
        }
        if (p.left != null) {
            return max(p.left);
        }
        if (parentFromLeft != null) {
            return parentFromLeft;
        }
        return null;
    }

    //查找关键字的后继值
    public Object predecessor(int key) {
        //如果有右孩子，就是以右孩子为根节点的最小值
        //如果没有右孩子，就是自右而来的第一个父辈节点
        BSTNode p = root;
        BSTNode parentFromRight = null;
        while (p != null) {
            if (key < p.key) {
                parentFromRight = p;
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {//没有找到目标值
            return null;
        }
        if (p.right != null) {
            return min(p.right);
        }
        if (parentFromRight != null) {
            return parentFromRight;
        }
        return null;
    }

    public Object delete(int key) {
        /*对于删除节点
        1.如果没有左孩子，就把右孩子“托孤”给父节点
        2.如果没有右孩子，就把左孩子“托孤”给父节点
         */
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (key > p.key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {//没有找到目标值
            return null;
        }
        if (p.left == null && p.right != null) {

        } else if (p.left != null && p.right == null) {

        }

        return p.val;
    }

    /*
    托孤算法
     */
    private void entrust(BSTNode parent,BSTNode deleted,BSTNode child){

    }

    @Override
    public String toString() {
        return "BSTree01{" +
                "root=" + root +
                '}';
    }
}
