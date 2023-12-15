package tree.avl_tree;

/**
 * 平衡二叉搜索树
 */
public class AVLTree {

    static class AVLNode {
        int key;
        Object val;
        AVLNode left;
        AVLNode right;
        int height = 1;//节点高度

        public AVLNode(int kay) {
            this.key = kay;
        }

        public AVLNode(int kay, Object val) {
            this.key = kay;
            this.val = val;
        }

        public AVLNode(int kay, Object val, AVLNode left, AVLNode right) {
            this.key = kay;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "[" + key + "=" + val + "]";
        }
    }

    //求节点的高度
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    //更新节点高度
    private void upHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    //求一个节点左右子树的高度差--平衡因子（balance factor
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);//-1,0,1 表示树平衡；大于1：左边高，小于-1：右边高
    }

    //四种失衡情况 LL LR RR RL 参数为失衡节点 返回旋转后的伪根节点
    private AVLNode rightRotate(AVLNode node) {//左不平衡，右旋
        AVLNode left = node.left;//一定不是null
        AVLNode leftRight = left.right;//可能是null
        node.left = leftRight;//换爹
        left.right = node;//上位
        upHeight(node);//更新高度
        upHeight(left);
        return left;
    }

    private AVLNode leftRotate(AVLNode node) {//右不平衡，左旋
        AVLNode right = node.right;
        node.right = right.left;
        right.left = node;
        upHeight(node);
        upHeight(right);
        return right;
    }

    //先左旋左子树再右旋根节点
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    //先右旋右子树再左旋根节点
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    //平衡方法
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) {//LL
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) {//LR
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) >= 0) {//RL
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) < 0) {//RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, Object val) {
        root = doPut(root, key, val);
    }

    private AVLNode doPut(AVLNode node, int key, Object val) {//node表示查询的起始节点
        if (node == null) {//找到空位，创建新节点
            return new AVLNode(key, val);
        }
        //key已经存在，更新val
        if (key == node.key) {
            node.val = val;
            return node;
        }
        //继续查找
        if (key > node.key) {
            node.right = doPut(node.right, key, val);
        } else {
            node.left = doPut(node.left, key, val);
        }
        upHeight(node);//更新高度
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {//返回node节点后面剩下的节点
        //1.node == null
        if (node == null) {
            return null;
        }
        //2.没找到key
        if (key > node.key) {
            node.right = doRemove(node.right, key);
        } else if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else {//3.找到key a.没有孩子 b.只有一个孩子 c.有两个孩子
            if (node.left == null && node.right == null) {
                return null;//没有左右孩子，删除节点后直接返回null
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {//左右节点都有，找后继节点
                AVLNode s = node.right;
                while(s.left != null){
                    s = s.left;
                }
                //这个时候s就是node的后继节点
                s.right = doRemove(node.right,s.key);
                s.left = node.left;
                node = s;
            }
        }
        //4，更新高度
        upHeight(node);
        //5.平衡二叉树
        return balance(node);

    }
}
