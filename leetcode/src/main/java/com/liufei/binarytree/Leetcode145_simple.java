package com.liufei.binarytree;

import java.util.*;

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
        System.out.println(leetcode145_simple.postorderTraversal3(treeNode));
        System.out.println(leetcode145_simple.postorderTraversal4(treeNode));
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

    /**
     * 迭代法
     * @param root
     * @return
     */
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

    /**
     * 迭代法 + 反转
     * 后序遍历是  中 -> 左 -> 右。 入栈顺序是：中 -> 左 -> 右，出栈顺序是：中 -> 右 -> 左。最后结果反转
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 迭代统一处理。那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。如何标记呢，就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法也可以叫做标记法。
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                stack.push(node);  // 添加中节点
                stack.push(null); // 中节点访问过，但是还没有处理，加入空节点做为标记。
                if (node.right != null) stack.push(node.right); // 添加右节点（空节点不入栈）
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
