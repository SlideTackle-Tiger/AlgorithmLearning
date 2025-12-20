package org.tiger.l4subString;

/**
 * @ClassName MaxWindow
 * @Description 滑动窗口的最大值
 * @Author tiger
 * @Date 2025/10/31 22:47
 */

import java.util.*;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *返回 滑动窗口中的最大值 。
 * 示例：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * */
public class MaxWindow {
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(solveWithPriorityQueue(nums, k));

    }

    // 滑动窗口思想解决问题 --暴力解法 滑动整个数组
    public static List<Integer> solve(int[] nums, int k) {
        int n = nums.length;
        if (n < k) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();

        // 初始化第一个窗口的最大值（从窗口内首个元素开始比较）
        int maxValue = nums[0];
        for (int i = 0; i < k; i++) {
            maxValue = Math.max(maxValue, nums[i]);
        }
        res.add(maxValue);

        // 滑动窗口：每次移动后，重新计算当前窗口的最大值
        for (int i = k; i < n; i++) {
            // 当前窗口范围：[i - k + 1, i]
            int currentMax = nums[i - k + 1]; // 从窗口左边界开始
            for (int j = i - k + 1; j <= i; j++) {
                currentMax = Math.max(currentMax, nums[j]);
            }
            res.add(currentMax);
        }

        return res;
    }

    // 优化解法 -- 大顶堆快速定位最大值
    public static List<Integer> solveWithPriorityQueue(int[] nums, int k){
        int n = nums.length;
        if(n < k){return new ArrayList<>();}
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        for(int i =0;i<k;i++){
            queue.offer(new int[]{nums[i],i});
        }
        List<Integer> res = new ArrayList<>();
        res.add(queue.peek()[0]);

        // 窗口开始滑动
        for(int i = k; i< n;i++){
            queue.offer(new int[]{nums[i], i});
            // 窗口左边界移除窗口,如果当前堆定元素的索引小于 i-k（滑动窗口最左边界）说明最大值不在窗口内，从大顶堆移除这个元素
            while(queue.peek()[1] <= i - k){
                queue.poll();
            }
            res.add(queue.peek()[0]);
        }
        return res;
    }


}
