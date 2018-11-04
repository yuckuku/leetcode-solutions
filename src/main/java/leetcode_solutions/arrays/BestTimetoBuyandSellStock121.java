package leetcode_solutions.arrays;

public class BestTimetoBuyandSellStock121 {

    public int maxProfit(int[] prices) {
        int max=0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i+1; j < prices.length; j++) {
                max=Math.max(max,prices[j]-prices[i]);
            }
        }
        return max;
    }

    //better solution
    public int maxProfit0(int[] prices) {
        int buymin=Integer.MAX_VALUE;
        int sum=0;
        if(prices.length<2)
            return 0;
        else
            buymin=prices[0];
        for(int i=1;i<=prices.length-1;i++){
            sum=(prices[i]-buymin)>sum?(prices[i]-buymin):sum;
            if(prices[i]<buymin) buymin=prices[i];
        }

        return sum;
    }

}



