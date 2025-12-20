package org.tiger.lev11Strack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @ClassName MonotonicStack
 * @Description 单调栈解决每日温度问题
 * @Author tiger
 * @Date 2025/11/17 15:59
 */
public class MonotonicStack {
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(solve(temperatures)));
    }

    public static int[] solve(int[] temperatures){
        Deque<Integer> stack = new ArrayDeque<>(); // 栈，用于存储未找到大于当前温度的日期下标
        int[] res = new int[temperatures.length]; // 结果数组，如果没有数据默认会为0

        // 遍历天气数组
        for(int i = 0; i < temperatures.length; i++){
            // 如果当前栈为空或栈顶元素大于当前元素则直接将当前日期压入栈
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            // 其他情况 栈为空 或 当前温度小于栈顶温度，我们需要将当前温度压入栈。
            stack.push(i);
        }
        return res;
    }
}
