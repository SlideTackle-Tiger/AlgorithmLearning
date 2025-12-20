package org.tiger.lev9backtracking;

/**
 * @ClassName WordSearch
 * @Description 单词搜索
 * @Author tiger
 * @Date 2025/11/7 14:48
 */
/**
 *给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
 * 输出：true
 * */
public class WordSearch {
    public static void main(String[] args) {
        // 示例输入：二维字符矩阵和目标单词
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String targetWord = "ABCCED";
        // 检查矩阵中是否存在目标单词并打印结果
        System.out.println(isWordExists(board, targetWord));
    }

    /**
     * 检查二维字符矩阵中是否存在指定的单词（单词由相邻单元格的字符组成，相邻指水平或垂直方向）
     *
     * @param board 二维字符矩阵
     * @param word  待查找的目标单词
     * @return 若矩阵中存在该单词则返回true，否则返回false
     */
    public static boolean isWordExists(char[][] board, String word) {
        int rowCount = board.length; // 矩阵的行数
        // 处理空矩阵的特殊情况
        if (rowCount == 0) {
            return false;
        }
        int colCount = board[0].length; // 矩阵的列数
        boolean[][] isVisited = new boolean[rowCount][colCount]; // 标记单元格是否已访问（避免重复使用）

        // 从矩阵的每个单元格出发，尝试匹配单词
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                boolean isFound = checkPath(board, isVisited, i, j, word, 0);
                if (isFound) {
                    return true;
                }
            }
        }
        // 所有起点均未匹配成功
        return false;
    }

    /**
     * 递归检查从指定单元格出发，是否能匹配单词的指定位置及后续字符
     * 采用回溯算法：标记当前单元格为已访问 -> 探索四个方向 -> 回溯（取消标记）
     *
     * @param board     二维字符矩阵
     * @param isVisited 标记矩阵，记录单元格是否已访问
     * @param row       当前检查的单元格行索引
     * @param col       当前检查的单元格列索引
     * @param word      目标单词
     * @param charIndex 待匹配的单词字符索引（从0开始）
     * @return 若从当前位置能匹配到单词剩余字符则返回true，否则返回false
     */
    public static boolean checkPath(char[][] board, boolean[][] isVisited, int row, int col, String word, int charIndex) {
        // 1. 若当前单元格字符与目标字符不匹配，直接返回false
        if (board[row][col] != word.charAt(charIndex)) {
            return false;
        }
        // 2. 若已匹配到单词最后一个字符，说明找到完整路径，返回true
        else if (charIndex == word.length() - 1) {
            return true;
        }

        // 标记当前单元格为已访问（避免在后续路径中重复使用）
        isVisited[row][col] = true;
        // 定义四个探索方向：右、左、下、上（水平和垂直方向）
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean isFound = false;

        // 探索四个方向的相邻单元格
        for (int[] dir : directions) {
            int newRow = row + dir[0]; // 新行索引（当前行+方向行偏移）
            int newCol = col + dir[1]; // 新列索引（当前列+方向列偏移）

            // 检查新单元格是否在矩阵范围内，且未被访问过
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (!isVisited[newRow][newCol]) {
                    // 递归检查下一个字符（索引+1）
                    boolean nextMatch = checkPath(board, isVisited, newRow, newCol, word, charIndex + 1);
                    if (nextMatch) {
                        isFound = true;
                        break; // 找到路径后无需继续探索其他方向
                    }
                }
            }
        }

        // 回溯：取消当前单元格的访问标记（允许该单元格在其他路径中被使用）
        isVisited[row][col] = false;
        return isFound;
    }
}
