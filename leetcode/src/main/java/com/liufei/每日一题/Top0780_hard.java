package com.liufei.每日一题;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 780. 到达终点  [ https://leetcode-cn.com/problems/reaching-points/ ]
 *
 * 给定四个整数sx,sy，tx和ty，如果通过一系列的转换可以从起点(sx, sy)到达终点(tx, ty)，则返回 true，否则返回false。
 *
 * 从点(x, y)可以转换到(x, x+y) 或者(x+y, y)。
 *
 *
 *
 * 示例 1:
 *
 * 输入: sx = 1, sy = 1, tx = 3, ty = 5
 * 输出: true
 * 解释:
 * 可以通过以下一系列转换从起点转换到终点：
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * 示例 2:
 *
 * 输入: sx = 1, sy = 1, tx = 2, ty = 2
 * 输出: false
 * 示例 3:
 *
 * 输入: sx = 1, sy = 1, tx = 1, ty = 1
 * 输出: true
 *
 *
 * 提示:
 *
 * 1 <= sx, sy, tx, ty <= 109
 */
public class Top0780_hard {


    public static void main(String[] args) {
        Top0780_hard top0780_hard = new Top0780_hard();
        int sx = 35;
        int sy = 13;
        int tx = 455955547;
        int ty = 420098884;

//        int sx = 1;
//        int sy = 1;
//        int tx = 3;
//        int ty = 5;

        boolean b = top0780_hard.reachingPoints(sx, sy, tx, ty);
        System.out.println(b);
    }


    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx <= ty && sy <= ty) { //因为sx, sy, tx, ty 是范围在 [1, 10^9] 的整数，逆推不能出界
            if (sx == tx && sy == ty) {  //判断是否到达了起始值
                return true;
            }
            //每次逆推只能有tx、ty中较大值减去若干个较小值
            if (tx > ty) {
                //tx - sx是目标与起始值在x的差距，我们需要一次减去n * ty达到快速逼近sx的目的
                tx -= Math.max((tx - sx) / ty, 1) * ty;
            } else {
                //ty - sy是目标与起始值在y的差距，我们需要一次减去n * tx达到快速逼近sy的目的
                ty -= Math.max((ty - sy) / tx, 1) * tx;
            }
        }
        return false;
    }


    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr[0] == tx && arr[1] == ty) {
                    return true;
                }
                if (arr[0] > tx || arr[1] == ty) {
                    return false;
                }
                queue.add(new int[]{sx, sx + sy});
                queue.add(new int[]{sx + sy, sy});
            }
        }
        return false;
    }
}
