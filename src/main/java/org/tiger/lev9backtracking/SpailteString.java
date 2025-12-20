package org.tiger.lev9backtracking;

/**
 * @ClassName SpailteString
 * @Description 分割回文子串
 * @Author tiger
 * @Date 2025/11/7 15:51
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * */

/**
 * 回溯 + 枚举动态规划
 * */
public class SpailteString {
    public static void main(String[] args) {
        // 示例输入：待分割的字符串
        String inputStr = "aab";
        // 生成并打印所有可能的回文分割方案
        System.out.println(generatePalindromePartitions(inputStr));
    }

    /**
     * 生成字符串的所有可能回文分割方案
     * 回文分割是指将字符串分割为若干个子串，每个子串均为回文串（正读和反读一致）
     * 例如：输入"aab"，输出[["a","a","b"],["aa","b"]]
     *
     * @param s 待分割的字符串
     * @return 所有回文分割方案的列表，每个方案是一个回文子串列表
     */
    public static List<List<String>> generatePalindromePartitions(String s) {
        List<List<String>> partitionResults = new ArrayList<>(); // 存储所有回文分割结果
        List<String> currentPartition = new ArrayList<>(); // 存储当前正在构建的分割方案
        int strLength = s.length();

        // 构建回文子串判断矩阵：f[i][j]表示s[i..j]（闭区间）是否为回文串
        boolean[][] isPalindromeMatrix = new boolean[strLength][strLength];
        // 初始化：单个字符一定是回文串，所有f[i][i] = true
        for (int i = 0; i < strLength; i++) {
            Arrays.fill(isPalindromeMatrix[i], true);
        }

        // 动态规划预处理回文矩阵（从长度为2的子串开始倒序计算）
        // 子串长度越长，依赖更短的子串结果，倒序遍历确保计算顺序正确
        for (int i = strLength - 1; i >= 0; i--) {
            for (int j = i + 1; j < strLength; j++) {
                // 核心判断：s[i..j]是回文串的条件 = 首尾字符相等 + 中间子串s[i+1..j-1]是回文串
                isPalindromeMatrix[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindromeMatrix[i + 1][j - 1];
            }
        }

        // 调用深度优先搜索，从字符串起始位置（索引0）开始生成分割方案
        backtrackPartition(partitionResults, currentPartition, isPalindromeMatrix, s, 0, strLength);
        return partitionResults;
    }

    /**
     * 回溯算法生成回文分割方案的核心方法
     * 核心逻辑：从当前起始索引开始，枚举所有可能的分割终点，若子串为回文则加入方案，递归处理剩余部分
     *
     * @param partitionResults  存储所有回文分割结果的列表
     * @param currentPartition  当前正在构建的分割方案（回文子串列表）
     * @param isPalindromeMatrix 回文子串判断矩阵（预处理结果，避免重复计算）
     * @param s                 待分割的原始字符串
     * @param startIndex        当前分割的起始索引（从该位置开始寻找下一个回文子串）
     * @param strLength         原始字符串的长度（用于判断分割终止条件）
     */
    public static void backtrackPartition(List<List<String>> partitionResults, List<String> currentPartition,
                                          boolean[][] isPalindromeMatrix, String s, int startIndex, int strLength) {
        // 递归终止条件：当起始索引达到字符串长度时，说明已完成一次完整分割
        if (startIndex == strLength) {
            partitionResults.add(new ArrayList<>(currentPartition));
            return;
        }

        // 枚举当前起始索引后的所有可能终点（j从startIndex到strLength-1）
        for (int endIndex = startIndex; endIndex < strLength; endIndex++) {
            // 若s[startIndex..endIndex]是回文串，则加入当前分割方案
            if (isPalindromeMatrix[startIndex][endIndex]) {
                // 截取回文子串并添加到当前方案
                currentPartition.add(s.substring(startIndex, endIndex + 1));
                // 递归处理剩余部分：从endIndex+1开始继续分割
                backtrackPartition(partitionResults, currentPartition, isPalindromeMatrix, s, endIndex + 1, strLength);
                // 回溯：移除最后添加的回文子串，尝试其他分割方式
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }
}
