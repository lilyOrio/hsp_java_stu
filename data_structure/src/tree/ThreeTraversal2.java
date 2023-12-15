package tree;

import java.util.Stack;

/**
 * 遍历二叉树--非递归方式
 */
public class ThreeTraversal2 {

    static ThreeNode root = new ThreeNode(
            new ThreeNode(
                    new ThreeNode(null, 4, null),
                    2,
                    new ThreeNode(null, 7, null)
            ),
            1,
            new ThreeNode(
                    new ThreeNode(null, 5, null),
                    3,
                    new ThreeNode(null, 6, null)
            )
    );


    public static void main(String[] args) {
//        Stack<ThreeNode> stack = new Stack<>();
//        ThreeNode curr = root;
//        while (curr != null || !stack.isEmpty()) {
//            if (curr != null) {
////                colorPrintln(curr.val + " ", 31);//前序遍历
//                stack.push(curr);
//                curr = curr.left;
//            } else {
//                ThreeNode pop = stack.pop();
//                colorPrintln(pop.val + " ", 34);//中序遍历
//                curr = pop.right;
//            }
//        }
//
//        //后序遍历
//        curr = root;
//        ThreeNode pop = null;//记录最近一次弹栈元素
//        while (curr != null || !stack.isEmpty()) {
//            if (curr != null) {
//                stack.push(curr);
//                curr = curr.left;
//            } else {
//                ThreeNode peek = stack.peek();
//                if (peek.right == null || peek.right == pop) {//右节点为空，或者右节点已经打印，就打印当前节点
//                    pop = stack.pop();
//                    System.out.println(" " + pop.val);
//                } else {//如果右节点未处理完就去处理右节点
//                    curr = peek.right;
//                }
//            }
//        }

        print();
    }

    private static void colorPrintln(String origin, int color) {//彩色打印
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }

    //打印前中后序逻辑结合
    public static void print() {
        Stack<ThreeNode> stack = new Stack<>();
        ThreeNode curr = root;
        ThreeNode pop = null;//记录最近一次弹栈元素
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                colorPrintln(curr.val + "前", 31);
                curr = curr.left;//待处理左节点
            } else {
                ThreeNode peek = stack.peek();
                if (peek.right == null) {//没有右节点
                    colorPrintln(peek.val + "中", 36);
                    pop = stack.pop();
                    colorPrintln(pop.val + "后", 34);
                } else if (peek.right == pop) {//右节点已经处理过
                    pop = stack.pop();
                    colorPrintln(pop.val + "后", 34);
                } else {
                    colorPrintln(peek.val + "中", 36);
                    curr = peek.right;//待处理右节点
                }
            }
        }
    }
}
