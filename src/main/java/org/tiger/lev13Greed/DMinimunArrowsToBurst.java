package org.tiger.lev13Greed;

import java.util.Arrays;

/**
 * @ClassName DMinimunArrowsToBurst
 * @Description 用最少的箭引爆气球
 * @Author tiger
 * @Date 2025/12/14 15:46
 */
public class DMinimunArrowsToBurst {
    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println("最少需要：" + solve(points) + " 箭");
    }

    public static int solve(int[][] points){
        // 第一步，先对左边界排序 从小到大
        int n = points.length;
        if(n == 0) return 0;
        Arrays.sort(points, (a,b) ->{
            return Integer.compare(a[0], b[0]);
        });

        // 第二步 遍历结合逻辑判断
        int arrows = 1; // 最少需要1根箭
        int miniRight = points[0][1]; // 当前区间右边界
        for(int i = 1; i < n; i++){
            // 当前节点 左边界大于区间右边界时，需要添加一只箭
            if(points[i][0] > miniRight){
                arrows++;
                miniRight = points[i][1];
            }else {
                // 当前节点左边界不大于区间右边界，那么我们更新最小右边界。遍历下一个节点。
                miniRight = Math.min(miniRight, points[i][1]);
            }
        }
        return arrows;
    }
}
