package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间 [ https://leetcode-cn.com/problems/merge-intervals/ ]
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class Top056_middle {

    public static void main(String[] args) {
        Top056_middle top056Middle = new Top056_middle();

        int[][] intervals = {{4, 5}, {2, 4}, {4, 6}, {3, 4}, {0, 0}, {1, 1}, {3, 5}, {2, 2}};
        // int[][] intervals = {{4, 5}, {1, 4}};
        // int[][] intervals = {{1, 3}, {2 , 6}, {8, 10}, {15, 18}};
        // int[][] intervals = {{2, 3}, {2 , 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
        // int[][] intervals = {{4, 5}, {2 , 4}, {4, 6}, {3, 4}, {0, 0}, {1 , 1}, {3, 5}, {2, 2}};
        // int[][] intervals = {{1, 3}, {0, 2}, {2, 3}, {4, 6}, {4, 5}, {5, 5}, {0, 2}, {3, 3}};
        // int[][] intervals = {{0, 2}, {2, 3}, {4, 4}, {0, 1}, {5, 7}, {4, 5}, {0, 0}};

        // int[][] merge = top056Middle.merge(intervals);
        // int[][] merge = top056Middle.merge2(intervals);
        int[][] merge = top056Middle.merge5(intervals);
        for (int i = 0; i < merge.length; i++) {
            System.out.println(Arrays.toString(merge[i]));
        }
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> res = new ArrayList<>();
        int[] nums = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (nums[1] < intervals[i][0]) {
                // 直接添加, 重新new个数组，最好不要破坏原来的数组
                res.add(new int[]{nums[0], nums[1]});
                nums = intervals[i];
            } else {
                nums[1] = Math.max(intervals[i][1], nums[1]);
            }
            if (i == intervals.length - 1) {
                res.add(new int[]{nums[0], nums[1]});
            }
        }
        return res.toArray(new int[][]{});
    }

    /**
     * 官方答案：排序
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[][]{});
    }

    /**
     *  7ms 或 8ms
     * @param intervals
     * @return
     */
    public int[][] merge3(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int i = 0, n = intervals.length;
        while (i < n) {
            int left = intervals[i][0], right = intervals[i][1];
            while (i < n - 1 && intervals[i + 1][0] <= right) {
                right = Math.max(right, intervals[i + 1][1]);
                i++;
            }
            merged.add(new int[]{left, right});
            i++;
        }
        return merged.toArray(new int[][]{});
    }

    /**
     * 原地合并
     * @param intervals
     * @return
     */
    public int[][] merge4(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int index = 1;
        int len = intervals.length;
        for (int i = 1; i < len; i++) {
            int[] arr = intervals[index - 1];
            if (intervals[i][0] > arr[1]) {
                intervals[index++] = intervals[i];
            } else {
                arr[1] = Math.max(intervals[i][1], arr[1]);
            }
        }
        return Arrays.copyOf(intervals, index);
    }

    /**
     * 耗时长，需要修改下
     *
     * @param intervals
     * @return
     */
    public int[][] merge5(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] visitedCount = new int[10000];
        int[] node = new int[10000];
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] == intervals[i][1] && visitedCount[intervals[i][0]] == 0) {
                if (i == intervals.length - 1 || intervals[i][1]  != intervals[i + 1][0]) {
                    res.add(intervals[i]);
                }
                continue;
            }
            node[intervals[i][0]]++;
            for (int j = intervals[i][0]; j <= intervals[i][1]; j++) {
                visitedCount[j]++;
            }
        }
        int n = visitedCount.length;
        for (int i = 0; i < n; i++) {
            if (visitedCount[i] > 0 && node[i] > 0) {
                int[] nums = new int[2];
                nums[0] = i;
                i++;
                while ((visitedCount[i] > node[i] && node[i] > 0 || node[i] == 0 && visitedCount[i] > 0) && i < n) {
                    i++;
                }
                nums[1] = --i;
                res.add(nums);
                continue;
            }
        }
        return res.toArray(new int[][]{});
    }
}
