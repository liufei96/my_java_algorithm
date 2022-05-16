package 每日一题;

import 二叉树.TreeNode;

/**
 * 面试题 04.06. 后继者   [ https://leetcode.cn/problems/successor-lcci/ ]
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [2,1,3], p = 1
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * <p>
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * <p>
 * 输出: null
 */
public class 面试题_后继者_middle {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5, new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)), new TreeNode(6));
        TreeNode p = new TreeNode(6);
        TreeNode treeNode = inorderSuccessor(root, p);
        System.out.println(treeNode);
    }

    public static TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 当前节点值小于等于目标值，那么当前目标值的后继者必然在右子树
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        // 否则结果有可能是当前节点，或者在当前节点的左子树中
        // 那么先去左子树找一下试试，找不到的话返回当前节点即是结果
        TreeNode node = inorderSuccessor(root.left, p);
        return node == null ? root : node;
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        int target = p.val;
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.val > target) {
                ans = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }
}
