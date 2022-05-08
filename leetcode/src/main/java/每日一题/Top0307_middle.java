package 每日一题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 307. 区域和检索 - 数组可修改   [ https://leetcode-cn.com/problems/range-sum-query-mutable/ ]
 *
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组nums下标对应的值
 * 另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的 和，其中left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组nums中索引left和索引right之间（包含）的nums元素的 和（即，nums[left] + nums[left + 1], ..., nums[right]）
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 *
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104
 *
 */
public class Top0307_middle {

    public static void main(String[] args) {
        List<Integer> ans = new ArrayList<>();
        int[] nums = {1, 3, 5};
        NumArray numArray = new NumArray(nums);
        int i = numArray.sumRange(0, 2);
        System.out.println(i);
        numArray.update(1, 2);
        i = numArray.sumRange(0, 2);
        System.out.println(i);
        System.out.println(Arrays.toString(nums));

        String[] operators = {"NumArray", "sumRange", "update", "sumRange"};
    }
}


class NumArray2 {

    int[] nums;
    int size;
    int[] sum;

    public NumArray2(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        size = (int)Math.sqrt(n);
        sum = new int[(n + size - 1) / size];
        for (int i = 0; i < n; i++) {
            sum[i / size] += nums[i];
        }
    }

    public void update(int index, int val) {
        sum[index / size] += val - nums[index];
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
       int b1 = left / size, i1 = left % size, b2 = right / size, i2 = right % size;
        if (b1 == b2) {
            int sum = 0;
            for (int i = i1; i <= i2; i++) {
                sum += nums[b1 * size + i];
            }
            return sum;
        }
        int sum1 = 0;
        for (int i = i1; i < size; i++) {
            sum1 += nums[b1 * size + i];
        }
        int sum2 = 0;
        for (int j = 0; j <= i2; j++) {
            sum2 += nums[b2 * size + j];
        }
        int sum3 = 0;
        for (int i = b1 + 1; i < b2; i++) {
            sum3 += sum[i];
        }
        return sum1 + sum2 + sum3;
    }
}




class NumArray {

    int[] nums;
    int[] tree;
    int n;


    public NumArray(int[] nums) {
        this.nums = nums;
        n = nums.length;
        if (n == 0) {
            return;
        }
        tree = new int[n * 4];
        buildTree(0, 0, n - 1);
    }

    void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int mid = (end + start) / 2 + start;
        int left  = node * 2 + 1;
        int right = node * 2 + 2;
        buildTree(left, start, mid);
        buildTree(right, mid + 1, end);
        tree[node] = tree[left] + tree[right];
    }

    public void update(int index, int val) {
        updateTree(index, 0, val, 0, n - 1);
    }

    public void updateTree(int idx, int node, int val, int start, int end) {
        if (start > end) {
            return;
        }
        if (start == end) {
            nums[idx] = val;
            tree[node] = val;
        } else {
            int mid = (start + end) >> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            if (idx >= start && idx <= mid) {
                updateTree(idx, left, val, start, mid);
            } else {
                updateTree(idx, right, val, mid + 1, end);
            }
            tree[node] = tree[left] + tree[right];
        }

    }

    public int sumRange(int left, int right) {
        return query(left, right, 0, 0, n - 1);
    }

    public int query(int l, int r, int node, int start, int end) {
        if (l > end || r < start) {
            return 0;
        }
        if (start == end) {
            return tree[node];
        }
        if (l <= start && r <= end) {
            return tree[node];
        } else {
            int mid = (start + end) >> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            int ls = query(l, r, left, start, mid);
            int rs = query(l, r, right, end, mid + 1);
            return ls + rs;
        }
    }
}