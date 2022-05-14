package 每日一题;

import 二叉树.TreeNode;

import java.util.*;

public class Top0449_middle {

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        Top0449_middle ser = new Top0449_middle();
        Top0449_middle deser = new Top0449_middle();
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        String tree = ser.serialize(root);
        System.out.println(tree);
        TreeNode ans = deser.deserialize(tree);
        System.out.println(ans);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans.toString();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
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
        return ans.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.substring(1, data.length() - 1).split(", ");
        Deque<Integer> stack = new ArrayDeque<>();
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            stack.push(Integer.parseInt(arr[i]));
        }
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private TreeNode construct(int lower, int upper, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper) {
            return null;
        }
        int val = stack.pop();
        TreeNode root = new TreeNode(val);
        root.right = construct(val, upper, stack);
        root.left = construct(lower, val, stack);
        return root;
    }
}
