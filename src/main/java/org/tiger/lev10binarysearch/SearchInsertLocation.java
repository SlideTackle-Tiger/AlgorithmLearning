package org.tiger.lev10binarysearch;

/**
 * @ClassName SearchInsertLocation
 * @Description 查找插入位置
 * @Author tiger
 * @Date 2025/11/10 14:31
 */

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * */

/**
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * */

//题目有序的数组且控制时间复杂度为O（logN）就要想到二分查找法
//题目转换为二分查找数组中第一个大于target元素的位置pos，pos-1即为题目所求
public class SearchInsertLocation {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 5;
        System.out.println(search(nums, target));
    }
    // 二分查找
    public static int search(int[] nums, int target){
        int n = nums.length;
        int left = 0;
        int right = n -1;
        int ans = n;

        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if(target <= nums[mid]){
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return ans;
    }

}
