package leetcode_solutions;

/**
 * Created by Administrator on 2017/10/30.
 */
public class BestTimetoBuyandSellStockII122 {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i]>prices[i-1]){
                max+=prices[i]-prices[i-1];
            }
        }
        return max;
    }
}
