package org.tiger.lev10binarysearch;

/**
 * @ClassName TowArraysMedian
 * @Description 寻找两个有序数组的中位数，控制复杂度为log n
 * @Author tiger
 * @Date 2025/11/11 11:08
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        // 示例输入：两个有序数组
        int[] sortedArray1 = {1, 3};
        int[] sortedArray2 = {2};
        // 计算并打印两个有序数组的中位数
        System.out.println(findMedianOfTwoSortedArrays(sortedArray1, sortedArray2));
    }

    /**
     * 寻找两个非空有序数组的中位数（时间复杂度O(log(min(m,n)))，空间复杂度O(1)）
     * 核心算法：通过二分查找在较短数组中确定分割线，使左右两侧元素满足中位数定义
     * 中位数定义：将合并后的数组分为两部分，左半部分元素个数≥右半部分（奇数时多1个），且左半最大≤右半最小
     *
     * @param nums1 第一个有序数组
     * @param nums2 第二个有序数组
     * @return 两个数组的中位数（double类型，适配奇数/偶数长度合并数组）
     */
    public static double findMedianOfTwoSortedArrays(int[] nums1, int[] nums2) {
        // 确保nums1是较短数组，减少二分查找的迭代次数（核心优化：log(min(m,n))）
        if (nums1.length > nums2.length) {
            int[] tempArray = nums1;
            nums1 = nums2;
            nums2 = tempArray;
        }

        int len1 = nums1.length; // 较短数组的长度
        int len2 = nums2.length; // 较长数组的长度

        // 合并后左半部分的总元素个数：奇数时左半多1个，保证中位数在左半最大值（奇数）或左右均值（偶数）
        int totalLeftCount = (len1 + len2 + 1) / 2;

        // 二分查找的边界：在nums1中寻找分割线i（i表示nums1左半部分的元素个数）
        int leftPointer = 0;
        int rightPointer = len1;

        while (leftPointer < rightPointer) {
            // 计算nums1的分割线i（+1是为了避免死循环，确保指针能收敛）
            int split1 = ((rightPointer - leftPointer + 1) >> 1) + leftPointer;
            // 计算nums2的分割线j（j = 左半总元素数 - nums1左半元素数）
            int split2 = totalLeftCount - split1;

            // 冲突判断：nums1左半最大值 > nums2右半最小值，说明split1偏大，需向左调整
            if (nums1[split1 - 1] > nums2[split2]) {
                rightPointer = split1 - 1;
            } else {
                // split1偏小或合适，向右调整（包含当前split1，避免遗漏）
                leftPointer = split1;
            }
        }

        // 二分查找结束，得到最终分割线
        int split1 = leftPointer;
        int split2 = totalLeftCount - split1;

        // 计算nums1左半最大值（i=0时左半为空，用最小整数兜底）
        int nums1LeftMax = split1 == 0 ? Integer.MIN_VALUE : nums1[split1 - 1];
        // 计算nums1右半最小值（i=len1时右半为空，用最大整数兜底）
        int nums1RightMin = split1 == len1 ? Integer.MAX_VALUE : nums1[split1];
        // 计算nums2左半最大值（j=0时左半为空，用最小整数兜底）
        int nums2LeftMax = split2 == 0 ? Integer.MIN_VALUE : nums2[split2 - 1];
        // 计算nums2右半最小值（j=len2时右半为空，用最大整数兜底）
        int nums2RightMin = split2 == len2 ? Integer.MAX_VALUE : nums2[split2]; // 修正原代码bug：j的边界是len2而非len1

        // 合并数组长度为奇数：中位数是左半部分的最大值
        if ((len1 + len2) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        }
        // 合并数组长度为偶数：中位数是左半最大值与右半最小值的平均值
        else {
            return (double) (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }
    }
}
