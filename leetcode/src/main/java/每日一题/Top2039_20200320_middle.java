package 每日一题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 2039. 网络空闲的时刻   [https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/]
 */
public class Top2039_20200320_middle {

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1},
                {1, 2}
        };
        int[] patience = {0, 2, 1};
        int res = networkBecomesIdle(edges, patience);
        System.out.println(res);
    }


    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        List<Integer>[] path = new List[patience.length];
        for (int i = 0; i < patience.length; i++) {
            path[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            path[edges[i][0]].add(edges[i][1]);
            path[edges[i][1]].add(edges[i][0]);
        }
        boolean cameBefore[] = new boolean[patience.length];
        cameBefore[0] = true;
        int ans = 0, distance = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (q.size() > 0) {
            int size = q.size();
            distance++;
            for (int i = 0; i < size; i++) {
                int a = q.poll();
                for (int j = 0; j < path[a].size(); j++) {
                    int b = path[a].get(j);
                    if (cameBefore[b]) {
                        continue;
                    }
                    ans = Math.max(ans, (distance * 2 - 1) / patience[b] * patience[b] + distance * 2 + 1);
                    cameBefore[b] = true;
                    q.add(b);
                }
            }
        }
        return ans;
    }
}
