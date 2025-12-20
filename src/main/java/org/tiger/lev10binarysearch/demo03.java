package org.tiger.lev10binarysearch;

/**
 * @ClassName demo03
 * @Description
 * @Author tiger
 * @Date 2025/11/12 16:12
 */
public class demo03 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target){
        int left = 0; int right = nums.length - 1;
        while(left <= right){
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target){return mid;}
            // mid 切分两侧一定过有一个有序
            if(nums[0] <= nums[mid]){
                // 左侧有序
                if(nums[mid] > target && nums[0] < target){
                    // 舍去右侧
                    right = mid - 1;
                }else {
                    // 舍去左侧
                    left = mid + 1;
                }
            }else{
                // 右侧有序
                if(nums[mid] < target && nums[nums.length -1] > target){
                    // 舍去左侧数组
                    left = mid  + 1;
                }else {
                    right = mid -1;
                }

            }
        }
        return -1;
    }
}
