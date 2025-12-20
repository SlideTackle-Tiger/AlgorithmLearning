package org.tiger.lev11Strack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @ClassName VerifyParentheses
 * @Description 验证括号是否有效
 * @Author tiger
 * @Date 2025/11/13 11:03
 */
public class VerifyParentheses {
    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s){
        int n = s.length();
        if(n % 2 != 0){return false;}

        // Map存储括号对 ,右括号为key 左括号为value
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        // 栈存储左括号
        Deque<Character> stack = new LinkedList<>();
        for(int i =0;i < n; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                // 存在右括号
                if(stack.isEmpty() || stack.peek() != map.get(c)){
                    return false;
                }
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();

    }
}
