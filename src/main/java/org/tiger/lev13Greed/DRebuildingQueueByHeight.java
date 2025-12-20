package org.tiger.lev13Greed;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName DRebuildingQueueByHeight
 * @Description 根据身高重建队列
 * @Author tiger
 * @Date 2025/12/12 16:18
 */
public class DRebuildingQueueByHeight {
    public static void main(String[] args) {
        // people = {h,k}, h表示身高，k表示有k个大于等于 i身高的人在前面
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        System.out.println("根据身高重建后队列：" + Arrays.stream(solve(people)).toArray().toString());
    }

    public static int[][] solve(int[][] people){
        // 先对身高按照从大到小的顺序排列，对于相同身高，我们将高于自身次数多的元素放在后面。
        Arrays.sort(people, (a, b) ->{
            // 身高相同的情况, 从小到大排序
            if(a[0] == b[0]){return a[1] - b[1];}
            return b[0] - a[0];
        });

        // 用链表存储，因为我们要不断的将 k较小的元素前移
        LinkedList<int[]> que = new LinkedList<>();

        for(int[] p : people){
            que.add(p[1],p);
        }
        return que.toArray(new int[people.length][]);
    }
}
