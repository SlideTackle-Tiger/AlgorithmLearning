package org.tiger.lev12Heep;

import java.util.PriorityQueue;

/**
 * @ClassName KthLargestElement
 * @Description 数组中第K个最大元素
 * @Author tiger
 * @Date 2025/12/3 10:21
 */
public class KthLargestElement {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int element1 = solution(arr, k);
        System.out.println("大顶堆：" + element1);
    }

    // 设计维护数组最大最小值，可以考虑堆
    /**
     * 适用于不固定数组的情况，复杂度为nlogk
     * */
    public static int solution(int[] arr, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
        for(int n : arr){
            heap.add(n);
            if(heap.size() > k){
                heap.poll();
            }
        }
        return heap.poll();
    }
}

