package org.tiger.lev10binarysearch;

import java.util.Arrays;

/**
 * @ClassName demo02
 * @Description
 * @Author tiger
 * @Date 2025/11/12 15:46
 */
public class demo02 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 10; // 1,2
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public static int[] searchRange(int[] nums, int target){
        // 找到第一个元素
        int left = searchLeft(nums, target);

        // 找到最后一个元素
        int right = searchRight(nums, target);

        // 比较两个元素是否存在特殊情况
        if(right - left < 0 || left == -1 || right == -1){
            return new int[]{-1,-1};
        }else {
            return new int[]{left, right};
        }
    }

    public static int searchLeft(int[] nums, int target){
        int left = 0; int right = nums.length - 1; int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] >= target){
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int searchRight(int[] nums, int target){
        int left = 0; int right = nums.length -1; int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] <= target){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
