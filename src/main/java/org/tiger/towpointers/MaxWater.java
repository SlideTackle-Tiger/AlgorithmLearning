package org.tiger.towpointers;

/**
 * @ClassName MaxWater
 * @Description 盛水最多的容器
 * @Author tiger
 * @Date 2025/10/30 10:12
 */

/**
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水的最大值为 49。
 * 解法：双指针思想左右指针，计算当前面积，更新最大面积，移动左右指针最小的那个。
 * */
public class MaxWater {
    public static void main(String[] args) {
        int[] nums = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxWater(nums));
    }

    public static int maxWater(int[] nums){
        int n = nums.length;
        int left = 0, right = n - 1;
        int maxArea = 0;
        while(left != right){
            int currentArea = (right - left) * Math.min(nums[left],nums[right]);
            maxArea = Math.max(maxArea,currentArea);
            // 移动指针
            if(nums[left] < nums[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxArea;

    }

}
