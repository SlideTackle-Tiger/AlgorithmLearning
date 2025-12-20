package org.tiger.lev13Greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName DMaximizeSumOfArrayAfterKNegations
 * @Description n次取反后最大化的数组和
 * @Author tiger
 * @Date 2025/12/10 10:34
 */
public class DMaximizeSumOfArrayAfterKNegations {
    public static void main(String[] args) {
        int k = 1;
        int[] nums = {4,2,3};
        System.out.println("k次取反最大和：" + solve(k, nums));
    }

    public static int solve(int k, int[] nums){
        Arrays.sort(nums);
        for(int i =0;i<nums.length && k > 0;i++){
            if(nums[i] < 0){
                nums[i] = -nums[i];
                k --;
            }
        }
        if(k % 2 == 1){
            Arrays.sort(nums);
            nums[0] = - nums[0];
        }
        int sumResult = 0;
        for(int num : nums){
            sumResult += num;
        }
        return sumResult;
    }
}
