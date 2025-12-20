package org.tiger.lev11Strack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @ClassName demo
 * @Description
 * @Author tiger
 * @Date 2025/11/13 10:18
 */
// Deque 双端队列只用一头模拟栈，有两个实现类 ArrayDeque(基于数组，线程不安全，单机环境效率高) 和 LinkedList(基于链表，多线程安全，插入删除效率高)
// Deque + Map （辅助map 用于配对括号对 key 右括号，value 左括号）
// 遍历S对于每一个字符首先判断是否是map的key(右括号)
    // 右括号那么查看栈顶元素是否为匹配的左括号，不是则return false
    // 左括号则压入栈中
public class demo {
    public static void main(String[] args) {

    }

    public boolean isValid(String s){
        int n = s.length();
        if(n % 2 != 0){return false;}

        // 判断s是否为有效括号
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');

        Deque<Character> deque = new LinkedList<>();
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if(deque.isEmpty() || deque.peek() != map.get(c)){
                    return false;
                }
                deque.pop();
            }else {
                deque.push(c);
            }
        }
        return deque.isEmpty();

    }
}
