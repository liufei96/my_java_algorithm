package 栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Top739_middle {

    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] ints = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 单调栈
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer preIndex  = stack.pop();
                ans[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static int[] dailyTemperatures2(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length ; i++) {
            int count = 0;
            for (int j = i + 1; j < temperatures.length; j++) {
                count++;
                if (temperatures[i] < temperatures[j]) {
                    break;
                }
                if (j == temperatures.length - 1) {
                    count = 0;
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}
