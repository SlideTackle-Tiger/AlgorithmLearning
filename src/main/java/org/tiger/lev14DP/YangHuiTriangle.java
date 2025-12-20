package org.tiger.lev14DP;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName YangHuiTriangle
 * @Description 杨辉三角
 * @Author tiger
 * @Date 2025/12/9 07:27
 */
public class YangHuiTriangle {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(n+ "阶杨辉三角" + solution(n));
    }
    public static List<List<Integer>> solution(int n){
        // 我们注意到第n行的第i个元素等于第n-1行的i-1元素与i元素之和，
        List<List<Integer>> result = new ArrayList<>();
        for(int i =0;i < n;i++){
            List<Integer> row = new ArrayList<>();
            for(int j =0;j<=i;j++){
                if(j == 0 || j == i){
                    row.add(1);
                }else{
                    row.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
}
