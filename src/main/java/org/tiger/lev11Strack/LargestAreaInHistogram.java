package org.tiger.lev11Strack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

/**
 * @ClassName LargestAreaInHistogram
 * @Description 84. 柱状图中最大矩形面积
 * @Author tiger
 * @Date 2025/12/3 08:28
 */
public class LargestAreaInHistogram {
    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = violentSolution(heights);
        int maxArea2 = solution(heights);
        System.out.println("暴力解法：" +  maxArea);
        System.out.println("单调栈优化：" + maxArea2);
    }

    // 暴力解法
    /**
     * 面积 = 宽度 * 高度
     * 宽度 = 左右低于当前高度的差值 -1 ； 不好固定
     * 高度 = 当前高度； 好固定
     * 对于高度i，向左右两边扩散，遇到比i小的柱形停下，左右两边差值就是宽度
     * */
    public static int violentSolution(int[] heights){
        int maxArea = 0;
        int len = heights.length;
        for(int i = 0; i < len; i++){
            int height = heights[i];
            int left = i, right = i;
            while(left >= 0 && heights[left] >= height){
                left --;
            }

            while(right < len && heights[right] >= height){
                right ++;
            }

            maxArea = Math.max(maxArea, (right - left - 1) * height);
        }
        return maxArea;
    }

    // 单调栈优化
    /**
     * 优化暴力解法，每次遍历保留之前的数据
     *
     * */
    public static int solution(int[] heights){
        // 获取数组长度，判断特殊情况
        int len = heights.length;
        if(len == 0){
            return 0;
        }else if(len == 1){
            return heights[0];
        }

        int area = 0; // 最大面积
        Deque<Integer> stack = new ArrayDeque<>(); // 单调栈存储当前暂时无法处理柱形的索引

        // 遍历数组，注意每次计算的面积实际为栈顶柱形的最大面积
        for(int i = 0; i < len; i++){
            // 如果当前栈不为空，且栈顶元素严格大于当前元素，说明栈顶元素可以被处理了，处理栈顶元素
            while(!stack.isEmpty() && heights[stack.peekLast()] > heights[i]){
                int height = heights[stack.removeLast()]; // 高度为栈顶元素在高度数组中的数据

                int width;
                // 如果栈此时为空，说明左侧没有比栈顶元素低的柱形，宽度为i
                if(stack.isEmpty()){
                    width = i;
                }else {
                    // 栈不为空，说明左侧有比栈顶元素低的柱形， 宽度为当前索引i与栈顶元素索引的差值 -1；
                    width = i - stack.peekLast() - 1;
                }

                area = Math.max(area, width * height);
            }

            // 不满足while条件，说明当前高度还不能被处理，入栈
            stack.add(i);
        }

        // 循环结束后，栈中如果还有元素，那么继续处理栈中元素
        while(!stack.isEmpty()){
            int height = heights[stack.removeLast()];

            int width;
            if(stack.isEmpty()){
                width = len;
            }else {
                width = len - stack.peekLast() - 1;
            }
            area = Math.max(area, width * height);
        }

        return area;
    }

}
