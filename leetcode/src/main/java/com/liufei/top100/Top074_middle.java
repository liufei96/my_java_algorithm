package com.liufei.top100;

public class Top074_middle {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 3;
        Top074_middle top074_middle = new Top074_middle();
        boolean ans = top074_middle.searchMatrix(matrix, target);
        boolean ans2 = top074_middle.searchMatrix2(matrix, target);
        System.out.println(ans);
        System.out.println(ans2);
    }

    /**
     * 看左上角，像不像一颗二叉平衡树
     *
     * 两种方法殊途同归，都利用了二分查找，在二维矩阵上寻找目标值。值得注意的是，
     * 若二维数组中的一维数组的元素个数不一，方法二将会失效，而方法一则能正确处理。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0,j = col-1;
        int node;
        while(i < row && j >= 0){
            node = matrix[i][j];
            if(node < target)
                i++;
            else if(node > target)
                j--;
            else
                return true;
        }
        return false;
    }

    /**
     * 两次二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 二分法找出第几行
        int rowLeft = 0, rowRight = m, midRow = -1;
        while (rowLeft < rowRight) {
            midRow = (rowLeft + rowRight) >> 1;
            if (matrix[midRow][0] > target) {
                rowRight = midRow;
            } else if (matrix[midRow][n - 1] < target) {
                rowLeft = midRow + 1;
            } else {
                break;
            }
        }

        if (midRow == -1) {
            return false;
        }

        // 二分法查找这一行的第几个数字
        int colLeft = 0, colRight = n;
        while (colLeft < colRight) {
            int midCol = (colLeft + colRight) >> 1;
            if (matrix[midRow][midCol] > target) {
                colRight = midCol;
            } else if (matrix[midRow][midCol] < target) {
                colLeft = midCol + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
