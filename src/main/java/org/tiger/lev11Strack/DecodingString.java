package org.tiger.lev11Strack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @ClassName DecodingString
 * @Description 字符串解码
 * @Author tiger
 * @Date 2025/11/17 13:30
 */
public class DecodingString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s){
        Deque<Integer> countStack = new ArrayDeque<>(); // 存储数字
        Deque<String> stringStack = new ArrayDeque<>(); // 存储字符
        String currentString = ""; // 当前解码字符串
        int k = 0; // 当前倍数
        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch)){
                // 当前字符是数字
                k = k * 10 + (ch - '0'); //计算多位数
            }else if(ch == '['){
                // 当前字符是左括号，将当前字符串和数字压入各自的栈
                countStack.push(k);
                stringStack.push(currentString);
                // 重置
                currentString = "";
                k = 0;
            }else if(ch == ']'){
                // 等于右括号时解码
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                for(int i = 0; i < repeatTimes; i++){
                    temp.append(currentString);
                }
                currentString = temp.toString();
            }else {
                // 如果是字母则加入当前字符串
                currentString = currentString + ch;
            }
        }
        return currentString;
    }
}
