package com.liufei.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 145. 二叉树的后序遍历  [ https://leetcode-cn.com/problems/binary-tree-postorder-traversal/ ]
 */
public class Leetcode94_simple {

    public static void main(String[] args) {
        Leetcode94_simple leetcode94_simple = new Leetcode94_simple();
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        // TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> list = leetcode94_simple.inorderTraversal(treeNode);
        System.out.println(list);
        System.out.println(leetcode94_simple.inorderTraversal2(treeNode));
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        // 注意这一句：中序遍历放在中间
        res.add(root.val);
        inorder(root.right, res);
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
