package org.tiger.lev12Heep;

import java.util.PriorityQueue;

/**
 * @ClassName MedianInDataStream
 * @Description 数据流中的中位数，既使用的数据结构实现快速获取数据中的中位数
 * @Author tiger
 * @Date 2025/12/8 09:21
 */
public class MedianInDataStream {
    public static void main(String[] args) {
        MedianDataStream medianDataStream = new MedianDataStream();
        medianDataStream.addNum(1);
        medianDataStream.addNum(2);
        System.out.println(medianDataStream.findMedian());
        medianDataStream.addNum(3);
        System.out.println(medianDataStream.findMedian());
    }

    static class MedianDataStream{
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianDataStream(){
            minHeap = new PriorityQueue<>((a, b) -> a - b);
            maxHeap = new PriorityQueue<>((a,b) -> b - a);
        }

        public void addNum(int num){
            if(minHeap.isEmpty() || num > minHeap.peek()){
                minHeap.offer(num);
            }else {
                maxHeap.offer(num);
            }

            // 维护
            if(minHeap.size() > maxHeap.size() + 1){
                maxHeap.offer(minHeap.poll());
            }else if(maxHeap.size() > minHeap.size()){
                minHeap.offer(maxHeap.poll());
            }
        }

        public double findMedian(){
            if(minHeap.size() == maxHeap.size()){
                return (minHeap.peek() + maxHeap.peek()) / 2.0;
            }else {
                return minHeap.peek();
            }
        }
    }
}
