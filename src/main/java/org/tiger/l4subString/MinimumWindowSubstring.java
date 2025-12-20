package org.tiger.l4subString;

/**
 * @ClassName MinimumWindowSubstring
 * @Description 最小覆盖子串问题
 * @Author tiger
 * @Date 2025/11/1 01:26
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * */
public class MinimumWindowSubstring {
    public static Map<Character, Integer> ori = new HashMap<>(); // 目标子串中的所有字符的个数
    public static Map<Character, Integer> cnt = new HashMap<>(); // 当前滑动窗口所有字符的个数

    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
    }

    public static String minWindow(String str, String target){
        int tLen = target.length();
        // 初始化目标子串所有字符个数
        for(int i = 0; i<tLen;i++){
            char c = target.charAt(i);
            ori.put(c, ori.getOrDefault(c,0) + 1);
        }
        // 初始化窗口左右指针
        int winLeft = 0, winRight = -1;
        // 初始化最短子串长度,最短子串边界
        int miniWindow = Integer.MAX_VALUE;
        int miniWindowLeft = -1, miniWindowRight = -1;
        // 获取字符串s（待遍历数组长度）
        int sLen = str.length();

        // 窗口右扩张寻找可行解
        while(winRight < sLen){
            winRight++;

            if(winRight < sLen && ori.containsKey(str.charAt(winRight))){
                Character c = str.charAt(winRight); // 当前扩张到的字符
                // 加入到cntMap中， cnt记录当前窗口中元素的个数
                cnt.put(c, cnt.getOrDefault(c, 0)+ 1);
            }

            // 左指针收缩边界 ,要满足：1. cnt中元素个数大于等于ori中元素个数，窗口左指针小于等于右指针
            while (checkTowMapEquals() && winLeft <= winRight){
                // 更新最短子串
                if(winRight - winLeft + 1 < miniWindow){
                    miniWindow = winRight - winLeft + 1;
                    miniWindowLeft = winLeft;
                    miniWindowRight = winRight;
                }

                // 左指针右移，尝试缩小窗口
                if(ori.containsKey(str.charAt(winLeft))){
                    cnt.put(str.charAt(winLeft), cnt.getOrDefault(str.charAt(winLeft), 0) -1);
                }
                winLeft++;
            }

        }
        return miniWindowLeft == -1 ? "" : str.substring(miniWindowLeft, miniWindowRight + 1);
    }

    public static boolean checkTowMapEquals(){
        // 判断两个Map是否相等，这里使用了迭代器，map的迭代器提供了next()方法获取Map.Entry对象，hasNext()方法判断是否还有内容
        Iterator iter = ori.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            Character key = (Character) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if(cnt.getOrDefault(key, 0) < value){
                return false;
            }
        }
        return true;
    }
}
