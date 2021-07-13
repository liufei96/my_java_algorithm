package com.liufei.swordFinger;

/**
 * 剑指 Offer 12. 矩阵中的路径 [ https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/ ]
 * <p>
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * <p>
 * A B C E
 * S F C S
 * A D E E
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 */
public class Algorithm_012 {

    public static void main(String[] args) {
        Algorithm_012 algorithm_012 = new Algorithm_012();
        String word = "ABCCED";
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        boolean exist = algorithm_012.exist(board, word);
        System.out.println(exist);
    }

    /**
     * 使用dfs
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // dfs。从(0, 0) 开始
                if (chars[0] == board[i][j] && dfs(board, chars, visited, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    boolean dfs(char[][] board, char[] chars, boolean[][] visited, int i, int j, int start) {
        // 这个不能少。
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || chars[start] != board[i][j] || visited[i][j]) {
            return false;
        }
        if (start == chars.length - 1) {
            return true;
        }
        // 在遍历到当前 board[i][j] 的时候，首先应将该位置的 visited[i][j] 设置为 true，表明访问过；访问过的，不能在重新访问
        visited[i][j] = true;
        boolean ans = dfs(board, chars, visited, i, j + 1, start + 1) ||
                dfs(board, chars, visited, i, j - 1, start + 1) ||
                dfs(board, chars, visited, i + 1, j, start + 1) ||
                dfs(board, chars, visited, i - 1, j, start + 1);
        visited[i][j] = false;
        return ans;
    }

}
