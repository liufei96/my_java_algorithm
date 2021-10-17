package com.liufei.binarytree;

import java.util.List;

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
        TreeNode node = leetcode226_simple.invertTree(treeNode);
        System.out.println(node);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);
        return root;
    }


}
