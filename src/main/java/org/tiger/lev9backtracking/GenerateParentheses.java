package org.tiger.lev9backtracking;

/**
 * @ClassName GenerateParentheses
 * @Description 括号生成
 * @Author tiger
 * @Date 2025/11/6 16:03
 */

import java.util.ArrayList;
import java.util.List;

/**
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * */

// 核心回溯分为三种情况，case1 数量达到了n的2倍表示已经足够，case2 左括号小于n 添加一个左 回溯，case3 右括号小于n 添加一个右 回溯
public class GenerateParentheses {
    public static void main(String[] args) {
        // 示例输入：生成3对有效括号
        System.out.println(generateValidParentheses(3));
    }

    /**
     * 生成所有由n对括号组成的有效括号组合
     * 有效括号组合需满足：左括号必须在对应的右括号之前，且任意前缀中左括号数量不少于右括号
     * 例如：n=3时，输出["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n 括号的对数（正整数）
     * @return 所有有效括号组合的列表
     */
    public static List<String> generateValidParentheses(int n) {
        List<String> validCombinations = new ArrayList<>();
        StringBuffer currentCombination = new StringBuffer();

        // 调用回溯算法生成所有有效组合
        backtrack(validCombinations, currentCombination, 0, 0, n);
        return validCombinations;
    }

    /**
     * 回溯算法生成有效括号组合的核心方法
     * 核心逻辑：通过控制左括号和右括号的添加顺序保证有效性——左括号数量不超过n，右括号数量不超过左括号
     *
     * @param validCombinations 存储所有有效括号组合的结果列表
     * @param currentCombination 用于构建当前括号组合的字符串缓冲区
     * @param openCount 已添加的左括号数量
     * @param closeCount 已添加的右括号数量
     * @param totalPairs 目标括号对数（n）
     */
    public static void backtrack(List<String> validCombinations, StringBuffer currentCombination,
                                 int openCount, int closeCount, int totalPairs) {
        // 递归终止条件：当当前组合长度等于2n时（n对括号共2n个字符），说明已生成一个完整组合
        if (currentCombination.length() == 2 * totalPairs) {
            validCombinations.add(currentCombination.toString());
            return;
        }

        // 情况1：添加左括号（仅当左括号数量未达到n时）
        if (openCount < totalPairs) {
            currentCombination.append('(');
            // 递归：左括号数量+1，继续构建组合
            backtrack(validCombinations, currentCombination, openCount + 1, closeCount, totalPairs);
            // 回溯：移除最后添加的左括号，尝试其他可能性
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }

        // 情况2：添加右括号（仅当右括号数量小于左括号数量时，保证有效性）
        if (closeCount < openCount) {
            currentCombination.append(')');
            // 递归：右括号数量+1，继续构建组合
            backtrack(validCombinations, currentCombination, openCount, closeCount + 1, totalPairs);
            // 回溯：移除最后添加的右括号，尝试其他可能性
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }
}
