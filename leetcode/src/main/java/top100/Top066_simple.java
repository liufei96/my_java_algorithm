package top100;

import java.util.Arrays;

public class Top066_simple {

    public static void main(String[] args) {
        Top066_simple top066_simple = new Top066_simple();
        int[] nums = {1,2,3};
        int[] ints = top066_simple.plusOne(nums);
        System.out.println(Arrays.toString(ints));

    }

    public int[] plusOne(int[] digits) {
        int tmp = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i] + tmp;
            digits[i] = num % 10;
            tmp = num / 10;
        }
        if  (tmp == 0) {
            return digits;
        } else {
            int[] ans = new int[digits.length + 1];
            ans[0] = tmp;
            System.arraycopy(digits, 0, ans, 1, digits.length);
            return ans;
        }
    }
}
