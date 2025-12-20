package org.tiger.lev12Heep;

import java.util.*;

/**
 * @ClassName TopKFrequentElement
 * @Description 前k个高频元素
 * @Author tiger
 * @Date 2025/12/3 10:50
 */
public class TopKFrequentElement {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution(nums, k);
        System.out.println(Arrays.toString(result));
    }

    // 题目转换 Map存放 value + 次数。 smallHeap存放前k个高频
    /**
     * 需要注意的是，我们需要前k个频率元素就要控制，堆的数量不能超过k，否则堆插入，弹出会产生额外开销
     * 堆存储的数据是 key + value 用value的数量排序 需要重写
     * */
    public static int[] solution(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> bigHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int num = entry.getKey(); int count = entry.getValue();
            if(bigHeap.size() == k){
                if(bigHeap.peek()[1] < count){
                    bigHeap.poll();
                    bigHeap.offer(new int[]{num, count});
                }
            }else {
                bigHeap.offer(new int[]{num, count});
            }
        }

        int[] result = new int[k];
        for(int i = 0; i < k ; i ++){
            result[i] = bigHeap.poll()[0];
        }
        return result;
    }
}
