package org.tiger.lev11Strack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName MiniStrack
 * @Description 最小栈
 * @Author tiger
 * @Date 2025/11/13 11:19
 */
public class MiniStack {
    public static void main(String[] args) {
        MyMiniStack myMiniStrack = new MyMiniStack();
        myMiniStrack.push(5);
        myMiniStrack.push(3);
        myMiniStrack.push(2);
        myMiniStrack.push(1);
        myMiniStrack.push(4);
        myMiniStrack.push(6);
        System.out.println(myMiniStrack.top()); // 6
        System.out.println(myMiniStrack.getMin()); // 1

        myMiniStrack.pop();
        System.out.println(myMiniStrack.top()); // 4
        System.out.println(myMiniStrack.getMin()); // 1

        myMiniStrack.pop();
        System.out.println(myMiniStrack.top()); // 1
        System.out.println(myMiniStrack.getMin()); // 1
        myMiniStrack.pop();

        System.out.println(myMiniStrack.top()); // 2
        System.out.println(myMiniStrack.getMin()); // 2
    }

    public static class MyMiniStack{
        Deque<Integer> stack; // 存储当前元素
        Deque<Integer> minStack; // 存储当前最小元素

        public MyMiniStack(){
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val){
            stack.push(val);
            // 比较最小元素
            if(minStack.isEmpty() || val < minStack.peek()){
                minStack.push(val);
            }
        }

        public void pop(){
            Integer stackPeek = stack.pop();
            if(stackPeek.equals(minStack.peek())){
                minStack.pop();
            }
        }

        public int top(){
            return stack.peek();
        }

        public int getMin(){
            return minStack.peek();
        }
    }
}
