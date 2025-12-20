package org.tiger.lev10binarysearch;

/**
 * @ClassName SearchRotatedMini
 * @Description 搜索旋转后数组中最小值
 * @Author tiger
 * @Date 2025/11/11 10:16
 */
// 方法类似旋转数组搜索，只需要每次收缩有序数组中最小的边界即可
// 核心思想，while 左右不重合， mid和右侧比 小于右等于mid 大于左等于mid+1； 返回左侧指针指向元素
public class SearchRotatedMini {
    public static void main(String[] args) {
        int[] nums = {3,4,5,1,2};
        System.out.println(search(nums));
    }

    public static  int search(int[] nums){
        int n = nums.length;
        if(n == 0 || n == 1)return n == 0? -1: nums[0];

        int left = 0;
        int right = n -1;
        while(left < right){
            int mid = ((right - left) >> 1) + left;// mid
            if(nums[mid] < nums[right]){
                // mid 小于 右
                right = mid;
            }else {
                // mid 大于右
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
