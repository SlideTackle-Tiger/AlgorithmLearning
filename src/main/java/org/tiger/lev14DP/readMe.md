# 动态规划
## 理论基础
动态规划包括哪种问题？
- 基础问题，斐波那契额数列、爬楼梯。
- 背包问题
- 打家劫舍
- 股票问题
- 子序列问题  
  
动态规划类题目解题思路
- DP数组定义以及下标的含义
- 递推公式
- dp数组如何初始化
- 遍历顺序
- 打印dp数组，分析上述四点是否与代码相同。

## 算法题目
### 1. 斐波那契数列
力扣地址： https://leetcode.cn/problems/fibonacci-number/description/  
项目代码： DFibonacci.class  
题目简短描述： 斐波那契数列 f(n) = f(n - 1) + f(n - 2);  1 1 2 3 5。 求f[n]  
核心代码：
``` java.
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
        
        /** 状态压缩*/
        int dp0 = 0;int dp1 = 1; int sum = dp0 + dp1;
        for(int i = 2; i <= n ; i++){
            sum = dp0 + dp1;
            dp0 = dp1;
            dp1 = sum;
        }
        return sum;
        
        /** 递归*/
        if(n < 2){return n;}
        return solveByRecursion(n - 1) + solveByRecursion(n - 2);
```
算法思路：
- 确定DP数组含义： dp[i]表示第i个斐波那契数
- 确定递推公式： 题目中已经给出 dp[i] = dp[i - 1] + dp[i - 2];
- dp数组如何初始化： 题目中已经给出 dp[0] = 1, dp[1] = 1;
- 遍历顺序： 从前向后遍历才能保证dp[i]可以由dp[i - 1] 和dp[i - 2]计算得来。
- 打印dp数组： 用来debug，快速定位问题。     

其他解法：
- 上述提供了动态规划解法，这里提供两种其他解法。
- 状态压缩
  - 观察代码我们可以看到，题目中只需要求出dp[n]即可，我们可以不必定义一个dp数组，而是用滚动数组的方式来表示 f[n]、f[n - 1]、f[n - 2];
- 递归
  - 本题使用递归会导致严重的时间复杂度2的n次方，不建议使用。
  - 递归终止条件，当n小于2时 n= 0或1此时直接返回初始值 0 ， 1。
  - 递归压栈：每一次压入n- 1 与 n - 2,递归计算二者之和。

具体实现：
- 实现。

### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现
### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现
### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现
### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现
### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现
### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：  
具体实现：
- 实现