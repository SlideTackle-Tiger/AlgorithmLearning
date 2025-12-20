package org.tiger.slidingwindow;

/**
 * @ClassName LongestSubstring
 * @Description 无重复字符串的最长子串
 * @Author tiger
 * @Date 2025/10/31 16:32
 */

import java.util.HashSet;

/**
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 * */
public class LongestSubstring {
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(longestSubstring(s));
    }

    public static int longestSubstring(String s){
        HashSet<Character> set = new HashSet<>(); // set用于判断是否有重复字符
        int rightPointer = 0; // 窗口右指针
        int maxAns = 0; // 最大窗口长度
        for(int leftPointer = 0; leftPointer < s.length(); leftPointer++){
            // 移除滑动窗口最左边的字符
            if( leftPointer > 0){
                set.remove(s.charAt(leftPointer - 1));
            }

            // 不断向右更新滑动窗口大小
            while(rightPointer < s.length() -1 && !set.contains(s.charAt(rightPointer ))){
                set.add(s.charAt(rightPointer ));
                rightPointer++;
            }
            // rightPointer 从 -1 开始表示窗口为左闭右开，所以这里长度就是 right - left
            maxAns = Math.max(maxAns, rightPointer - leftPointer );
        }
        return maxAns;
    }
}
