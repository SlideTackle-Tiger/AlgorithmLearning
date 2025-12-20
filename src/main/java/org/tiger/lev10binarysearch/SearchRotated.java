package org.tiger.lev10binarysearch;

/**
 * @ClassName SearchRotated
 * @Description 搜索旋转排序数组 注意控制时间复杂度为 log n
 * @Author tiger
 * @Date 2025/11/10 18:54
 */

/*
* 整数数组 nums 按升序排列，数组中的值 互不相同 。在传递给函数之前，nums 在下标k上进行了 向左旋转，
* 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
*
* 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
* */
/*
* 输入：nums = [4,5,6,7,0,1,2], target = 0
  输出：4

* */
// 核心，mid分开的左右数组 其中一个必有序。只需要在有序的几何中判断元素是否存在即可
public class SearchRotated {
    public static void main(String[] args) {
        // 示例输入：旋转后的有序数组和目标值
        int[] rotatedArray = {4, 5, 6, 7, 0, 1, 2};
        int targetValue = 0;
        // 搜索目标值并打印索引（未找到返回-1）
        System.out.println(searchInRotatedArray(rotatedArray, targetValue));
    }

    /**
     * 在旋转后的有序数组中搜索目标值，返回其索引（数组元素不重复）
     * 旋转有序数组：由升序数组旋转得到，如[0,1,2,4,5,6,7]旋转后为[4,5,6,7,0,1,2]
     * 核心算法：二分查找，通过判断mid所在的有序区间，缩小搜索范围
     *
     * @param rotatedArray 旋转后的升序数组（元素无重复）
     * @param target       待搜索的目标值
     * @return 目标值在数组中的索引，未找到则返回-1
     */
    public static int searchInRotatedArray(int[] rotatedArray, int target) {
        int arrayLength = rotatedArray.length;

        // 处理空数组的特殊情况
        if (arrayLength == 0) {
            return -1;
        }
        // 处理数组只有一个元素的情况：直接判断是否等于目标值
        if (arrayLength == 1) {
            return rotatedArray[0] == target ? 0 : -1;
        }

        int leftPointer = 0; // 左指针（初始指向数组起始位置）
        int rightPointer = arrayLength - 1; // 右指针（初始指向数组末尾位置）

        // 二分查找核心循环：左指针<=右指针时持续搜索
        while (leftPointer <= rightPointer) {
            // 计算中间索引（避免(left+right)溢出，等价于(left+right)/2）
            int midIndex = ((rightPointer - leftPointer) >> 1) + leftPointer;

            // 终止条件1：中间元素正好是目标值，直接返回索引
            if (rotatedArray[midIndex] == target) {
                return midIndex;
            }

            // 情况1：mid所在左半部分（0~mid）是有序区间（nums[0] <= nums[mid]）
            if (rotatedArray[0] <= rotatedArray[midIndex]) {
                // 目标值在左半有序区间内：缩小右指针到mid-1
                if (rotatedArray[0] <= target && target < rotatedArray[midIndex]) {
                    rightPointer = midIndex - 1;
                } else {
                    // 目标值不在左半有序区间：缩小左指针到mid+1
                    leftPointer = midIndex + 1;
                }
            }
            // 情况2：mid所在右半部分（mid~end）是有序区间（nums[mid] < nums[0]）
            else {
                // 目标值在右半有序区间内：缩小左指针到mid+1
                if (rotatedArray[midIndex] < target && target <= rotatedArray[arrayLength - 1]) {
                    leftPointer = midIndex + 1;
                } else {
                    // 目标值不在右半有序区间：缩小右指针到mid-1
                    rightPointer = midIndex - 1;
                }
            }
        }

        // 循环结束未找到目标值，返回-1
        return -1;
    }

}
