package com.yiyang.dynamic;

/**
 * 介绍动态规划
 * 首先,我们看一下官方定义:
 * 定义:
 * 动态规划算法是通过拆分问题，定义问题状态和状态之间的关系，使得问题能够以递推（或者说分治）的方式去解决。
 * 动态规划算法的基本思想与分治法类似，也是将待求解的问题分解为若干个子问题（阶段），按顺序求解子阶段，前一子问题的解，为后一子问题的求解提供了有用的信息。
 * 在求解任一子问题时，列出各种可能的局部解，通过决策保留那些有可能达到最优的局部解，丢弃其他局部解。依次解决各子问题，最后一个子问题就是初始问题的解。
 * <p>
 * 基本思想与策略编辑:
 * 由于动态规划解决的问题多数有重叠子问题这个特点，为减少重复计算，对每一个子问题只解一次，将其不同阶段的不同状态保存在一个二维数组中。
 * <p>
 * 参考资料：https://blog.csdn.net/baidu_28312631/article/details/47418773
 */
public class DynamicDemo {

    /**
     * 经典的数字三角行问题
     * 7
     * 3  8
     * 8   1  0
     * 2  7  4  4
     * 4 5   2  6   5
     * <p>
     * 在上面的数字三角形中寻找一条从顶部到底边的路径，使得路径上所经过的数字之和最大。路径上的每一步都只能往下或右下走。
     * 只需要求出最大和即可，不必给出具体路径。
     * 三角形行数大于1小于等于100，数字为 0 ~ 99
     * <p>
     * 分析：
     * 1. 用二位数组存放数字三角形
     * 2. D(r,j) 表示第r行的第j个数字，j从1开始
     * 3. maxSum(r,j)。
     * <p>
     * 典型的递归
     * D(r.j)出发，下一步只能走D(r+1, j) 或 D(r+1, j+1)
     */

    static int count = 1;

    public static void main(String[] args) {
        /**
         * 5
         * 7
         * 3 8
         * 8 1 0
         * 2 7 4 4
         * 4 5 2 6 5
         */
        int maxRow = 100;
        int[][] arr = new int[maxRow][maxRow];   //存储数字三角形
        arr[0][0] = 5;
        arr[1][0] = 7;
        arr[2][0] = 3;
        arr[2][1] = 8;
        arr[3][0] = 8;
        arr[3][1] = 1;
        arr[3][2] = 0;
        arr[4][0] = 2;
        arr[4][1] = 7;
        arr[4][2] = 4;
        arr[4][3] = 4;
        arr[5][0] = 4;
        arr[5][1] = 5;
        arr[5][2] = 2;
        arr[5][3] = 6;
        arr[5][4] = 5;
        int i = 0;
        int j = 0;
        int n = 6;  // n表示层数
        int[][] maxSumArr = new int[maxRow][maxRow];
        int[] maxSumArr2 = new int[maxRow];
        for (int k = 0; k < maxSumArr.length; k++) {
            for (int l = 0; l < maxSumArr[k].length; l++) {
                maxSumArr[k][l] = -1;
            }
            maxSumArr2[k] = -1;
        }
        // int maxSum = getMaxSum(arr, n, i, j);
        // 动态规划
        int maxSum2 = getMaxSum2(arr, maxSumArr, n, i, j);
        System.out.println(maxSum2);
        int maxSum3 = getMaxSum3(arr, maxSumArr2, n, i, j);
        System.out.println(maxSum3);
    }

    /**
     * 其实仔细观察,上面的解答过程时间复杂度难以想象的大,那是因为他对有的数字的解进行了多次的重复计算,具体如下图:
     * 时间复杂度是 2^n。6层，总共循环了64此
     *
     * @param D
     * @param n
     * @param i
     * @param j
     * @return
     */
    public static int getMaxSum(int[][] D, int n, int i, int j) {
        if (i == n - 1) {
            return D[i][j];
        }
        int x = getMaxSum(D, n, i + 1, j);
        int y = getMaxSum(D, n, i + 1, j + 1);
        return Math.max(x, y) + D[i][j];
    }

    /**
     * 动态规划
     * 优化：
     * 如果每算出一个MaxSum(r,j)就保存下来，下次用到其值的时候，直接取就可以了，则可以避免重复的计算。
     * 可以用到0(n^2)完成计算，因为三角形的数字总数是n(n+1)/2
     */
    public static int getMaxSum2(int[][] D, int[][] maxSumArr, int n, int i, int j) {
        if (maxSumArr[i][j] != -1) {
            return maxSumArr[i][j];
        }
        if (i == n - 1) {
            maxSumArr[i][j] = D[i][j];
        } else {
            int x = getMaxSum2(D, maxSumArr, n, i + 1, j);
            int y = getMaxSum2(D, maxSumArr, n, i + 1, j + 1);
            maxSumArr[i][j] = Math.max(x, y) + D[i][j];
        }
        return maxSumArr[i][j];
    }

    /**
     * 继续优化。
     * 将二维数组变成一维数组
     *
     * @param D
     * @param n
     * @param i
     * @param j
     * @return
     */
    public static int getMaxSum3(int[][] D, int[] maxSumArr, int n, int i, int j) {
        if (maxSumArr[j] != -1) {
            return maxSumArr[j];
        }
        if (i == n - 1) {
            return D[i][j];
        }
        int x = getMaxSum3(D, maxSumArr, n, i + 1, j);
        int y = getMaxSum3(D, maxSumArr, n, i + 1, j + 1);
        maxSumArr[j] = Math.max(x, y) + D[i][j];
        return maxSumArr[j];
    }
}
