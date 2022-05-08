package 剑指Offer;

/**
 * 剑指 Offer 28. 对称的二叉树 [ https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/ ]
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 */
public class Algorithm_028 {

    public static void main(String[] args) {
        Algorithm_028 algorithm_028 = new Algorithm_028();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        TreeNode treeNode2 = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
        boolean ans = algorithm_028.isSymmetric(treeNode2);
        System.out.println(ans);
    }

    public boolean isSymmetric(TreeNode root) {
        if  (root == null) return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if  (root1 == null || root2 == null) return false;
        return root1.val == root2.val && helper(root1.left, root2.right) && helper(root1.right, root2.left);
    }
}
