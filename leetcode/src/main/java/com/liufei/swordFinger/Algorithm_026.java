package com.liufei.swordFinger;

/**
 * 剑指 Offer 26. 树的子结构 [ https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/ ]
 * <p>
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * / \
 * 4  5
 * / \
 * 1  2
 * <p>
 * 给定的树 B：
 * 4
 * /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 */
public class Algorithm_026 {

    public static void main(String[] args) {
        Algorithm_026 algorithm026 = new Algorithm_026();
        TreeNode A = new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));
        TreeNode B = new TreeNode(4, new TreeNode(1), null);
        boolean ans = algorithm026.isSubStructure(A, B);
        System.out.println(ans);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
