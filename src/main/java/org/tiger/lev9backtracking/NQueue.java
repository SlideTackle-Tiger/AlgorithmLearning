package org.tiger.lev9backtracking;

/**
 * @ClassName NQueue
 * @Description n皇后问题
 * @Author tiger
 * @Date 2025/11/9 16:50
 */

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * */

import java.util.*;

/**
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * */
public class NQueue {
    public static void main(String[] args) {
        // 示例输入：4皇后问题（n=4）
        System.out.println(solveNQueens(4));
    }

    /**
     * 解决N皇后问题，生成所有合法的皇后摆放方案
     * N皇后规则：在n×n棋盘上放置n个皇后，使它们互不攻击（同一行、列、对角线无多个皇后）
     *
     * @param n 棋盘大小（即皇后数量）
     * @return 所有合法摆放方案的列表，每个方案用字符串列表表示棋盘状态
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutionResults = new ArrayList<>(); // 存储所有合法摆放方案
        int[] queenColumns = new int[n]; // 记录每个皇后的列位置：queenColumns[row] = col（第row行皇后在第col列）
        Arrays.fill(queenColumns, -1); // 初始化：-1表示该位置未放置皇后

        // 用于快速判断冲突的集合：避免同一列、两条对角线出现多个皇后
        Set<Integer> usedColumns = new HashSet<>(); // 已使用的列（防止列冲突）
        Set<Integer> diagonal1 = new HashSet<>(); // 左上→右下对角线（行-列 为固定值，防止对角线冲突）
        Set<Integer> diagonal2 = new HashSet<>(); // 右上→左下对角线（行+列 为固定值，防止对角线冲突）

        // 调用回溯算法，从第0行开始摆放皇后
        backtrackNQueens(solutionResults, queenColumns, n, 0, usedColumns, diagonal1, diagonal2);
        return solutionResults;
    }

    /**
     * 回溯算法摆放皇后的核心方法
     * 核心逻辑：按行依次摆放皇后，每一行尝试所有合法列位置，通过集合快速判断冲突，递归+回溯探索所有方案
     *
     * @param solutionResults 存储所有合法摆放方案的列表
     * @param queenColumns    记录每个皇后列位置的数组（索引=行号，值=列号）
     * @param n               棋盘大小（皇后数量）
     * @param currentRow      当前正在摆放皇后的行号（从0开始）
     * @param usedColumns     已使用的列集合（判断列冲突）
     * @param diagonal1       左上→右下对角线集合（行-列，判断该方向冲突）
     * @param diagonal2       右上→左下对角线集合（行+列，判断该方向冲突）
     */
    public static void backtrackNQueens(List<List<String>> solutionResults, int[] queenColumns, int n,
                                        int currentRow, Set<Integer> usedColumns,
                                        Set<Integer> diagonal1, Set<Integer> diagonal2) {
        // 递归终止条件：所有行都已摆放皇后（currentRow == n），生成棋盘状态并加入结果集
        if (currentRow == n) {
            List<String> chessboard = generateChessboard(queenColumns, n);
            solutionResults.add(chessboard);
            return;
        }

        // 遍历当前行的所有列，尝试摆放皇后
        for (int currentCol = 0; currentCol < n; currentCol++) {
            // 计算当前位置对应的两条对角线标识
            int diag1Key = currentRow - currentCol; // 左上→右下对角线的唯一标识
            int diag2Key = currentRow + currentCol; // 右上→左下对角线的唯一标识

            // 冲突判断：若当前列或两条对角线已被占用，跳过该列
            if (usedColumns.contains(currentCol)) {
                continue; // 列冲突，跳过
            }
            if (diagonal1.contains(diag1Key)) {
                continue; // 左上→右下对角线冲突，跳过
            }
            if (diagonal2.contains(diag2Key)) {
                continue; // 右上→左下对角线冲突，跳过
            }

            // 选择当前列：摆放皇后，记录占用状态
            queenColumns[currentRow] = currentCol;
            usedColumns.add(currentCol);
            diagonal1.add(diag1Key);
            diagonal2.add(diag2Key);

            // 递归处理下一行（当前行摆放完成，继续摆下一行）
            backtrackNQueens(solutionResults, queenColumns, n, currentRow + 1, usedColumns, diagonal1, diagonal2);

            // 回溯：撤销当前选择，清除占用状态，尝试其他列
            queenColumns[currentRow] = -1;
            usedColumns.remove(currentCol);
            diagonal1.remove(diag1Key);
            diagonal2.remove(diag2Key);
        }
    }

    /**
     * 根据皇后位置数组，生成可视化的棋盘状态
     * 棋盘用字符串列表表示：每个字符串对应一行，'.'表示空位置，'Q'表示皇后
     *
     * @param queenColumns 记录皇后列位置的数组（索引=行号，值=列号）
     * @param n            棋盘大小
     * @return 可视化的棋盘状态列表
     */
    public static List<String> generateChessboard(int[] queenColumns, int n) {
        List<String> chessboard = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            char[] rowChars = new char[n];
            Arrays.fill(rowChars, '.'); // 初始化当前行为空（全是'.'）
            rowChars[queenColumns[row]] = 'Q'; // 在皇后所在列标记'Q'
            chessboard.add(new String(rowChars));
        }
        return chessboard;
    }




}
