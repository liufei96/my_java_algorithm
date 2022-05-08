package 每日一题;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Top0682_simple {

    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        int res = calPoints2(ops);
        System.out.println(res);
    }

    public static int calPoints(String[] ops) {
        int total = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < ops.length; i++) {
            String str = ops[i];
            if ("C".equals(str)) {
                stack.pop();
            } else if ("D".equals(str)) {
                int num = Integer.valueOf(stack.peek());
                stack.push(num * 2);
            } else if ("+".equals(str)) {
                int second = stack.pop();
                int first = stack.peek();
                // 再把pop的塞回去
                stack.push(second);
                stack.push(second + first);
            } else {
                stack.push(Integer.valueOf(ops[i]));
            }
        }
        while (!stack.isEmpty()) {
            total += stack.pop();
        }
        return total;
    }

    public static int calPoints2(String[] ops) {
        int[] arr =  new int[ops.length];
        int i = 0;
        for (String s : ops) {
            if ("C".equals(s)) {
                arr[i - 1] = 0;
                i--;
            } else if ("D".equals(s)) {
                arr[i] = arr[i - 1] * 2;
                i++;
            } else if ("+".equals(s)) {
                arr[i] = arr[i - 1] + arr[i - 2];
                i++;
            } else {
                arr[i] = Integer.valueOf(s);
                i++;
            }
        }
        int total = 0;
        for (int j = 0; j < i; j++) {
            total += arr[j];
        }
        return total;
    }
}
