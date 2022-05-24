package 每日一题;

import 二叉树.TreeNode;

/**
 * 965. 单值二叉树  [ https://leetcode.cn/problems/univalued-binary-tree/ ]
 * <p>
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：[2,2,2,5,2]
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 */
public class Top0965_simple {

    public static void main(String[] args) {
        new TreeNode(1, new TreeNode(1, new TreeNode(1), new TreeNode(1)), new TreeNode(1, null, new TreeNode(1)));
    }

    public static boolean isUnivalTree(TreeNode root) {
        int target = root.val;
        return trace(root, target);
    }

    static boolean trace(TreeNode root, int target) {
        if (root == null) {
            return true;
        }
        if (target != root.val) {
            return false;
        }
        return trace(root.left, target) && trace(root.right, target);
    }
}
