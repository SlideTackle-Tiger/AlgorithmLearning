package org.tiger.lev13Greed;

import java.util.Arrays;

/**
 * @ClassName DNoOverlapping
 * @Description 无重叠区间
 * @Author tiger
 * @Date 2025/12/14 16:20
 */
public class DNoOverlapping {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println("删除" + sovle(intervals) + "个区间后，区间无重叠");
    }

    public static int sovle(int[][] intervals){
        Arrays.sort(intervals,(a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        int n = intervals.length;
        if(n == 0)return 0;
        int count = 0;
        int miniRight = intervals[0][1];
        for(int i = 1; i < n;i++){
            if(intervals[i][0] > miniRight){
                count++;
                miniRight = intervals[i][1];
            }else {
                miniRight = Math.min(miniRight, intervals[i][1]);
            }
        }
        return count;
    }
}
