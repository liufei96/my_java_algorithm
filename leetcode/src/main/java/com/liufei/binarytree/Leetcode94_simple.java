package com.liufei.binarytree;

import java.util.*;

/**
 * 94. 二叉树的中序遍历  [ https://leetcode-cn.com/problems/binary-tree-inorder-traversal/ ]
 * <p>
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 */
public class Leetcode94_simple {

    public static void main(String[] args) {
        Leetcode94_simple leetcode94_simple = new Leetcode94_simple();
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        // TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> list = leetcode94_simple.inorderTraversal(treeNode);
        System.out.println(list);
        System.out.println(leetcode94_simple.inorderTraversal2(treeNode));
        System.out.println(leetcode94_simple.inorderTraversal3(treeNode));
        System.out.println(leetcode94_simple.inorderTraversal4(treeNode));
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

    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 迭代统一处理。那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。如何标记呢，就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法也可以叫做标记法。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.right != null) stack.push(node.right); // 添加右节点（空节点不入栈）
                stack.push(node);  // 添加中节点
                stack.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.left != null) stack.push(node.left); // 添加左节点（空节点不入栈）
            } else {  // 只有遇到空节点的时候，才将下一个节点放进结果集
                node = stack.peek();  // 重新取出栈中元素
                stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }
}
