package org.tiger.lev13Greed;

/**
 * @ClassName MaxProfit
 * @Description 买股票的最佳时机
 * @Author tiger
 * @Date 2025/12/8 09:50
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("暴力解法，时间复杂度n^2，结果：" + maxProfitViolent(prices));
        System.out.println("优化解法，时间复杂度n，结果：" + maxProfit(prices));
    }

    public static int maxProfitViolent(int[] prices){
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i ++){
            for(int j = i +1; j < prices.length; j++){
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    // 维护一个最小值，当前价格减去之前的最小值就是今天的最大利润。
    public static int maxProfit(int[] prices){
        int maxProfit = 0;
        int minPrice = prices[0];
        for(int i =1;i<prices.length;i++){
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

}
