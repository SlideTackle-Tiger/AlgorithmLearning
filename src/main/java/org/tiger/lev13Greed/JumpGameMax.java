package org.tiger.lev13Greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName JumpGameMax
 * @Description 划分字母区间, 一个字母至多只能存在于一个区间，尽可能多的将字符串s分割，返回每个字符段落的字母个数
 * @Author tiger
 * @Date 2025/12/8 14:47
 */
public class JumpGameMax {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(jumpGameMax(s));
    }

    public static List<Integer> jumpGameMax(String s){
        int n = s.length();
        // 存储s中每个字母的最远结束位置
        int[] lastCharPosition = new int[26];
        for(int i =0;i<n;i++){
            lastCharPosition[s.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0; int end = 0;
        for(int i =0;i<n;i++){
            end = Math.max(end, lastCharPosition[s.charAt(i) - 'a']);
            if(i == end){
                res.add(end - start + 1);
                start = end + 1;
            }
        }
        return res;
    }
}
