package 剑指Offer;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树 [ https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/ ]
 * <p>
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * <p>
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 3
 * 9   20
 * 15   7
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class Algorithm_007 {

    public static void main(String[] args) {
        Algorithm_007 algorithm_007 = new Algorithm_007();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode treeNode = algorithm_007.buildTree(preorder, inorder);
        System.out.println(treeNode);

        System.out.println(algorithm_007.buildTree2(preorder, inorder));
        System.out.println(algorithm_007.buildTree3(preorder, inorder));

    }

    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }


    /**
     * 迭代法
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }

    /**
     * 迭代法
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        return help(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode help(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2) {
        if (l1 > r1 || l2 > r2) return null;
        int i = l2;
        while (inorder[i] != preorder[l1]) {//在中序数组中去寻找根节点
            i++;
        }
        TreeNode root = new TreeNode(preorder[l1]);
        root.left = help(preorder, l1 + 1, l1 + i - l2, inorder, l2, i - 1);
        root.right = help(preorder, l1 + i - l2 + 1, r1, inorder, i + 1, r2);
        return root;
    }

    /**
     * 补充知识：对于前序遍历、中序遍历、后序遍历
     *
     * 这里的前、中、后针对的是头结点
     *
     */

}
