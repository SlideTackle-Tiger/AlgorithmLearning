package org.tiger.lev13Greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName DMergeIntervals
 * @Description 合并区间
 * @Author tiger
 * @Date 2025/12/14 18:58
 */
public class DMergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("合并区间后：" + Arrays.deepToString(solve(intervals)));
    }

    public static int[][] solve(int[][] intervals){
        // 按照左区间从小到大排序，如果左区间相同则右区间较大的放在后面
        Arrays.sort(intervals, (a, b) ->{
            if(a[0] == b[0]){
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0] , b[0]);
        });

        // 合并区间
        List<int[]> result = new ArrayList<>();
        int left = intervals[0][0]; int right = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] <= right){
                // 合并区间
                right = Math.max(right, intervals[i][1]);
            }else {
                // 保存区间
                result.add(new int[]{left, right});
                // 更新left、right
                left = intervals[i][0]; right = intervals[i][1];
            }
        }
        // 最后一个区间需要单独保存
        result.add(new int[]{left, right});
        return result.toArray(new int[result.size()][]);
    }
}
