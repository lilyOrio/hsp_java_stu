package tree.red_black_tree;

public class RedBlackTree {
    enum Color {
        RED, BLACK
    }

    static class Node {
        int key;
        Object val;
        Node left;
        Node right;
        Node parent;//父节点
        Color color = Color.RED;//新节点默认黑色

        //判断当前节点是否是左孩子
        boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        //找当前节点的叔叔节点 和 当前节点的父节点平辈的节点
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            Node grandpa = parent.parent;
            if (parent.isLeftChild()) {
                return grandpa.right;
            } else {
                return grandpa.left;
            }
        }

        //找兄弟节点
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        public Node(int key, Object val) {
            this.key = key;
            this.val = val;
        }
    }

    //判断节点是否为红色
    boolean isRed(Node node) {
        if (node != null) {
            return node.color == Color.RED;
        }
        return false;
    }

    //判断节点是否为黑色
    boolean isBlack(Node node) {
        return !isRed(node);
    }

    Node root;

    //红黑树的旋转
    private void rightRotate(Node node) {//右旋 1.parent的处理 2.旋转后新根的父子关系
        Node parent = node.parent;//原节点的父节点
        Node left = node.left;//右旋说明左边高
        Node leftRight = left.right;

        node.left = leftRight;
        if (leftRight != null) {
            leftRight.parent = node;
        }

        left.parent = parent;
        node.parent = left;
        left.right = node;
        if (parent == null) {//原节点的父节点为空说明旋转节点是根节点
            root = left;//将旋转后的left变成根节点
        } else if (node.isLeftChild()) {
            parent.left = left;
        } else {
            parent.right = left;
        }
    }

    private void leftRotate(Node node) {//左旋 1.parent的处理 2.旋转后新根的父子关系
        Node parent = node.parent;//原节点的父节点
        Node right = node.right;//右旋说明左边高
        Node rightLeft = right.left;

        node.right = rightLeft;
        if (rightLeft != null) {
            rightLeft.parent = node;
        }

        right.parent = parent;
        node.parent = right;
        right.left = node;
        if (parent == null) {//原节点的父节点为空说明旋转节点是根节点
            root = right;//将旋转后的left变成根节点
        } else if (node.isLeftChild()) {
            parent.left = right;
        } else {
            parent.right = right;
        }
    }

    //红黑树的插入
    public void put(int key, Object val) {//红红不平衡
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.val = val;
                return;
            }
        }
        Node node = new Node(key, val);
        if (parent == null) {
            root = node;
        } else {
            if (key < parent.key) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            node.parent = parent;
        }
        fixRedRed(node);
    }

    //重新调整红黑树平衡
    private void fixRedRed(Node x) {//修正两个相邻得红色节点
        //1.插入节点是根节点
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }

        //2.插入节点的父亲节点是黑色，无需做处理
        if (isBlack(x.parent)) {
            return;
        }
        /* 3.父红叔红 --变色处理==》父变黑叔变黑 祖父变红 祖父可能再次触发红红冲突 递归处理祖父冲突
         * 父亲是红色并且叔叔也是红色的，把父亲和叔叔的颜色就变成黑色，祖父变红色，
         * 祖父是红色有可能会再次触发红红相邻，就要再次递归处理（将父亲叔叔变黑，祖父变红，直到祖父是root就不能再变红了）
         */
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandpa = parent.parent;
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandpa.color = Color.RED;
            fixRedRed(grandpa);
            return;
        }
        /*4.父红叔黑 ：a.先父亲（左/右旋） b.再变色 插入节点变黑 祖父变红 c.最后祖父（右/左旋）
        父亲是红色但是叔叔是黑色的 要配合旋转使用
        父亲变黑，祖父变红,然后再旋转
         */
        if (parent.isLeftChild() && x.isLeftChild()) {//LL
            parent.color = Color.BLACK;
            grandpa.color = Color.RED;
            rightRotate(grandpa);//祖父右旋
        } else if (parent.isLeftChild() && !x.isLeftChild()) {//LR
            leftRotate(parent);//对父亲节点做一次左旋
            x.color = Color.BLACK;
            grandpa.color = Color.RED;
            rightRotate(grandpa);
        } else if (!parent.isLeftChild() && !x.isLeftChild()) {//RR
            parent.color = Color.BLACK;
            grandpa.color = Color.RED;
            leftRotate(grandpa);
        } else {//RL
            rightRotate(parent);
            x.color = Color.BLACK;
            grandpa.color = Color.RED;
            leftRotate(grandpa);
        }
    }

    public void remove(int key) {//考虑黑黑不平衡，删除红色节点不需要考虑，删除黑色节点就需要考虑失衡
        Node removed = find(key);
        if (removed == null) {
            return;
        }
        doRemove(removed);
    }

    private void doRemove(Node removed) {
        Node remain = findRemain(removed);
        Node parent = removed.parent;
        if (remain == null) {//删除节点没有孩子
            if (removed == root) {//删除的是根节点
                root = null;
            } else {//先调整颜色再删除
                if (isBlack(removed)) {//删除节点是黑色
                    fixDoubleBlack(removed);
                } else {//删除节点是红色，不做处理

                }
                if (removed == parent.left) {//删除的是叶子节点
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                removed.parent = null;//便于垃圾回收
            }

            return;
        }
        if (removed.left == null || removed.right == null) {//只有一个孩子 那么它一定是黑色的，它的孩子一定是红色（黑色孩子不能单独存在）
            if (removed == root) {
                //交换根节点和其孩子节点
                root.key = remain.key;
                root.val = remain.val;
                root.left = null;
                root.right = null;
            } else {
                if (removed.isLeftChild()) {
                    parent.left = remain;
                } else {
                    parent.right = remain;
                }
                remain.parent = parent;
                removed.left = null;
                removed.right = null;
                removed.parent = null;
                if (isBlack(removed) && isBlack(remain)) {//remain是黑色
                    fixDoubleBlack(remain);
                } else {//remain是红色,就把它变成黑色
                    remain.color = Color.BLACK;
                }
            }
            return;
        }

        //有两个孩子：交换删除节点removed和它的后继节点remain的k-v值
        // 然后就可以转换成删除只有一个孩子或者没有孩子的情况
        int k = removed.key;
        Object v = removed.val;
        removed.key = remain.key;
        removed.val = remain.val;
        remain.key = k;
        remain.val = v;
        //对交换后的remain节点继续递归删除
        doRemove(remain);
    }

    private void fixDoubleBlack(Node node){//现象：路径中少了一个黑色；参数表示待调整的节点
        if (node == root){
            return;
        }
        /*
        1.被调整节点的兄弟是红色，此时两个侄子的颜色一定是黑色
        2.被调整节点的兄弟是黑色,两个侄子的颜色是黑色
        3.被调整节点的兄弟是黑色,两个侄子至少有一个是红色
         */
        Node parent = node.parent;//父亲
        Node sibling = node.sibling();//兄弟
        //情况一 兄弟是红色
        if (isRed(sibling)){
            //a.先对父亲节点左/右旋
            if (node.isLeftChild()){
                leftRotate(parent);
            }else {
                rightRotate(parent);
            }
            //b.将父亲的颜色变红，原兄弟颜色变黑 ==》这个时候新的兄弟节点就是黑色的
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            //c.递归调用 参数还是原来要调整的节点
            fixDoubleBlack(node);
            return;
        }
        if (sibling != null){

        }else {
            fixDoubleBlack(parent);
        }

    }

    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (p.key > key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    //查找node节点后的剩余节点
    private Node findRemain(Node node) {
        if (node.left == null) {//没有左孩子
            return node.right;
        }
        if (node.right == null) {//没有右孩子
            return node.left;
        }
        //左右孩子都有
        Node s = node.right;
        while (s.left != null) {//找后继节点
            s = s.left;
        }
        return s;
    }

}
