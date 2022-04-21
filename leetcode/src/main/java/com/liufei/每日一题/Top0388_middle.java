package com.liufei.每日一题;

/**
 * 821. 字符的最短距离  https://leetcode-cn.com/problems/shortest-distance-to-a-character
 *
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 *
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 *
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 *
 * 示例 1：
 *
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 *
 * 示例 2：
 *
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 */
public class Top0388_middle {

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int ans = lengthLongestPath(input);
        System.out.println("dir/subdir2/subsubdir2/file2.ext".length());
    }


    public static int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) return 0;
        String[] words = input.split("\n");
        int[] pathLens = new int[words.length + 1];//pathLens[i]存放地i层次的最后面的元素的路径长度
        pathLens[0] = -1;//层次最少是1，为了统一dp操作（具体看下面循环体），取pathLens[0]=-1
        int ans = 0;
        //自左向右，dp
        for (String word : words) {
            int level = word.lastIndexOf('\t') + 1 + 1;//层次计算
            int nameLen = word.length() - (level - 1);//计算名字长度
            //word的父文件夹必定目前是level-1层次的最后一个，因此pathLens[level-1]就是父文件夹路径长度
            //这个word必然是目前本层次的最后一个，因此需要刷新pathLens[level],+1是因为要加一个'\'
            pathLens[level] = pathLens[level - 1] + 1 + nameLen;
            //如果是文件，还需要用路径长度刷新ans
            if (word.contains(".")) {
                ans = Math.max(ans, pathLens[level]);
            }
        }
        return ans;
    }
}
