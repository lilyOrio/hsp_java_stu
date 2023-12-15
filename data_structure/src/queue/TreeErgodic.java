package queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 层级遍历二叉树
 * 使用队列
 */
public class TreeErgodic {
    private static class TreeNode {
        TreeNode left;
        Integer val;
        TreeNode right;

        public TreeNode(TreeNode left, Integer val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }
    }

    static TreeNode root = new TreeNode(//创建一个树    1 23 4567
            new TreeNode(new TreeNode(null, 4, null),
                    2,
                    null),
            1,
            new TreeNode(new TreeNode(null, 6, null),
                    3,
                    new TreeNode(null, 7, null))
    );

    public List<List<Integer>> levelOrder(TreeNode root) {//[[1], [2, 3], [4, 5, 6, 7]]
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedListQueue<>(10);
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            while (size != 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                size--;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    public void levelOrder2(TreeNode root) {//1 2 3 4 5 6 7
        LinkedListQueue<TreeNode> treeNodes = new LinkedListQueue<>();
        treeNodes.offer(root);
        while (!treeNodes.isEmpty()) {
            TreeNode node = treeNodes.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                treeNodes.offer(node.left);
            }
            if (node.right != null) {
                treeNodes.offer(node.right);
            }
        }
    }

    public void levelOrder3(TreeNode root) {//1 2 3 4 5 6 7
        LinkedListQueue<TreeNode> treeNodes = new LinkedListQueue<>();
        treeNodes.offer(root);
        int c1 = 1;
        while (!treeNodes.isEmpty()) {
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode node = treeNodes.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    treeNodes.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    treeNodes.offer(node.right);
                    c2++;
                }
            }
            c1 = c2;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeErgodic treeErgodic = new TreeErgodic();
        List<List<Integer>> lists = treeErgodic.levelOrder(root);
        System.out.println(lists);//[[1], [2, 3], [4, 5, 6, 7]]
        treeErgodic.levelOrder2(root);
        System.out.println();
        System.out.println("================");
        treeErgodic.levelOrder3(root);
    }
}
