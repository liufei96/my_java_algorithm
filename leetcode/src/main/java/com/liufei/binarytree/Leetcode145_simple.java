package com.liufei.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 145. 二叉树的后序遍历  [ https://leetcode-cn.com/problems/binary-tree-postorder-traversal/ ]
 */
public class Leetcode145_simple {

    public static void main(String[] args) {
        Leetcode145_simple leetcode145_simple = new Leetcode145_simple();
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        // TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> list = leetcode145_simple.postorderTraversal(treeNode);
        System.out.println(list);
        System.out.println(leetcode145_simple.postorderTraversal2(treeNode));
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        // 注意这一句：后序遍历放在最后面
        res.add(root.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
