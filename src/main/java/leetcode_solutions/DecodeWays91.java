package leetcode_solutions;

import org.junit.Test;

import java.util.HashMap;

public class DecodeWays91 {
    /**
     * 执行用时 : 1970 ms, 在所有 java 提交中击败了5.01%的用户
     * 内存消耗 : 37.2 MB, 在所有 java 提交中击败了5.41%的用户
     */
    class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) return 1;
            else if (s.charAt(0) == '0') {
                return 0;
            } else {
                int ans1 = numDecodings(s.substring(1));
                int ans2 = 0;
                //判断前两个数字是不是小于等于 26 的
                if (s.length() > 1) {
                    int ten = (s.charAt(0) - '0') * 10;
                    int one = s.charAt(1) - '0';
                    if (ten + one <= 26) {
                        ans2 = numDecodings(s.substring(2));
                    }
                }
                return ans1 + ans2;
            }
        }
    }

    /**
     * 执行用时 :643 ms, 在所有 java 提交中击败了6.29%的用户
     * 内存消耗 :34.2 MB, 在所有 java 提交中击败了61.94%的用户
     **/
    class Solution1 {

        public int numDecodings(String s) {
            return getAns(s, 0);
        }

        private int getAns(String s, int start) {
            //划分到了最后返回 1
            if (start == s.length()) {
                return 1;
            }
            //开头是 0,0 不对应任何字母，直接返回 0
            if (s.charAt(start) == '0') {
                return 0;
            }
            //得到第一种的划分的解码方式
            int ans1 = getAns(s, start + 1);
            int ans2 = 0;
            //判断前两个数字是不是小于等于 26 的
            if (start < s.length() - 1) {
                int ten = (s.charAt(start) - '0') * 10;
                int one = s.charAt(start + 1) - '0';
                if (ten + one <= 26) {
                    ans2 = getAns(s, start + 2);
                }
            }
            return ans1 + ans2;
        }
    }

    //解法一的递归中，走完左子树，再走右子树会把一些已经算过的结果重新算，所以我们可以用 memoization 技术，就是算出一个结果很就保存，第二次算这个的时候直接拿出来就可以了。

    /**
     * 执行用时 :4 ms, 在所有 java 提交中击败了34.38%的用户
     * 内存消耗 :34.8 MB, 在所有 java 提交中击败了43.09%的用户
     */
    class Solution2 {
        public int numDecodings(String s) {
            HashMap<Integer, Integer> memoization = new HashMap<>();
            return getAns(s, 0, memoization);
        }

        private int getAns(String s, int start, HashMap<Integer, Integer> memoization) {
            if (start == s.length()) {
                return 1;
            }
            if (s.charAt(start) == '0') {
                return 0;
            }
            //判断之前是否计算过
            int m = memoization.getOrDefault(start, -1);
            if (m != -1) {
                return m;
            }
            int ans1 = getAns(s, start + 1, memoization);
            int ans2 = 0;
            if (start < s.length() - 1) {
                int ten = (s.charAt(start) - '0') * 10;
                int one = s.charAt(start + 1) - '0';
                if (ten + one <= 26) {
                    ans2 = getAns(s, start + 2, memoization);
                }
            }
            //将结果保存
            memoization.put(start, ans1 + ans2);
            return ans1 + ans2;
        }

    }

    /**
     * 解法三 动态规划
     * 同样的，递归就是压栈压栈压栈，出栈出栈出栈的过程，我们可以利用动态规划的思想，省略压栈的过程，直接从 bottom 到 top。
     * 用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s 从 i 开始到结尾的字符串的解码方式。
     * 这样和递归完全一样的递推式。
     * 如果 s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么
     * dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]
     */
    class Solution3 {
        public int numDecodings(String s) {
            int len = s.length();
            int[] dp = new int[len + 1];
            dp[len] = 1; //将递归法的结束条件初始化为 1
            //最后一个数字不等于 0 就初始化为 1
            if (s.charAt(len - 1) != '0') {
                dp[len - 1] = 1;
            }
            for (int i = len - 2; i >= 0; i--) {
                //当前数字时 0 ，直接跳过，0 不代表任何字母
                if (s.charAt(i) == '0') {
                    continue;
                }
                int ans1 = dp[i + 1];
                //判断两个字母组成的数字是否小于等于 26
                int ans2 = 0;
                int ten = (s.charAt(i) - '0') * 10;
                int one = s.charAt(i + 1) - '0';
                if (ten + one <= 26) {
                    ans2 = dp[i + 2];
                }
                dp[i] = ans1 + ans2;

            }
            return dp[0];
        }

    }

    /**
     * 接下来就是，动态规划的空间优化了，例如5题，10题，53题，72题等等都是同样的思路。
     * 都是注意到一个特点，当更新到 dp [ i ] 的时候，我们只用到 dp [ i + 1] 和 dp [ i + 2]，之后的数据就没有用了。所以我们不需要 dp 开 len + 1 的空间。
     * 简单的做法，我们只申请 3 个空间，然后把 dp 的下标对 3 求余就够了。
     */
    class Solution4 {
        public int numDecodings4(String s) {
            int len = s.length();
            int[] dp = new int[3];
            dp[len % 3] = 1;
            if (s.charAt(len - 1) != '0') {
                dp[(len - 1) % 3] = 1;
            }
            for (int i = len - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    dp[i % 3] = 0; //这里很重要，因为空间复用了，不要忘记归零
                    continue;
                }
                int ans1 = dp[(i + 1) % 3];
                int ans2 = 0;
                int ten = (s.charAt(i) - '0') * 10;
                int one = s.charAt(i + 1) - '0';
                if (ten + one <= 26) {
                    ans2 = dp[(i + 2) % 3];
                }
                dp[i % 3] = ans1 + ans2;

            }
            return dp[0];
        }

    }

    /**
     * 然后，如果多考虑以下，我们其实并不需要 3 个空间，我们只需要 2 个就够了，只需要更新的时候，指针移动一下，代码如下。
     */
    class Solution5 {
        public int numDecodings5(String s) {
            int len = s.length();
            int end = 1;
            int cur = 0;
            if (s.charAt(len - 1) != '0') {
                cur = 1;
            }
            for (int i = len - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    end = cur;//end 前移
                    cur = 0;
                    continue;
                }
                int ans1 = cur;
                int ans2 = 0;
                int ten = (s.charAt(i) - '0') * 10;
                int one = s.charAt(i + 1) - '0';
                if (ten + one <= 26) {
                    ans2 = end;
                }
                end = cur; //end 前移
                cur = ans1 + ans2;

            }
            return cur;
        }

    }

    class Solution0ms {
        public int numDecodings(String s) {

            int len = s.length();
            if (len == 0) return 0;

            if (s.charAt(0) == '0') return 0;

            int pre2 = 1;
            int pre1 = 1;
            int cur = 1;

            char p, q;
            for (int i = 2; i <= len; i++) {

                p = s.charAt(i - 2);
                q = s.charAt(i - 1);

                cur = 0;

                if (q != '0') cur = pre1;
                if (p == '1') cur += pre2;
                if (p == '2' && q <= '6') cur += pre2;

                if (cur == 0) return 0;

                pre2 = pre1;
                pre1 = cur;
            }

            return cur;
        }
    }

    @Test
    public void test() {
        System.out.println((int) '1');
        System.out.println("--------------");
        String s = "12";
        Solution solution = new Solution();
        int re = solution.numDecodings(s);
        System.out.println(re);
        System.out.println("----------");
        System.out.println(s.substring(1));
        System.out.println(s.substring(2));
    }
}
