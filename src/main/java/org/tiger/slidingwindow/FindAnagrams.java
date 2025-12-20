package org.tiger.slidingwindow;

/**
 * @ClassName FindAnagrams
 * @Description 找到字符串中所有字母异位词的索引
 * @Author tiger
 * @Date 2025/10/31 20:54
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * */
public class FindAnagrams {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s,p));

    }

    public static List<Integer> findAnagrams(String s, String p){
        int sLen = s.length(), pLen = p.length(); // 初始化字符串s，子串p的长度
        if( sLen < pLen){ return new ArrayList<>();} // 如果s长度小于p长度，则没有子串可以组成p，返回空列表

        List<Integer> res = new ArrayList<>(); // 存储子串开始索引列表

        // 首先要构造滑动窗口
        int[] sWindow = new int[26]; // 存储对于s的滑动窗口，如“aba” 那么sWindow = [2,1,0....]
        int[] pGroundTruth = new int[26]; // 存储目标子串，如果swindow与pGroundTruth相同，则说明找到了子串
        // 首次初始化
        for(int i = 0; i< pLen; i++){
            char sChar = s.charAt(i);
            char pChar = p.charAt(i);
            sWindow[sChar - 'a']++;
            pGroundTruth[pChar - 'a']++;
        }

        // 首次判断
        if(Arrays.equals(sWindow,pGroundTruth)){
            res.add(0);
        }

        // 开始滑动窗口,注意窗口左侧从0开始，右侧能达到的最大索引为sLen - pLen
        // 如果当前窗口与groundTruth相同，那么i+1为子串索引的位置（因为i从0开始，实际子串位置为i+1）
        for(int i = 0; i < sLen - pLen; i++){
            // 从左侧缩短窗口
            sWindow[s.charAt(i) - 'a'] --;
            // 右侧扩大一个窗口
            sWindow[s.charAt(i + pLen) - 'a']++;
            // 判断是否找到子串
            if(Arrays.equals(sWindow,pGroundTruth)){
                res.add(i + 1);
            }
        }
        return res;
    }
}
