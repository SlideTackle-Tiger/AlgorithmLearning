package org.tiger.lev13Greed;

/**
 * @ClassName JumpGameTow
 * @Description 跳跃游戏二
 * @Author tiger
 * @Date 2025/12/8 11:22
 */
public class JumpGameTow {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(jumpSteps(nums));
        System.out.println("算法二：维护最远距离、end窗口" + jumpSteps2(nums));
    }

    // 解法一：最终到达的位置固定，然后从数组头开始遍历每一步能到的位置，如果能够到达最终位置，那么最终位置就更新到当前位置下标
    public static int jumpSteps(int[] nums){
        int n = nums.length;
        int position = n - 1;
        int steps = 0;
        while(position > 0){
            for(int i =0;i < position; i++){
                if(i + nums[i] >= position){
                    position = i;
                    steps++;
                    break; // 我们只要最早能够到达的位置
                }
            }
        }
        return steps;
    }

    // 算法二， 一次遍历，维护一个能够到达最远距离的变量，当当前位置等于最远距离时，更新最远距离，并增加步数。
    public static int jumpSteps2(int[] nums){
        int n = nums.length - 1;
        int steps = 0;
        int maxPosition = 0;
        int end = 0;
        for(int i =0;i<n;i++){
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(i == end){
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
