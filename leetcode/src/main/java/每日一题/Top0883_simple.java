package 每日一题;

/**
 * 883. 三维形体投影面积  [ https://leetcode-cn.com/problems/projection-area-of-3d-shapes/ ]
 */
public class Top0883_simple {

    public static void main(String[] args) {
        int[][] grid = {{1, 2}, {3, 4}};
        int res = projectionArea(grid);
        System.out.println(res);
    }


    public static int projectionArea2(int[][] grid) {
        int xy = 0, yz = 0, xz = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {  //计算俯视图
                    xy++;
                }
                if (grid[i][j] > max) {  //计算左视图
                    max = grid[i][j];
                }
            }
            yz += max;
        }
        //计算主视图
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j < n; j++) {
                if (grid[j][i] > max) {
                    max = grid[j][i];
                }
            }
            xz += max;
        }
        return xy + yz + xz;
    }

    /**
     * 根据题意，x 轴对应行，y 轴对应列，z 轴对应网格的数值。
     *
     * 因此：
     *
     * xy 平面的投影面积等于网格上非零数值的数目；
     * yz 平面的投影面积等于网格上每一列最大数值之和；
     * zx 平面的投影面积等于网格上每一行最大数值之和。
     *
     * @param grid
     * @return
     */
    public static int projectionArea(int[][] grid) {
        int xy = 0, yz = 0, xz = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            int yzHeight = 0, xzHeight = 0;
            for (int j = 0; j < n; j++) {
                xy += grid[i][j] > 0 ? 1 : 0;
                yzHeight = Math.max(yzHeight, grid[j][i]);
                xzHeight = Math.max(xzHeight, grid[i][j]);
            }
            yz += yzHeight;
            xz += xzHeight;
        }
        return xy + yz + xz;
    }
}
