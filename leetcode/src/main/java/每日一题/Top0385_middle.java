package 每日一题;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Top0385_middle {

    public static void main(String[] args) {
        Top0385_middle top0385_middle = new Top0385_middle();
        String s = "324";

        NestedInteger deserialize = top0385_middle.deserialize(s);
        System.out.println(deserialize);
    }

    /**
     * NestedInteger（序列）：{
     *     NestedInteger（数字）：1
     *     NestedInteger（序列）：{
     *         NestedInteger（数字）：2
     *         NestedInteger（数字）：3
     *     }
     *     NestedInteger（数字）：4
     * }
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Deque<NestedInteger> stack = new ArrayDeque<>();
        int num = 0;
        boolean negative = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
            } else if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                stack.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    if (negative) {
                        num *= -1;
                    }
                    stack.peek().add(new NestedInteger(num));
                }
                num = 0;
                negative = false;
                if (c == ']' && stack.size() > 1) {
                    NestedInteger ni = stack.pop();
                    stack.peek().add(ni);
                }
            }
        }
        return stack.pop();
    }


    public class NestedInteger {

        int val;

        List<NestedInteger> list;

        // Constructor initializes an empty nested list.
        public NestedInteger() {}

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.val = value;
            list = new ArrayList<>();
        }

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return val;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.val = value;
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            list.add(ni);
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return list;
        }

        @Override
        public String toString() {
            return "NestedInteger{" +
                    "val=" + val +
                    ", list=" + list +
                    '}';
        }
    }
}
