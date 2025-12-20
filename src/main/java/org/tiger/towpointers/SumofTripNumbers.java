package org.tiger.towpointers;

/**
 * @ClassName SumofTripNumbers
 * @Description 三数之和
 * @Author tiger
 * @Date 2025/10/30 10:32
 */

/**
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * */

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

/**
 * 算法思想：
 * */
public class SumofTripNumbers {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        Arrays.sort(nums);
        System.out.println(solve(nums));
    }

    public static List<List<Integer>> solve(int[] nums){
        Arrays.sort(nums);
        //System.out.println(Arrays.toString(nums));
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for(int i =0;i<n;i++){
            // i就是first指针
            if(i > 0 && nums[i-1] == nums[i]){continue;} // 去重
            for(int second = i + 1; second < n -1; second++){
                int third = n -1;
                int target = -nums[i]; // second + third = target
                if(second > i + 1 && nums[second - 1] == nums[second]){continue;}
                while(third > second && nums[second] + nums[third] > target){
                    third--;
                }
                if(nums[second] + nums[third] == target){
                    List<Integer> arr = new ArrayList<>();
                    arr.add(nums[i]);
                    arr.add(nums[second]);
                    arr.add(nums[third]);
                    result.add(arr);
                }
            }
        }

        return result;
    }
}
