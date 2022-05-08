package top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 57. 插入区间 [ https://leetcode-cn.com/problems/insert-interval/ ]
 * <p>
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 示例 3：
 * 输入：intervals = [], newInterval = [5,7]
 * 输出：[[5,7]]
 * <p>
 * 示例 4：
 * 输入：intervals = [[1,5]], newInterval = [2,3]
 * 输出：[[1,5]]
 * <p>
 * 示例 5：
 * 输入：intervals = [[1,5]], newInterval = [2,7]
 * 输出：[[1,7]]
 * <p>
 * 提示：
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= intervals[i][0] <= intervals[i][1] <= 105
 * intervals 根据 intervals[i][0] 按 升序 排列
 * newInterval.length == 2
 * 0 <= newInterval[0] <= newInterval[1] <= 105
 */
public class Top057_middle {

    public static void main(String[] args) {
        Top057_middle top057_middle = new Top057_middle();
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
//        int[][] intervals = { {1 , 3}, {6, 9} };
//        int[] newInterval = {2, 5};
        int[][] res = top057_middle.insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        List<int[]> res = new ArrayList<>();
        List<int[]> needMergeList = new ArrayList<>();
        needMergeList.add(newInterval);
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] >= newInterval[0] && intervals[i][0] <= newInterval[0]) {
                index = i;
            } else if (intervals[i][1] < newInterval[0]) {
                index = i + 1;
            }
            if (intervals[i][1] < newInterval[0] || intervals[i][0] > newInterval[1]) {
                res.add(intervals[i]);
                continue;
            }
            needMergeList.add(intervals[i]);
        }
        extracted(needMergeList, res, index);
        return res.toArray(new int[][]{});
    }

    private void extracted(List<int[]> needMergeList, List<int[]> res, int index) {
        int[][] arr = needMergeList.toArray(new int[][]{});
        int[][] merge = merge(arr);
        // 找到插入的index
        for (int j = 0; j < merge.length; j++) {
            res.add(index + j, merge[j]);
        }
    }


    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
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
}
