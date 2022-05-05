package com.liufei.每日一题;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 */
public class Top1823_middle {

    public static void main(String[] args) {
        int n = 6, k = 5;
        System.out.println(findTheWinner(n, k));
    }

    public static int findTheWinner2(int n, int k) {
        int p = 0;
        for (int i = 2; i <= n; i++) {
            p = (p + k) % i;
        }
        return p + 1;
    }

    public static int findTheWinner(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i + 1);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }
}
