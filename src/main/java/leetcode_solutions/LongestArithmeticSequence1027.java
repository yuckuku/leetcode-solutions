package leetcode_solutions;

import org.junit.Test;

import java.util.*;

public class LongestArithmeticSequence1027 {
    /**
     * 等差数列 其实在 给定前2个数之后，就能求出整个等差数列(虽说是无限长的)。
     * 既然如此，那就不断地 固定等差数列的 前2个数，去判断下1个理论值 是否在数组A[]中 且 在数组A[]中的下标 要 > 等差数列的最后1个实际值的下标(即理论值的 上1个值的下标)。
     * <p>
     * 构建1个HashMap：数组A[]中的值作为key，值所对应的下标 去组成1个ArrayList 作为value。
     */
    //执行用时:87ms,在所有Java提交中击败了78.64%的用户
    //内存消耗:37.1MB,在所有Java提交中击败了100.00%的用户
    class Solution {
        public int longestArithSeqLength(int[] A) {
            int len = A.length;
            //构建HashMap: key: 数的值， value: 由数在 数组A中的下标 所构成的ArrayList
            Map<Integer, List<Integer>> map = new HashMap<>();
            //遍历数组A并构造HashMap
            for (int i = 0; i < len; ++i) {
                List<Integer> temp_list = map.computeIfAbsent(A[i], unused -> new ArrayList<>());
                temp_list.add(i);
            }
            //存储最终的结果
            int res = 0;
            //计数器，初始化为2(从2起步)
            int count = 2;
            //拿来打杂的，存储 当前等差数列中，理论上的 下1个数的值
            int temp;
            //确定等差数列中的第1个数
            for (int i = 0; i < len - res; ++i) {
                //确定等差数列中的第2个数
                for (int j = i + 1; j < len - res + 1; ++j) {
                    //计算等差数列的 差值
                    int diff = A[j] - A[i];
                    //若差值为0，则A[j]和A[i]相等，在HashMap中查找key为A[i]或A[j] 的value(ArrayList的长度，即下标的个数)
                    if (diff == 0) {
                        //更新res的值
                        res = Math.max(res, map.get(A[i]).size());
                        //跳到下一次循环
                        continue;
                    }
                    //获取 当前等差数列中， 理论上的 下1个数的值
                    temp = A[j] + diff;
                    //记录 等差数列中，最后1个数的下标(初始化为j，即等差数列中第2个数的下标)
                    int last_idx = j;
                    //HashMap中 存在 当前等差数列， 下1个数的理论值
                    search:
                    while (map.containsKey(temp)) {
                        //获取key为理论值的 value(ArrayList)
                        List<Integer> temp_list = map.get(temp);
                        //遍历value(ArrayList)
                        for (int k = 0; k < temp_list.size(); ++k) {
                            //记录当前下标 所代表的值(值 代表 temp这个数 在 A数组中的下标)
                            int temp_idx = temp_list.get(k);
                            //若该值 > 等差数列中 最后1个值的下标
                            if (temp_idx > last_idx) {
                                //更新last_idx(等差数列中 最后1个值的下标)
                                last_idx = temp_idx;
                                //count + 1
                                ++count;
                                //计算 当前等差数列中， 理论上的 下1个数的值
                                temp += diff;
                                continue search;
                            }
                        }
                        //未找到则break
                        break search;
                    }
                    //更新res的值
                    res = Math.max(res, count);
                    //count计数 重置为2(从2起步)
                    count = 2;
                }
            }
            return res;
        }
    }

    //为了使代码更短更简洁，就将线性查找修改为二分查找：
    //执行用时:66ms,在所有Java提交中击败了92.54%的用户
    //内存消耗:39.3MB,在所有Java提交中击败了100.00%的用户
    class Solution1 {
        public int longestArithSeqLength(int[] A) {
            int len = A.length;
            //构建HashMap: key: 数的值， value: 由数在 数组A中的下标 所构成的ArrayList
            Map<Integer, List<Integer>> map = new HashMap<>();
            //遍历数组A并构造HashMap
            for (int i = 0; i < len; ++i) {
                List<Integer> temp_list = map.computeIfAbsent(A[i], unused -> new ArrayList<>());
                temp_list.add(i);
            }
            //存储最终的结果
            int res = 0;
            //计数器，初始化为2(从2起步)
            int count = 2;
            //拿来打杂的，存储 当前等差数列中，理论上的 下1个数的值
            int temp;
            //确定等差数列中的第1个数
            for (int i = 0; i < len - res; ++i) {
                //确定等差数列中的第2个数
                for (int j = i + 1; j < len - res + 1; ++j) {
                    //计算等差数列的 差值
                    int diff = A[j] - A[i];
                    //若差值为0，则A[j]和A[i]相等，在HashMap中查找key为A[i]或A[j] 的value(ArrayList的长度，即下标的个数)
                    if (diff == 0) {
                        //更新res的值
                        res = Math.max(res, map.get(A[i]).size());
                        //跳到下一次循环
                        continue;
                    }
                    //获取 当前等差数列中， 理论上的 下1个数的值
                    temp = A[j] + diff;
                    //记录 等差数列中，最后1个数的下标(初始化为j，即等差数列中第2个数的下标)
                    int last_idx = j;
                    //HashMap中 存在 当前等差数列， 下1个数的理论值
                    while (map.containsKey(temp)) {
                        //获取key为理论值的 value(ArrayList)
                        List<Integer> temp_list = map.get(temp);
                        //Collections内置函数二分查找
                        last_idx = -(Collections.binarySearch(temp_list, last_idx) + 1);
                        if (last_idx == temp_list.size())
                            break;
                        last_idx = temp_list.get(last_idx);
                        //count + 1
                        ++count;
                        //计算 当前等差数列中， 理论上的 下1个数的值
                        temp += diff;
                    }
                    //更新res的值
                    res = Math.max(res, count);
                    //count计数 重置为2(从2起步)
                    count = 2;
                }
            }
            return res;
        }
    }


    @Test
    public void test() {
        List<Integer> temp_list = Arrays.asList(1, 2, 4, 5, 6);
        System.out.println(temp_list);
        int last_idx = 3;
        System.out.println(Collections.binarySearch(temp_list, last_idx));
        last_idx = -(Collections.binarySearch(temp_list, last_idx) + 1);
        System.out.println(last_idx);
        System.out.println(last_idx == temp_list.size());
        last_idx = temp_list.get(last_idx);
        System.out.println(last_idx);
    }

    //DP
    //执行用时:16ms,在所有Java提交中击败了100.00%的用户
    //内存消耗:51.1MB,在所有Java提交中击败了100.00%的用户
    class Solution17ms {
        public int longestArithSeqLength(int[] A) {
            //happygrillzt
            int n = A.length;
            if (n < 2) return n;
            int[][] dp = new int[n][n];
            //dp[i][j] means nums[i] nums[j] is two last elements.
            int[] index = new int[20001];
            int res = 2;
            Arrays.fill(index, -1);
            for (int i = 0; i < n - 1; i++) {
                Arrays.fill(dp[i], 2);
                for (int j = i + 1; j < n; j++) {
                    int prev = A[i] * 2 - A[j];
                    if (prev < 0 || index[prev] == -1) continue;
                    dp[i][j] = dp[index[prev]][i] + 1;
                    res = Math.max(res, dp[i][j]);
                }
                index[A[i]] = i;
            }
            return res;
        }
    }
}
