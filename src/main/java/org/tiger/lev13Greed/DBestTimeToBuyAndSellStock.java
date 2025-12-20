package org.tiger.lev13Greed;

/**
 * @ClassName DBestTimeToBuyAndSellStock
 * @Description 最佳股票买入时机 2
 * @Author tiger
 * @Date 2025/12/9 16:06
 */
public class DBestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices){
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];
            if(profit > 0){
                maxProfit += profit;
            }
        }
        return maxProfit;
    }
}
