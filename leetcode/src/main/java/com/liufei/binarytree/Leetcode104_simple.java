package com.liufei.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度  [ https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/ ]
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class Leetcode104_simple {

    public static void main(String[] args) {
        Leetcode104_simple leetcode104_simple = new Leetcode104_simple();
        // TreeNode treeNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode treeNode = new TreeNode(5,  new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        int maxDepth = leetcode104_simple.maxDepth(treeNode);
        System.out.println(maxDepth);
        System.out.println(leetcode104_simple.maxDepth2(treeNode));
    }

    /**
     * 递归法
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * 迭代法
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return depth;
    }
}
