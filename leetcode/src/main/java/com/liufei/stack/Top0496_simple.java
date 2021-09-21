package com.liufei.stack;

import java.util.*;

public class Top0496_simple {

    public static void main(String[] args) {
        int[] num1 = {2,4};
        int[] num2 = {1,2, 3,4};
        int[] ans = nextGreaterElement3(num1, num2);
        System.out.println(Arrays.toString(ans));
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int num = -1;
            boolean show = false;
            for (int j : nums2) {
                if (nums1[i] < j && show) {
                    num = j;
                    break;
                }
                if (nums1[i] == j) {
                    show = true;
                }
            }
            ans[i] = num;
        }
        return ans;
    }

    /**
     * 单调栈
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        // 先遍历num1
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                Integer preIndex = stack.pop();
                if (map.containsKey(nums2[preIndex])) {
                    ans[map.get(nums2[preIndex])] = nums2[i];
                }
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 单调栈
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
