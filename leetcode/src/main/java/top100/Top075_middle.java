package top100;

import java.util.Arrays;

public class Top075_middle {

    public static void main(String[] args) {
        Top075_middle top075_middle = new Top075_middle();
        int[] nums = {2, 0, 2, 1, 1, 0};
        // int[] nums = {2, 0, 1};
        top075_middle.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 说下我的理解：三个指针num1、num2、num3将数组nums分成了3个分区，从左往右依次存储0、1、2。
     * 三个指针分别指向各自分区的尾部。
     * 从左到右遍历数组nums，(1)如果nums[i]=0,则1、2区都后移一个位置，给新来的0腾地方。
     * (2)如果是nums[i]=1，同样，2区后移一个位置，给新来的1腾地方。前面的0区无影响。
     *
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int num0 = 0, num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[num2++] = 2;
                nums[num1++] = 1;
                nums[num0++] = 0;
            } else if (nums[i] == 1) {
                nums[num2++] = 2;
                nums[num1++] = 1;
            } else {
                nums[num2++] = 2;
            }
        }
    }

    /**
     * 单指针，  两次遍历
     * 1、先将0的移动的最前面
     * 2、然后再讲1移动到0的后面
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                swap(nums, ptr, i);
                ptr++;
            }
        }
        for (int i = ptr; i < n; i++) {
            if (nums[i] == 1) {
                swap(nums, ptr, i);
                ptr++;
            }
        }
    }

    /**
     * 双指针，
     * 1、先将0的移动的最前面
     * 2、然后再讲1移动到0的后面
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                swap(nums, p1, i);
                p1++;
            } else if (nums[i] == 0) {
                swap(nums, p0, i);
                if (p0 < p1) {
                    swap(nums, p1, i);
                }
                p0++;
                p1++;
            }
        }
        System.out.println(p0);
        System.out.println(p1);
    }


    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
