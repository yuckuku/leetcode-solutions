package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class BestTimetoBuyandSellStockwithTransactionFee714 {
    //my solution:wrong answer;reclusive idea can not solve this problem
    public int maxProfit(int[] prices, int fee) {
        int profit = 0;
        int lastBuyIndex = 0;
        int lastSellIndex = 0;
        int buyIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[buyIndex]) {
                if (prices[i] - prices[buyIndex] > fee && i == prices.length - 1) {
                    profit += (prices[i] - prices[buyIndex] - fee);
                    return profit;
                }
                if (prices[i] - prices[buyIndex] > fee && prices[i] > prices[i + 1]) {
                    int currentProfit = prices[i] - prices[buyIndex] - fee;//temp profit
                    int lastProfit = prices[lastSellIndex] - prices[lastBuyIndex] - fee;//last profit
                    lastProfit = Math.max(0, lastProfit);
                    int totalProfit = prices[i] - prices[lastBuyIndex] - fee;//total profit
                    if (currentProfit + lastProfit > totalProfit) {
                        profit += currentProfit;
                        lastBuyIndex = buyIndex;
                        lastSellIndex = i;
                        buyIndex = i;
                    } else {
                        profit = profit + totalProfit - lastProfit;
                        lastSellIndex = i;
                        buyIndex = i;
                    }
                }
            }
            if (prices[i] == prices[buyIndex]) {
                continue;
            }
            if (prices[i] < prices[buyIndex]) {
                buyIndex = i;
            }
        }

        return profit;
    }

    //dynamic planning
    public int maxProfit0(int[] prices, int fee) {
        int hold = -prices[0];//the maxPro you have if you have a stock that day, if you have a stock the first day,hold=-prices[0]
        int profit = 0;//the maxPro you have if you don't have a stock that day
        int i = 1;
        for (; i < prices.length; i++) {
            profit = Math.max(profit, hold + prices[i] - fee);//cash in day i is the maxvalue of cash in day i-1 or you sell your stock
            hold = Math.max(hold, profit - prices[i]);
        }
        return profit;
    }

    //greedy
    public int maxProfit1(int[] prices, int fee) {
        int profit = 0;
        int curProfit = 0;
        int minP = prices[0];
        int maxP = prices[0];
        int i;
        for (i = 1; i < prices.length; i++) {
            minP = Math.min(minP, prices[i]);
            maxP = Math.max(maxP, prices[i]);
            curProfit = Math.max(curProfit, prices[i] - minP - fee);
            if ((maxP - prices[i]) >= fee) {//can just sell the stock at maxP day.
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        return profit + curProfit;//the last trade have to be made if there is some profit
    }


    @Test
    public void test() {
        int[] nums =
//                {1, 3, 7, 5, 10, 3};
                {1, 3, 2, 8, 4, 9};
        maxProfit1(nums, 2);
    }

}
