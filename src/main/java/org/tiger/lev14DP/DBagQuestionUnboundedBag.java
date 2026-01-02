package org.tiger.lev14DP;

/**
 * @ClassName DBagQuestionUnboundedBag
 * @Description 背包问题基础 完全背包问题
 * @Author tiger
 * @Date 2025/12/24 14:08
 */
public class DBagQuestionUnboundedBag {
    public static void main(String[] args) {
        /**
         * 题目描述：
         * @param weights: 物品重量数组 weights[i]表示第i个物品的重量
         * @param values: 物品价值数组 values[i] 表示第i个物品的价值
         * @param capacity: 背包容量表示背包最大的容量数
         * @return: 返回背包能装的最大价值，每个物品用量无限制
         * */
        int[] weights = {1, 3, 4};
        int[] values = {15,20,30};
        int capacity = 4;
        System.out.println("物品重复使用，在容量为" + capacity + "的背包中，能装的最大价值为：" + sovle(weights, values, capacity));
    }

    public static int sovle(int weights[], int[] values, int capacity){
        // 初始化dp数组
        int[] dp = new int[capacity + 1];

        // 遍历
        for(int i = 0; i < weights.length; i++){
            for(int j = weights[i]; j <= capacity; j ++){
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }

}
