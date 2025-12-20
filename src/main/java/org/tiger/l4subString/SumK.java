package org.tiger.l4subString;

/**
 * @ClassName SumK
 * @Description 合为k的子数组
 * @Author tiger
 * @Date 2025/10/31 21:14
 */

import java.util.HashMap;
import java.util.HashSet;

/**
 *给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * */
public class SumK {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(subSum(nums, k));
    }

    public static int subSum(int[] nums, int k){
        // 前缀和思想
        int count = 0;
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // key:前缀和，value:出现次数
        map.put(0,1);

        for(int i =0;i<nums.length; i++){
            preSum += nums[i];
            //  preSum(i) - preSum(j) [这就是子数组和] = k  => preSum(j) = preSum(i) - k
            // 我们要找到preSum(j) 的个数就是子数组之和等于k的个数
            if(map.containsKey(preSum - k)){
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0)+ 1);
        }

        return count;
    }
}
