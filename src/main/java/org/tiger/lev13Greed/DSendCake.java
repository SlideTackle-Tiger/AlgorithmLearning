package org.tiger.lev13Greed;

import java.util.Arrays;

/**
 * @ClassName DSendCake
 * @Description 代码随想录分发饼干
 * @Author tiger
 * @Date 2025/12/9 09:21
 */
public class DSendCake {
    public static void main(String[] args) {
        /*
        * 题目描述： 对于每个孩子i都有胃口g[i]，对于饼干i有尺寸s[i]
        * 如果s[j] >= g[i] 我们可以将这个饼干分配给孩子i 这个孩子会得到满足
        * 求尽可能满足更多的孩子
        * */
        int[] g ={1,2,7,10};
        int[] s = {1,3,5,9}; // 答案3
        System.out.println(solve(g,s));
    }

    public static int solve(int[] g, int[] s){
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int index = s.length - 1;
        for(int i = g.length -1; i>=0; i--){
            if (index >= 0 && s[index] >= g[i]){
                index --;
                result ++;
            }
        }
        return result;
    }
}
