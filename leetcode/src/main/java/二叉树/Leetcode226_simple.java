package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 翻转二叉树   [ https://leetcode-cn.com/problems/invert-binary-tree/ ]
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 *
 */
public class Leetcode226_simple {

    public static void main(String[] args) {
        Leetcode226_simple leetcode226_simple = new Leetcode226_simple();
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        TreeNode node = leetcode226_simple.invertTree2(treeNode);
        System.out.println(node);
    }

    /**
     * 前序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode right = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(right);
        return root;
    }

    /**
     * 利用中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        invertTree2(root.left); // 递归找到左节点
        TreeNode rightNode = root.right;  // 保存右节点
        root.right = root.left;
        root.left = rightNode;
        // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
        invertTree2(root.left);
        return root;
    }


    /**
     * 利用后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = invertTree3(root.left);
        TreeNode rightNode = invertTree3(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }


    /**
     * 利用层次遍历。广度吧
     * @param root
     * @return
     */
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode leftNode = node.right;
            node.right = node.left;
            node.left = leftNode;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return root;
    }
}
