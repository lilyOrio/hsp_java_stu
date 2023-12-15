package tree;

/**
 * 遍历二叉树--递归方式
 */
public class ThreeTraversal {

    static ThreeNode root = new ThreeNode(
            new ThreeNode(
                    new ThreeNode(null, 4, null),
                    2,
                    null
            ),
            1,
            new ThreeNode(
                    new ThreeNode(null, 5, null),
                    3,
                    new ThreeNode(null, 6, null)
            )
    );

    //前序遍历
    static void preOrder(ThreeNode root){//124356
        if (root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //中序遍历
    static void inOrder(ThreeNode root){//421536
        if (root == null){
            return;
        }
        inOrder(root.left);//左
        System.out.print(root.val + " ");//值
        inOrder(root.right);//右
    }

    static void postOrder(ThreeNode root){//425631
        if (root == null){
            return;
        }
        postOrder(root.left);//左
        postOrder(root.right);//右
        System.out.print(root.val + " ");//值
    }

    public static void main(String[] args) {
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }


}
