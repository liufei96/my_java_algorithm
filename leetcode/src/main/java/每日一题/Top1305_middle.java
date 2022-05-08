package 每日一题;

import 二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 1305. 两棵二叉搜索树中的所有元素  [ https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/ ]
 * <p>
 * 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 * <p>
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 * <p>
 * 示例 2：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 * <p>
 * 提示：
 * <p>
 * 每棵树的节点数在 [0, 5000] 范围内
 * -105 <= Node.val <= 105
 */
public class Top1305_middle {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, null, new TreeNode(8));
        TreeNode root2 = new TreeNode(8, new TreeNode(1), null);
        System.out.println(getAllElements2(root1, root2));
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root1 != null) {
            queue.add(root1);
        }
        if (root2 != null) {
            queue.add(root2);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                ans.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return ans.stream().sorted().collect(Collectors.toList());
    }


    public static List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        List<Integer> nums1 = new ArrayList<>();
        List<Integer> nums2 = new ArrayList<>();
        inOrder(root1, nums1);
        inOrder(root2, nums2);

        List<Integer> merge = new ArrayList<>();
        // 归并排序
        int p1 = 0, p2 = 0;
        while (true) {
            if (p1 == nums1.size()) {
                merge.addAll(nums2.subList(p2, nums2.size()));
                break;
            }

            if (p2 == nums2.size()) {
                merge.addAll(nums1.subList(p1, nums1.size()));
                break;
            }
            if (nums1.get(p1) < nums2.get(p2)) {
                merge.add(nums1.get(p1++));
            } else {
                merge.add(nums2.get(p2++));
            }
        }
        return merge;
    }


    static void inOrder(TreeNode treeNode, List<Integer> res) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left, res);
        res.add(treeNode.val);
        inOrder(treeNode.right, res);
    }
}
