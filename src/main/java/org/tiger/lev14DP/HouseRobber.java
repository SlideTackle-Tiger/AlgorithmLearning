package org.tiger.lev14DP;

/**
 * @ClassName HouseRobber
 * @Description 打家劫舍
 * @Author tiger
 * @Date 2025/12/9 07:59
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println("最大收益：" + rob(nums));
    }

    public static int rob(int[] nums){
        // 分析题目得到动态方程
        // dp[i] = max(dp[i-1],dp[i-2] + nums[i]) // 第i个房子的最大收益
        // 边界条件 dp[0] = nums[0], dp[1] = max(nums[0], nums[1])
        // 代码实现即可
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }

        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < length; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[length -1];

    }
}
