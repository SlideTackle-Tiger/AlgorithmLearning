package org.tiger.lev14DP;

/**
 * @ClassName DBagQuestionBaseOneZeroBag
 * @Description 背包问题基础之01背包问题
 * @Author tiger
 * @Date 2025/12/21 19:49
 */
public class DBagQuestionBaseOneZeroBag {
    public static void main(String[] args) {
        /**
         * 题目描述：
         * @param weights: 物品重量数组 weights[i]表示第i个物品的重量
         * @param values: 物品价值数组 values[i] 表示第i个物品的价值
         * @param capacity: 背包容量表示背包最大的容量数
         * @return: 返回背包能装的最大价值
         * */
        int[] weights = {1, 3, 4};
        int[] values = {15,20,30};
        int capacity = 4;
        System.out.println("最大价值(暴力解法)：" + solveByViolent(weights, values,capacity));
        System.out.println("最大价值(动态规划)：" + solveByDP(weights, values, capacity));
        System.out.println("最大价值(动态规划一维dp数组)" + solveByDP1D(weights, values, capacity));

    }

    public static int maxValue;
    public static int solveByViolent(int[] weights, int[] values, int capacity){
        maxValue = 0; // 初始化最大值
        dfs(weights, values, capacity, 0,0,0);
        return maxValue;
    }
    /**
     * 深度优先搜索（递归核心）
     * @param weights 物品重量数组
     * @param values 物品价值数组
     * @param capacity 背包最大容量
     * @param index 当前处理的物品索引
     * @param currentWeight 当前组合的总重量
     * @param currentValue 当前组合的总价值
     */
    public static void dfs(int[] weights, int[] values, int capacity,
                           int index, int currentWeight, int currentValue){
        // 递归终止条件，当前遍历位置等于物品长度
        if(index == weights.length){
            // 若总重量小于容量，且当前currentValue 大于maxValue 则更新maxValue
            if(currentWeight <= capacity && currentValue > maxValue){
                maxValue = currentValue;
            }
            return;
        }

        // 减枝
        if(currentWeight > capacity){
            return; // 当前容量已经大于最大容量了后续不必遍历
        }

        // 决策1 不选第index个物品，直接递归下一个物品
        dfs(weights, values, capacity, index + 1, currentWeight, currentValue);

        // 决策2 选择第index个物品，需要满足重量小于容量
        int newWeight = currentWeight + weights[index];
        int newValue = currentValue + values[index];
        dfs(weights, values,capacity, index + 1, newWeight, newValue);
    }

    // dp解法
    public static int solveByDP(int[] weights, int[] values, int capacity){
        // 边界校验，重量数组空 价值数组空 重量数组长度不等于价值数组 容量小于等于0 都属于异常情况
        if(weights == null || values == null || weights.length != values.length || capacity <= 0){
            return 0;
        }

        // 初始化dp
        int dpRows = weights.length; // 物品一共有多少个
        int dpCols = capacity + 1;
        int[][] dp = new int[dpRows][dpCols];

        // 初始化第一行
        for(int j = 0; j < dpCols;  j++){
            if(weights[0] <= j){
                dp[0][j] = values[0];
            }
        }
        // 初始化第一列 由于java new int[][] 会自动赋初值0，所以我们不必初始化
        System.out.println("初始化dp数组：");
        printDPArray(dp);
        // 开始遍历 外层物品，内层容量
        for(int i = 1; i < dpRows; i ++){
            for(int j = 0; j < dpCols; j++){
                // 剪枝，如果当前背包容量小于当前物品重量，那么无论如何都无法放入背包，我们直接取dp数组上方的值。
                if(j < weights[i]){
                    // 不放物品i 当前物品的重量大于j容量
                    dp[i][j] = dp[i - 1][j];

                }else {
                    // 当前容量在加入当前物品重量的情况下不超重，我们有两种方案，放或者不放。
                    // 方案一放 那么我们的最大价值就是 dp[i - 1][j - weights[i]](物品0到物品i-1 放与不放，重量减去当前重量) + 当前物品价值
                    // 方案二不放 那么我们的最大价值还是dp数组上方的dp[i -1][j]
                    // 具体取方案一还是方案二要取决于二者谁的价值最大，我们取价值最大的那个方案。
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i -1][j - weights[i]] + values[i]);
                }
            }
        }
        return dp[dpRows - 1][dpCols -1];
    }
    // 打印dp数组函数
    public static void printDPArray(int[][] dp){
        int row = dp.length;
        int col = dp[0].length;
        for(int i = 0; i < row; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; j++){
                sb.append(dp[i][j]);
                sb.append(" 、");
            }
            System.out.println(sb.toString());
        }
    }

    public static int solveByDP1D(int[] weights, int[] values, int capacity){
        // 边界校验
        if(weights == null || values == null || weights.length != values.length || capacity <= 0){
            return 0;
        }

        // 初始化dp
        int itemNumber = weights.length; // 物品数量
        int[] dp = new int[capacity + 1]; // dp数组，数量要为容量（包含容量）所以这里加一
        // 初始化dp数组 都为0 Java new int[] 会自动赋初值0，所以不必初始化

        // 遍历
        for(int i =0; i < itemNumber; i++){
            for(int j = capacity; j >= weights[i]; j --){
                dp[j] = Math.max(dp[j] , dp[j - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }
}
