package leetcode_solutions;

import org.junit.Test;

public class LongestPalindromicSubstring5 {
    class Solution {
        public String longestPalindrome(String s) {
            if (s.length() <= 1) return s;
            String re = s.substring(0, 1);
            int max = 1;
            for (int i = 0; i < s.length(); i++) {
                //以i为中心
                int j = i - 1, k = i + 1;
                while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                    j--;
                    k++;
                }
                j++;
                k--;
                if (k - j + 1 > max) {
                    re = s.substring(j, k + 1);
                    max = k - j + 1;
                }
                //以i和i+1为中心
                j = i;
                k = i + 1;
                while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                    j--;
                    k++;
                }
                j++;
                k--;
                if (k - j + 1 > max) {
                    re = s.substring(j, k + 1);
                    max = k - j + 1;
                }
            }
            return re;
        }
    }

    public class SolutionManacher {

        public String longestPalindrome(String s) {
            // 特判
            int len = s.length();
            if (len < 2) {
                return s;
            }

            // 得到预处理字符串
            String str = addBoundaries(s, '#');
            // 新字符串的长度
            int sLen = 2 * len + 1;

            // 数组 p 记录了扫描过的回文子串的信息
            int[] p = new int[sLen];

            // 双指针，它们是一一对应的，须同时更新
            int maxRight = 0;
            int center = 0;

            // 当前遍历的中心最大扩散步数，其值等于原始字符串的最长回文子串的长度
            int maxLen = 1;
            // 原始字符串的最长回文子串的起始位置，与 maxLen 必须同时更新
            int start = 0;

            for (int i = 0; i < sLen; i++) {
                if (i < maxRight) {
                    int mirror = 2 * center - i;
                    // 这一行代码是 Manacher 算法的关键所在，要结合图形来理解
                    p[i] = Math.min(maxRight - i, p[mirror]);
                }

                // 下一次尝试扩散的左右起点，能扩散的步数直接加到 p[i] 中
                int left = i - (1 + p[i]);
                int right = i + (1 + p[i]);

                // left >= 0 && right < sLen 保证不越界
                // str.charAt(left) == str.charAt(right) 表示可以扩散 1 次
                while (left >= 0 && right < sLen && str.charAt(left) == str.charAt(right)) {
                    p[i]++;
                    left--;
                    right++;

                }
                // 根据 maxRight 的定义，它是遍历过的 i 的 i + p[i] 的最大者
                // 如果 maxRight 的值越大，进入上面 i < maxRight 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
                if (i + p[i] > maxRight) {
                    // maxRight 和 center 需要同时更新
                    maxRight = i + p[i];
                    center = i;
                }
                if (p[i] > maxLen) {
                    // 记录最长回文子串的长度和相应它在原始字符串中的起点
                    maxLen = p[i];
                    start = (i - maxLen) / 2;
                }
            }
            return s.substring(start, start + maxLen);
        }


        /**
         * 创建预处理字符串
         *
         * @param s      原始字符串
         * @param divide 分隔字符
         * @return 使用分隔字符处理以后得到的字符串
         */
        private String addBoundaries(String s, char divide) {
            int len = s.length();
            if (len == 0) {
                return "";
            }
            if (s.indexOf(divide) != -1) {
                throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                stringBuilder.append(divide);
                stringBuilder.append(s.charAt(i));
            }
            stringBuilder.append(divide);
            return stringBuilder.toString();
        }
    }


    public class SolutionDp {

        public String longestPalindrome(String s) {
            int len = s.length();
            if (len <= 1) {
                return s;
            }
            int longestPalindrome = 1;
            String longestPalindromeStr = s.substring(0, 1);
            boolean[][] dp = new boolean[len][len];
            // abcdedcba
            //   l   r
            // 如果 dp[l, r] = true 那么 dp[l + 1, r - 1] 也一定为 true
            // 关键在这里：[l + 1, r - 1] 一定至少有 2 个元素才有判断的必要
            // 因为如果 [l + 1, r - 1] 只有一个元素，不用判断，一定是回文串
            // 如果 [l + 1, r - 1] 表示的区间为空，不用判断，也一定是回文串
            // [l + 1, r - 1] 一定至少有 2 个元素 等价于 l + 1 < r - 1，即 r - l >  2

            // 写代码的时候这样写：如果 [l + 1, r - 1]  的元素小于等于 1 个，即 r - l <=  2 ，就不用做判断了

            // 因为只有 1 个字符的情况在最开始做了判断
            // 左边界一定要比右边界小，因此右边界从 1 开始
            for (int r = 1; r < len; r++) {
                for (int l = 0; l < r; l++) {
                    // 区间应该慢慢放大
                    // 状态转移方程：如果头尾字符相等并且中间也是回文
                    // 在头尾字符相等的前提下，如果收缩以后不构成区间（最多只有 1 个元素），直接返回 True 即可
                    // 否则要继续看收缩以后的区间的回文性
                    // 重点理解 or 的短路性质在这里的作用
                    if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                        dp[l][r] = true;
                        if (r - l + 1 > longestPalindrome) {
                            longestPalindrome = r - l + 1;
                            longestPalindromeStr = s.substring(l, r + 1);
                        }
                    }
                }
            }
            return longestPalindromeStr;
        }
    }


    @Test
    public void test() {
        String s = "bbb";
        Solution solution = new Solution();
        String re = solution.longestPalindrome(s);
        System.out.println(re);
    }
}
