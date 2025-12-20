package org.tiger.lev9backtracking;

/**
 * @ClassName SumOfCombination
 * @Description 组合总和问题
 * @Author tiger
 * @Date 2025/11/6 15:23
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * */

// 核心在于递归中分为两种情况 选择当前数，跳过当前数
public class SumOfCombination {
    public static void main(String[] args) {
        // 示例输入：候选数字数组和目标和
        int[] candidates = {2, 3, 6, 7};
        int targetSum = 7;
        // 计算并打印所有可能的组合
        System.out.println(findCombinationSums(candidates, targetSum));
    }

    /**
     * 寻找候选数组中所有可以使数字和等于目标值的组合（元素可重复使用）
     * 例如：输入[2,3,6,7]和目标7，输出[[2,2,3],[7]]
     *
     * @param candidates 候选数字数组（元素无重复且为正整数）
     * @param target     目标和（正整数）
     * @return 所有符合条件的组合列表，若没有则返回空列表
     */
    public static List<List<Integer>> findCombinationSums(int[] candidates, int target) {
        List<List<Integer>> combinationResults = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>(); // 存储当前正在构建的组合

        // 处理空数组的特殊情况
        if (candidates == null) {
            return combinationResults;
        }

        // 调用深度优先搜索寻找所有符合条件的组合，从索引0开始处理
        depthFirstSearch(candidates, target, combinationResults, currentCombination, 0);
        return combinationResults;
    }

    /**
     * 深度优先搜索(DFS)寻找组合总和的核心方法
     * 采用回溯算法思想：对每个元素，要么不选（直接处理下一个元素），要么选（可重复选），直到和等于目标值
     *
     * @param candidates         候选数字数组
     * @param remainingTarget    剩余需要达成的目标和（初始为target，逐步递减）
     * @param combinationResults 存储所有符合条件的组合结果
     * @param currentCombination 当前正在构建的组合
     * @param currentIndex       当前处理的元素索引（用于避免重复组合和控制元素复用）
     */
    public static void depthFirstSearch(int[] candidates, int remainingTarget,
                                        List<List<Integer>> combinationResults,
                                        List<Integer> currentCombination, int currentIndex) {
        // 递归终止条件1：当处理完所有元素时，结束当前分支的搜索
        if (currentIndex == candidates.length) {
            return;
        }

        // 递归终止条件2：当剩余目标和为0时，当前组合符合条件，加入结果集
        if (remainingTarget == 0) {
            combinationResults.add(new ArrayList<>(currentCombination));
            return;
        }

        // 情况1：不选择当前索引的元素，直接处理下一个元素（索引+1）
        depthFirstSearch(candidates, remainingTarget, combinationResults, currentCombination, currentIndex + 1);

        // 情况2：选择当前索引的元素（仅当选择后剩余目标和非负时）
        if (remainingTarget - candidates[currentIndex] >= 0) {
            // 将当前元素加入组合
            currentCombination.add(candidates[currentIndex]);
            // 递归处理：剩余目标和减去当前元素值，索引不变（允许重复选择当前元素）
            depthFirstSearch(candidates, remainingTarget - candidates[currentIndex],
                    combinationResults, currentCombination, currentIndex);
            // 回溯：移除最后添加的元素，尝试其他组合可能性
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
