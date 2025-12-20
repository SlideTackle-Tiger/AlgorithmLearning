package org.tiger.lev13Greed;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DPartitionLabels
 * @Description 划分字母区间
 * @Author tiger
 * @Date 2025/12/14 17:36
 */
public class DPartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println("字母区间可以划分为" + solve(s) );
    }

    public static List<Integer> solve(String s){
        int n = s.length();
        int[] hash = new int[26];
        for(int i =0;i < n ;i ++){
            hash[s.charAt(i) - 'a'] = i;
        }

        //第二步
        List<Integer> result = new ArrayList<>();
        int left = 0; int right = 0;
        for(int i =0;i<n;i++){
            right = Math.max(right, hash[s.charAt(i) - 'a']);
            if(i == right){
                result.add(right - left + 1);
                left = right + 1;
            }
        }

        return result;
    }

}
