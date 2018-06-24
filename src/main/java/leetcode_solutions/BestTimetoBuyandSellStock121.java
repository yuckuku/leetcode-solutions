package leetcode_solutions;

import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BestTimetoBuyandSellStock121 {
    public int maxProfit(int[] prices) {
        if (null == prices || prices.length < 2) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> low_map = new HashMap<>();
        Map<Integer, Integer> high_map = new HashMap<>();

        int len = prices.length;
        int left = prices[0];
        int right = prices[len - 1];
        low_map.put(0, left);
        high_map.put(len - 1, right);

        if (len > 2) {
            right = prices[2];
            for (int i = 1; i < prices.length - 1; i++) {
                left = prices[i - 1];
                right = prices[i + 1];
                if (prices[i] <= left && prices[i] <= right) {
                    System.out.println("in low " + (i));
                    low_map.put(i, prices[i]);
                }
                if (prices[i] >= left && prices[i] >= right) {
                    System.out.println("in high " + (i));
                    high_map.put(i, prices[i]);
                }
            }
        }

        int re = 0;

        for (Map.Entry<Integer, Integer> entry1 : low_map.entrySet()) {
            System.out.println("key: " + entry1.getKey() + " value: " + entry1.getValue());
        }

        System.out.println("-----line------");
        for (Map.Entry<Integer, Integer> entry1 : high_map.entrySet()) {
            System.out.println("key: " + entry1.getKey() + " value: " + entry1.getValue());
        }

        for (Map.Entry<Integer, Integer> entry1 : low_map.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : high_map.entrySet()) {
                if (entry2.getKey() > entry1.getKey()) {
                    re = Math.max(re, entry2.getValue() - entry1.getValue());
                }
            }
        }
        return re;
    }

    @Test
    public void test() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}
