package 剑指Offer;

/**
 * 剑指 Offer 11. 旋转数组的最小数字 [ https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/ ]
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class Algorithm_011 {


    public static void main(String[] args) {
        Algorithm_011 algorithm_011  = new Algorithm_011();
        int[] numbers = { 3,4,5,1,2 };
        int res = algorithm_011.minArray(numbers);
        System.out.println(res);

        System.out.println(algorithm_011.minArray2(numbers));
    }

    /**
     * 通用做法
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    /**
     * 使用二分法查找
     * @param numbers
     * @return
     */
    public int minArray2(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            // 二分查找主要计算mid。记住mid = low + (high - low) / 2
            int mid = low + (high - low) / 2;
            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

}
