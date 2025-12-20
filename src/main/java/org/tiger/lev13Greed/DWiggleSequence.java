package org.tiger.lev13Greed;

/**
 * @ClassName DWiggleSequence
 * @Description 摆动序列
 * @Author tiger
 * @Date 2025/12/9 09:46
 */
public class DWiggleSequence {
    public static void main(String[] args) {
        int[] nums = {1,17,5,10,13,15,10,5,16,8};
        System.out.println("最长摆动子序列为：" + solve(nums));
    }
    public static int solve(int[] nums){
        if(nums.length <=1){
            return nums.length;
        }
        int prediff = 0, currdiff = 0, result = 1;
        // 注意这个循环从1开始，计算nums[0]的prediff和currdiff
        for(int i = 1; i < nums.length; i++){
            currdiff = nums[i] - nums[i -1];
            if( (prediff >= 0 && currdiff < 0) ||  (prediff <=0 && currdiff >0)){
                result ++;
                prediff = currdiff;
            }
        }
        return result;
    }
}
