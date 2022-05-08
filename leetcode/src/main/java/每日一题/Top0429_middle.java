package 每日一题;

import java.util.*;

public class Top0429_middle {


    public static void main(String[] args) {
        Top0429_middle top0429_middle = new Top0429_middle();
        List<Node> child = new ArrayList<>();
        List<Node> child2 = new ArrayList<>();
        child2.add(new Node(5));
        child2.add(new Node(6));
        child.add(new Node(3, child2));
        child.add(new Node(2));
        child.add(new Node(4));
        Node root = new Node(1, child);
        List<List<Integer>> lists = top0429_middle.levelOrder(root);
        System.out.println(lists);
    }


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                list.add(node.val);
                if (node.children == null) {
                    continue;
                }
                for (Node child : node.children) {
                    queue.add(child);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
