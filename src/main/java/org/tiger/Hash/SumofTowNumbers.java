package org.tiger.Hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SumofTowNumbers
 * @Description leecode-1-两数之和
 * @Author tiger
 * @Date 2025/10/30 09:18
 */
public class SumofTowNumbers {
    // test
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(towSum(nums, target)));

    }
    // solution
    public static int[] towSum(int[] nums, int target){
        // map存储key：当前值，value：当前值索引
        Map<Integer, Integer> map = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            map.put(nums[i],i);
        }

        // 遍历num
        for(int i =0;i<nums.length;i++){
            int another = target - nums[i];
            if(map.containsKey(another)){
                return new int[]{i,map.get(another)};
            }
        }
        return new int[]{-1,-1};
    }
}
