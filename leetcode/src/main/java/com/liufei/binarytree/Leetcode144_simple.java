package com.liufei.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 144. 二叉树的前序遍历 [ https://leetcode-cn.com/problems/binary-tree-preorder-traversal/ ]
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Leetcode144_simple {

    public static void main(String[] args) {
        Leetcode144_simple leetcode144_simple = new Leetcode144_simple();
        TreeNode treeNode = new TreeNode(5,  new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        List<Integer> list = leetcode144_simple.preorderTraversal(treeNode);
        System.out.println(list);
        System.out.println(leetcode144_simple.preorderTraversal2(treeNode));
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        // 注意这一句。谦虚遍历放在最前面
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }
}
