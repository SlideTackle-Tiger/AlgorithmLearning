package org.tiger.lev9backtracking;

/**
 * @ClassName SubSet
 * @Description 子集
 * @Author tiger
 * @Date 2025/11/3 10:38
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * */
public class SubSet {
    public static void main(String[] args) {
        // 示例输入数组
        int[] numbers = {1, 2, 3};
        // 生成并打印数组的所有子集（包含空集）
        System.out.println(generateAllSubsets(numbers));
    }

    /**
     * 生成指定数组的所有子集（包含空集和所有可能的非空子集）
     * 子集是指从数组中选取任意个元素（可以是0个）组成的集合，不考虑元素顺序且元素不重复
     *
     * @param numbers 输入的整数数组
     * @return 包含所有子集的列表，每个元素是一个表示子集的列表
     */
    public static List<List<Integer>> generateAllSubsets(int[] numbers) {
        List<Integer> currentSubset = new ArrayList<>(); // 存储当前正在构建的子集
        List<List<Integer>> subsetResults = new ArrayList<>(); // 用于存储所有子集结果
        int arrayLength = numbers.length; // 输入数组的长度

        // 处理空数组的特殊情况（直接返回只包含空集的结果）
        if (arrayLength == 0) {
            return subsetResults;
        }

        // 调用深度优先搜索生成所有子集，从索引0开始处理
        depthFirstSearch(subsetResults, currentSubset, numbers, 0);
        return subsetResults;
    }

    /**
     * 深度优先搜索(DFS)生成所有子集的核心方法
     * 采用"选或不选"的回溯算法思想：对每个元素，要么加入当前子集，要么不加入，以此遍历所有可能
     *
     * @param subsetResults 存储所有子集结果的列表
     * @param currentSubset 当前正在构建的子集
     * @param numbers       输入的整数数组
     * @param currentIndex  当前处理的元素索引（表示即将决定是否选择该索引对应的元素）
     */
    public static void depthFirstSearch(List<List<Integer>> subsetResults, List<Integer> currentSubset,
                                        int[] numbers, int currentIndex) {
        // 递归终止条件：当处理完所有元素（索引达到数组长度），当前子集已确定
        if (currentIndex == numbers.length) {
            // 将当前子集的副本添加到结果集（避免后续修改影响已保存的结果）
            subsetResults.add(new ArrayList<>(currentSubset));
            return;
        }

        // 情况1：选择当前索引的元素，加入当前子集
        currentSubset.add(numbers[currentIndex]);
        // 递归处理下一个元素（索引+1）
        depthFirstSearch(subsetResults, currentSubset, numbers, currentIndex + 1);

        // 回溯：撤销选择，将当前元素从子集移除
        currentSubset.remove(currentSubset.size() - 1);

        // 情况2：不选择当前索引的元素，直接处理下一个元素
        depthFirstSearch(subsetResults, currentSubset, numbers, currentIndex + 1);
    }
}
