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

### 2. 爬楼梯
力扣地址： https://leetcode.cn/problems/climbing-stairs/description/  
项目代码： DClimbingStairs.class   
题目描述：上台阶问题，一次可以上1阶或2阶，求n个台阶一共有多少种方案。  
简要分析：
- 1 阶台阶 一种方案 1
- 2 阶台阶 两种方案 11 2
- 3 阶台阶 可以从1阶台阶迈两步 或 从2阶台阶迈一步得来 共有 (1阶台阶方案 + 2阶台阶方案) 种方案
- 4 阶台阶 (3阶台阶方案 + 2阶台阶方案) 种方案。
- 至此我们不难分析出递推公式：
  - n 阶台阶 f(n) = f(n - 1) + f(n - 2) n > 2  

核心代码：
``` java.
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n ; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
```
算法思路：
- 确定DP数组以及下标的含义：dp[i] 表示达到第i阶有dp[i]种方案。
- 确定递推公式：dp[i] = dp[i - 1] + dp[i - 2]
- dp数组如何初始化： 注意上述n需要大于2 dp[1] = 1, dp[2] = 2; 为什么不考虑dp[0]？ 题目中说n为正整数，所以不包含0；
- 确定遍历顺序： 从前向后遍历，dp[i] 依赖于dp[i -1] 与 dp[i -2]
- 打印dp数组：在for循环中可以进行打印  

具体实现：
- 类似于斐波那契数列，不再赘述。  

### 3. 使用最小花费爬楼梯
力扣地址： https://leetcode.cn/problems/min-cost-climbing-stairs/description/  
项目代码： DMinCostClimbingStairs.class  
核心代码：  
题目描述：cost数组表示你每次向上爬台阶的话费，你可以从0或1开始走1或2步，求最小花费。注意我们要明确两点：
- 1 开始站位你可以站到0，1 并不会花费
- 2 结束位置要大于n 如 cost 0,1,2。那么我们结束位置要在3的位置。
``` java.
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 0;
        for(int i = 2; i <= n ; i++ ){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
```
算法思路：  
- 明确DP数组及下标含义：dp[i] i表示到了哪个台阶，dp[i]表示到达下标i位置的最小花费。
- 确定递推公式： dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
- dp数组如何初始化： 分析递推公式基础在于dp[0] 与 dp[1], 后续的所有dp元素都可以根据dp[0]dp[1]计算得来。
题目中说可以从0或1开始，走1，2步向上跳的时候才会产生花费。 所以dp[0] = 0, dp[1] = 0。向上跳的时候才会产生花费。
- 确定遍历顺序： 从前向后，dp[i] 依赖于前两个元素。
- 打印dp数组：for循环中可以打印。

具体实现：
- 注意实现的小细节，为什么这里我们dp数组初始化要到 n+ 1个元素，这是因为我们需要爬过cost数组的所有台阶。
- 返回的是上到n阶台阶的最小花费。  

## 二维动态规划问题
### 4. 不同路径
力扣地址： https://leetcode.cn/problems/unique-paths/description/  
项目代码： DUniquePaths.class  
题目描述： 在一个给定m x n网络中，从左上角出发到右下角一共有多少种方案，每次只能向下或向右。
![DUniquePaths.png](image/DUniquePaths.png)
核心代码：
``` java.
        int[][] dp = new int[m][n];
        // 初始化
        for(int i =0;i<m;i++){dp[i][0] = 1;}; // 初始化第一列
        for(int j =0; j < n; j++){dp[0][j] = 1;}// 初始化第一行
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i -1][j] + dp[i][j -1];
            }
        }
        return dp[m - 1][n -1];
```
算法思路：
- **明确DP数组及下标含义：** dp[i][i],从0，0到i，j有多少种不同的路径。
- **确定递推公式：** dp[i,j]可以由上面的元素 dp[i - 1,j]，和左边的元素dp[i, j -1]推导而来。
可以分析出递推公式：dp[i,j] = dp [i - 1, j] + dp[i, j -1];
- **dp数组如何初始化：** 从递推公式可以看出每一个元素都是由上方元素和左方元素推导得来，所以dp[0][j]行要初始化，dp[i][0]也要初始化。
初始化的值为1，从起点出发到达第0行所有元素和第0列所有元素都是一种方法，一直向右或一直向左。
- **确定遍历顺序：** 每一个状态依赖于上面和左面元素，遍历顺序为从左往右，从上向下进行遍历。
- **打印dp数组：** 排查错误

具体实现：
- 注意最终的结果直接返回dp[m -1][n -1]即可

### 5. 不同路径二
力扣地址： https://leetcode.cn/problems/unique-paths-ii/description/  
项目代码： DUniquePathsTwo.class  
题目描述： 在一个给定的二维数组种，从左上角出发到右下角一共有多少种方案，每次只能向下或向右,现在存在障碍用1表示。  
![DUniquePathsTwo.png](image/DUniquePathsTwo.png)
核心代码：
``` java.
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][ n - 1] == 1)return 0;
        for (int i = 0; i < m; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for (int j = 0; j < n ; j++){
            if(obstacleGrid[0][j] == 1){
                break;
            }
            dp[0][j] = 1;
        }
        // 开始遍历
        for(int i = 1; i < m ; i++){
            for(int j = 1; j < n ; j++){
                if(obstacleGrid[i][j] == 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j -1];
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
```
算法思路：
- **写在前面：** 本题与上一题最大的区别是加入了障碍，这样我们在进行初始化和计算dp[i][j]的时候都需要进行判断障碍。
- **明确DP数组及下标含义：** dp[i][j]表示从左上到右下一共有多少种方案。
- **确定递推公式：** dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 
- **dp数组如何初始化：** 对第一行第一列进行初始化，如果遇到障碍，则当前节点和后续节点都为0。需要注意的是如果左上和右下任意一个节点为障碍那么将会有0种方案。
- **确定遍历顺序：** 从左到右 从上到下进行遍历。
- **打印dp数组：**
具体实现：
- 参数判断处：我们需要判断obs数组是否为空和左上角右下角是否为障碍，如果为障碍那么直接返回0即可。
- 初始化处：如果当前初始化节点为障碍，那么我们当前和后续元素全初始化为0。
- 遍历处：如果当前节点为障碍，那么表示所有路径都不可到达当前节点，所以置为0。

### 1. 题目名称
力扣地址： https://leetcode.cn/problems/assign-cookies/description/  
项目代码： DSendCake.class  
核心代码：
``` java.
        start
```
算法思路：
- **明确DP数组及下标含义：**
- **确定递推公式：**
- **dp数组如何初始化：**
- **确定遍历顺序：**
- **打印dp数组：**

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
- **明确DP数组及下标含义：**
- **确定递推公式：**
- **dp数组如何初始化：**
- **确定遍历顺序：**
- **打印dp数组：**

具体实现：
- 实现  
