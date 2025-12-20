package org.tiger.lev10binarysearch;

import java.util.Arrays;

/**
 * @ClassName SearchFirshtAndLast
 * @Description 在排序数组中查找元素的第一个和最后一个位置 控制时间复杂度
 * @Author tiger
 * @Date 2025/11/10 16:28
 */
/*
* 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
* */

/*
* 输入：nums = [5,7,7,8,8,10], target = 8
  输出：[3,4]

* */
public class SearchFirshtAndLast {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public static int[] searchRange(int[] nums, int target){
        int leftIndex = binarySearchFirst(nums, target);
        int rightIndex = binarySearchLast(nums, target);
        System.out.println(leftIndex + " " + rightIndex);
        if(leftIndex <= rightIndex && rightIndex != -1 && leftIndex != -1){
            return  new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1,-1};
    }

    public static int binarySearchLast(int[] nums, int target){
        int left = 0; int right = nums.length -1;
        int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            // 当前值小于等于目标值时，会继续向右寻找到最后一个
            if(nums[mid] <= target){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return ans;
    }

    public static int binarySearchFirst(int[] nums, int target){
        int left = 0; int n = nums.length; int right = n -1;
        int ans = -1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            // 当前值大于目标值时，会继续向左寻找第一个大于等于目标值的位置
            if(nums[mid] >= target){
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
