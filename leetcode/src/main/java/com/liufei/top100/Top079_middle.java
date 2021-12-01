package com.liufei.top100;


/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Top079_middle {

    public static void main(String[] args) {
        Top079_middle top079_middle = new Top079_middle();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        boolean exist = top079_middle.exist(board, word);
        System.out.println(exist);
    }


    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean backtracking = backtracking(board, i, j, word, visited, 0);
                if (backtracking) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtracking(char[][] board, int i, int j, String word, boolean[][] visited, int position) {
        if (position >= word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(position)) {
            return false;
        }
        visited[i][j] = true;
        boolean result = backtracking(board, i + 1, j, word, visited, position + 1) ||
                backtracking(board, i - 1, j, word, visited, position + 1) ||
                backtracking(board, i, j + 1, word, visited, position + 1) ||
                backtracking(board, i, j - 1, word, visited, position + 1);
        visited[i][j] = false;
        return result;
    }
}
