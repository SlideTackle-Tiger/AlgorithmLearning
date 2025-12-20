package org.tiger.lev13Greed;

/**
 * @ClassName JumpGame
 * @Description 跳跃游戏
 * @Author tiger
 * @Date 2025/12/8 10:06
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums){
        int n = nums.length;
        int marRemoveStemp = 0;
        for(int i =0; i<n;i++){
            if( i <= marRemoveStemp){
                marRemoveStemp = Math.max(marRemoveStemp, i + nums[i]);
                if(marRemoveStemp >= n - 1){
                    return true;
                }
            }
        }
        return false;
    }
}
