package 剑指Offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 27. 二叉树的镜像 [ https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/ ]
 *
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 *
 * 例如输入：
 *
 *              4
 *            /  \
 *          2    7
 *        / \  / \
 *       1  3 6  9
 *
 * 镜像输出：
 *               4
 *             /  \
 *            7    2
 *           / \  / \
 *          9  6 3   1
 *
 * 示例 1：
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 */
public class Algorithm_027 {

    public static void main(String[] args) {
        Algorithm_027 algorithm_027 = new Algorithm_027();
        TreeNode treeNode = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode treeNode1 = algorithm_027.mirrorTree(treeNode);
        System.out.println(treeNode1);

        TreeNode treeNode2 = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9)));
        TreeNode treeNode3 = algorithm_027.mirrorTree2(treeNode2);
        System.out.println(treeNode3);
    }

    /**
     * 递归
     *
     * 这是一道很经典的二叉树问题。显然，我们从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转得到镜像。
     * 如果当前遍历到的节点 \textit{root}root 的左右两棵子树都已经翻转得到镜像，那么我们只需要交换两棵子树的位置，即可得到以 \textit{root}root 为根节点的整棵子树的镜像。
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }


    public TreeNode mirrorTree2(TreeNode root) {
        if(root == null) return null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node  = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
}
