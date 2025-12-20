package org.tiger.lev9backtracking;

/**
 * @ClassName Permutation
 * @Description 全排列问题
 * @Author tiger
 * @Date 2025/11/3 10:00
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * */

public class Permutation {
    public static void main(String[] args) {
        // 示例输入数组
        int[] numbers = {1, 2, 3};
        // 计算并打印数组的所有全排列
        System.out.println(generatePermutations(numbers));
    }

    /**
     * 生成指定数组的所有全排列
     * 全排列是指将数组中所有元素按照不同顺序重新排列的所有可能组合
     *
     * @param numbers 输入的整数数组
     * @return 包含所有全排列的列表，每个元素是一个表示排列的列表
     */
    public static List<List<Integer>> generatePermutations(int[] numbers) {
        int arrayLength = numbers.length; // 输入数组的长度
        List<List<Integer>> permutationResults = new ArrayList<>(); // 用于存储所有全排列结果

        // 处理空数组的特殊情况
        if (arrayLength == 0) {
            return permutationResults;
        }

        boolean[] isUsed = new boolean[arrayLength]; // 标记数组元素是否已被使用
        List<Integer> currentPath = new ArrayList<>(); // 存储当前正在构建的排列路径

        // 调用深度优先搜索进行全排列生成
        depthFirstSearch(numbers, arrayLength, 0, currentPath, isUsed, permutationResults);

        return permutationResults;
    }

    /**
     * 深度优先搜索(DFS)生成全排列的核心方法
     * 采用回溯算法思想：选择元素 -> 递归深入 -> 撤销选择(回溯)，以此探索所有可能的排列
     *
     * @param numbers         输入的整数数组
     * @param arrayLength     数组的长度（用于判断递归终止条件）
     * @param currentDepth    当前递归深度（表示当前排列已选择的元素个数）
     * @param currentPath     当前正在构建的排列路径
     * @param isUsed          标记数组元素是否已被使用的状态数组
     * @param permutationResults 存储所有全排列结果的列表
     */
    public static void depthFirstSearch(int[] numbers, int arrayLength, int currentDepth,
                                        List<Integer> currentPath, boolean[] isUsed,
                                        List<List<Integer>> permutationResults) {
        // 递归终止条件：当当前深度等于数组长度时，说明已构建出一个完整排列
        if (currentDepth == arrayLength) {
            // 将当前路径的副本添加到结果集（避免后续修改影响已保存的结果）
            permutationResults.add(new ArrayList<>(currentPath));
            return;
        }

        // 遍历数组，尝试将每个未使用的元素加入当前排列
        for (int i = 0; i < arrayLength; i++) {
            // 跳过已使用的元素
            if (!isUsed[i]) {
                // 选择当前元素：加入路径并标记为已使用
                currentPath.add(numbers[i]);
                isUsed[i] = true;

                // 递归深入：深度加1，继续构建排列的下一位
                depthFirstSearch(numbers, arrayLength, currentDepth + 1, currentPath, isUsed, permutationResults);

                // 回溯：撤销选择，将元素标记为未使用并从路径中移除，以便尝试其他元素
                // 默认以numsp[i]开头的已经处理完了，那么我们需要将i移除去到上层
                isUsed[i] = false;
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

}
