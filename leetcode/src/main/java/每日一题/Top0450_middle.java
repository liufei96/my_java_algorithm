package 每日一题;

import 二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Top0450_middle {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)), new TreeNode(6, null, new TreeNode(7)));
        int key = 3;
        TreeNode treeNode = deleteNode(root, key);
        System.out.println(treeNode);
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            // 在左边
            TreeNode left = deleteNode(root.left, key);
            root.left = left;
        } else if (key > root.val) {
            // 在右边
            TreeNode right = deleteNode(root.right, key);
            root.right = right;
        } else {
            TreeNode left = root.left;
            TreeNode right = root.right;
            //寻找右侧最小的叶子节点
            while (right != null && right.left != null) {
                right = right.left;
            }
            //如果存在右侧最小的叶子节点，将root的左子树拼接到右侧最小叶子节点的左子树
            if (right != null) {
                right.left = left;
                return root.right;
            } else {//如果不存在右侧最小的叶子节点，root的右子树为空，直接返回左子树
                return left;
            }
        }
        return root;
    }
}
