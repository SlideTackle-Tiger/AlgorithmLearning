package org.tiger.towpointers;

/**
 * @ClassName MoveZero
 * @Description 移动零
 * @Author tiger
 * @Date 2025/10/30 09:52
 */

import java.util.Arrays;

/**
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 双指针思想，快慢指针，慢指针为0，快指针不为0，交换快慢指针位置元素然后同时右移
 * 快指针为0则只移动快指针
 * */
public class MoveZero {
    public static void main(String[] args) {
        int[] nums = {2,1,0,3,12};
        solve(nums);
        System.out.println(Arrays.toString(nums));

    }

    public static void solve(int[] nums){
        int slow = 0, fast = 0;
        while(fast < nums.length){
            if(nums[fast] != 0){
                swap(nums, slow ,fast);
                slow++;
            }
            fast++;
        }
    }

    public static void swap(int[] nums, int slow,int fast){
        int temp = nums[slow];
        nums[slow] = nums[fast];
        nums[fast] = temp;
    }
}
