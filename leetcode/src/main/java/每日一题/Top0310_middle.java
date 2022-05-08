package 每日一题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 310. 最小高度树 [ https://leetcode-cn.com/problems/minimum-height-trees/ ]
 * <p>
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * <p>
 * 给你一棵包含n个节点的树，标记为0到n - 1 。给定数字n和一个有 n - 1 条无向边的 edges列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * <p>
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * <p>
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * <p>
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 * 解释：如图所示，当根是标签为 1 的节点时，树的高度是 1 ，这是唯一的最小高度树。
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 */
public class Top0310_middle {

    public static void main(String[] args) {
        Top0310_middle top0310_middle = new Top0310_middle();
        int[][] edges = {
                {1, 0},
                {1, 4},
                {1, 3},
                {4, 5},
                {4, 8},
                {3, 7},
                {3, 6},
                {3, 2},
                {6, 9},
        };
        int n = 10;
        List<Integer> minHeightTrees = top0310_middle.findMinHeightTrees(n, edges);
        System.out.println(minHeightTrees);
    }


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        // 保存每个节点的数量
        int[] degree = new int[n];
        // 保存每个节点的具体子节点的数
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            degree[a]++;
            degree[b]++;

            if (map.get(a) == null) {
                map.put(a, new ArrayList<>());
            }

            if (map.get(b) == null) {
                map.put(b, new ArrayList<>());
            }

            map.get(a).add(b);
            map.get(b).add(a);
        }
        // 保存节点数是1的数
        LinkedList<Integer> leafNode = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1) {
                leafNode.add(i);
            }
        }

        while (!leafNode.isEmpty()) {
            ans.clear();
            int size = leafNode.size();
            for (int i = 0; i < size; i++) {
                // 依次去除子节点是1的节点
                int leaf = leafNode.poll();
                ans.add(leaf);

                List<Integer> list = map.get(leaf);
                for (Integer value : list) {
                    degree[value]--;
                    if (degree[value] == 1) {
                        leafNode.add(value);
                    }
                }
            }
        }
        return ans;
    }
}
