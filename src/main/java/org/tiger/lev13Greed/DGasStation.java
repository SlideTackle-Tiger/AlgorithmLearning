package org.tiger.lev13Greed;

/**
 * @ClassName DGasStation
 * @Description 加油站
 * @Author tiger
 * @Date 2025/12/12 11:19
 */
public class DGasStation {
    public static void main(String[] args) {
        int[] gas = {2,5,2,3,5};
        int[] cost = {1,2,8,2,4};
        System.out.println("暴力解法：" + violentSolution(gas, cost));
        System.out.println("贪心算法：" + greedSolution(gas, cost));
    }

    public static int violentSolution(int[] gas, int[] cost){
        int n = gas.length;
        for(int i =0;i<n;i++){
            int carGass = 0;
            int cont = 0;
            while(cont < n){
                int postion = (i + cont) % n;
                carGass = carGass + gas[postion] - cost[postion];
                if(carGass < 0){
                    break;
                }
                cont++;
            }
            if(cont == n)return  i;
        }
        return  -1;
    }

    public static int greedSolution(int[] gas, int[] cost){
        // 利用差值数组 int[] diff = gas[] - cost[];
        int n = gas.length;
        int[] diff = new int[n];
        int diffSum = 0;
        for(int i =0; i < n;i++){
            diff[i] = gas[i] - cost[i];
            diffSum += diff[i];
        }
        if (diffSum < 0)return -1;

        // 贪心算法
        int start = 0;
        int carGas = 0;
        for(int i =0; i < n ;i++){
            carGas += diff[i];
            if(carGas < 0){
                start = i + 1; // 如果从i出发 汽车的油量无法大于0则我们从i+1开始，同时归零油量
                carGas = 0;
            }
        }
        return start;
    }
}
