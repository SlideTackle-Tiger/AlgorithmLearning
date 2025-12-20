package org.tiger.lev14DP;

/**
 * @ClassName ClimbingStairs
 * @Description 爬楼梯 问题
 * @Author tiger
 * @Date 2025/12/8 15:10
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 10;
        System.out.println("爬楼梯问题，n=" + n + "的解法：" + climbStairs(n));
    }

    public static int climbStairs(int n){
        // 分析问题得到递归方程 f(x) = f(x-1) + f(x-2)
        // 那么有 f(0) + f(1) = f(2), f(2) + f(1) = f(3)
        // 分析边界可知 f(0) = 0, f(1) = 1.所以可以使用滚动数组的方法 f(p) + f(q) = f(r) 循环直到 fn
        int fP = 0,fQ=0,fR= 1;
        for(int i = 0; i < n; i++){
            fP = fQ;
            fQ = fR;
            fR = fP + fQ;
        }
        return fR;
    }
}
