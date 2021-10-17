package com.liufei.binarytree;

import javax.xml.soap.Node;
import java.util.*;

/**
 * 102. 二叉树的层序遍历  [ https://leetcode-cn.com/problems/binary-tree-level-order-traversal/ ]
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class Leetcode102_middle {


    public static void main(String[] args) {
        Leetcode102_middle leetcode102_middle = new Leetcode102_middle();
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(6, new TreeNode(7), new TreeNode(8)));
        // TreeNode treeNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<List<Integer>> lists = leetcode102_middle.levelOrder(treeNode);
        System.out.println(lists);
        System.out.println(leetcode102_middle.levelOrder2(treeNode));

    }

    List<List<Integer>> ans = new ArrayList<>();
    List<List<Integer>> ans1 = new ArrayList<>();


    public List<List<Integer>> levelOrder(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    /**
     * 深度优先算法。深度有限算法 使用递归
     *
     * @param node
     * @param deep
     */
    public void dfs(TreeNode node, Integer deep) {
        if (node == null) return;
        deep++;
        if (ans.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<>();
            ans.add(item);
        }
        ans.get(deep - 1).add(node.val);
        dfs(node.left, deep);
        dfs(node.right, deep);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        bfs(root);
        return ans1;
    }

    /**
     * 广度优先算法。广度优先遍历也叫层序遍历。深度优先遍历用的是栈，而广度优先遍历要用队列来实现，
     *
     * @param node
     */
    public void bfs(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int size = queue.size();
            while (size > 0) {
                TreeNode tmpNode = queue.poll();
                itemList.add(tmpNode.val);
                if (tmpNode.left != null) queue.offer(tmpNode.left);
                if (tmpNode.right != null) queue.offer(tmpNode.right);
                size--;
            }
            ans1.add(itemList);
        }
    }
}
