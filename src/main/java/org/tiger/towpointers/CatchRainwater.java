package org.tiger.towpointers;

/**
 * @ClassName CatchRainwater
 * @Description 接雨水
 * @Author tiger
 * @Date 2025/10/31 15:40
 */

/**
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * */

/**
 * 动态规划问题：全部的雨水转换为某一点能接的雨水
 * 某一点的雨水 = min(左边最高点，右边最高点) - 当前点的高度
 * 构造左右最高点数组是要注意，对于点ai，左侧最高数组中ai位置存放的是原数组a0-ai中最大的高度
 * */
public class CatchRainwater {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solve(height));
    }

    public static int solve(int[] height){
        int n = height.length;
        if(n == 0)return 0;
        // 左侧高度数组 如 2，1，0. 0点左侧最高高度为2 而不是1
        int[] leftHeight = new int[n];
        leftHeight[0] = height[0];
        for(int i = 1; i< n;i++){
            leftHeight[i] = Math.max(leftHeight[i -1], height[i]);
        }

        // 右侧最高数组
        int[] rightHeight = new int[n];
        rightHeight[n -1] = height[n -1];
        for(int i = n - 2 ; i>=0;i--){
            rightHeight[i] = Math.max(height[i], rightHeight[i + 1]);
        }

        // 计算总的接雨水数量
        int totalRainwater = 0;
        for(int i = 0; i< n;i++){
            int currentRainwater = Math.min(leftHeight[i], rightHeight[i]) - height[i];
            if(currentRainwater > 0){totalRainwater = totalRainwater + currentRainwater;}
        }

        return totalRainwater;



    }

}
