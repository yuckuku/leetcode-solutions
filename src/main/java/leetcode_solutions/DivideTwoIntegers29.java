package leetcode_solutions;

import org.junit.Test;

import java.time.LocalDateTime;

public class DivideTwoIntegers29 {

    //TLE
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == 0) return 0;
            int re = 0;

            if (dividend > 0 && divisor > 0) {
                while (dividend >= divisor) {
                    dividend -= divisor;
                    re++;
                }
                return re;
            } else if (dividend > 0 && divisor < 0) {
                while (dividend >= -divisor) {
                    dividend += divisor;
                    re++;
                }
                return -re == Integer.MIN_VALUE ? Integer.MAX_VALUE : -re;
            } else if (dividend < 0 && divisor > 0) {
                while (dividend <= -divisor) {
                    dividend += divisor;
                    re++;
                }
                return -re;
            } else {
                while (dividend <= divisor) {
                    dividend -= divisor;
                    re++;
                }
                return re == Integer.MIN_VALUE ? Integer.MAX_VALUE : re;
            }

        }
    }

    @Test
    public void test() {
        int dividend = -2147483648;
        int divisor = -1;
        System.out.println(Math.abs(dividend));
        System.out.println(Math.abs(divisor));
        System.out.println(Integer.MIN_VALUE);
        System.out.println("------------");
        Solution solution = new Solution();
        System.out.println(LocalDateTime.now());
        int re = solution.divide(dividend, divisor);
        System.out.println(LocalDateTime.now());

        System.out.println(re);
    }

    class SolutionWithLong {

        public int divide(int dividend, int divisor) {
            long ans = divide((long) dividend, (long) (divisor));
            long m = 2147483648L;
            if (ans == m) {
                return Integer.MAX_VALUE;
            } else {
                return (int) ans;
            }
        }

        public long divide(long dividend, long divisor) {
            long ans = 1;
            long sign = 1;
            if (dividend < 0) {
                sign = opposite(sign);
                dividend = opposite(dividend);
            }
            if (divisor < 0) {
                sign = opposite(sign);
                divisor = opposite(divisor);
            }
            long origin_dividend = dividend;
            long origin_divisor = divisor;

            if (dividend < divisor) {
                return 0;
            }

            dividend -= divisor;
            while (divisor <= dividend) {
                ans = ans + ans;
                divisor += divisor;
                dividend -= divisor;
            }
            long a = ans + divide(origin_dividend - divisor, origin_divisor);
            return sign > 0 ? a : opposite(a);
        }

        public long opposite(long x) {
            return ~x + 1;
        }
    }

    class Solution1 {

        public int divide(int dividend, int divisor) {
            int ans = -1;
            int sign = 1;
            if (dividend > 0) {
                sign = opposite(sign);
                dividend = opposite(dividend);
            }
            if (divisor > 0) {
                sign = opposite(sign);
                divisor = opposite(divisor);
            }

            int origin_dividend = dividend;
            int origin_divisor = divisor;
            if (dividend > divisor) {
                return 0;
            }

            dividend -= divisor;
            while (divisor >= dividend) {
                ans = ans + ans;
                divisor += divisor;
                dividend -= divisor;
            }
            //此时我们传进的是两个负数，正常情况下，它就返回正数，但我们是在用负数累加，所以要取相反数
            int a = ans + opposite(divide(origin_dividend - divisor, origin_divisor));
            if (a == Integer.MIN_VALUE) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            } else {
                if (sign > 0) {
                    return opposite(a);
                } else {
                    return a;
                }
            }
        }

        public int opposite(int x) {
            return ~x + 1;
        }
    }


    @Test
    public void test1() {
//        int dividend = -2147483648;
        int dividend = 12;
        int divisor = -1;

        SolutionWithLong solution = new SolutionWithLong();
        long tmp = solution.opposite(dividend);
        System.out.println(Integer.toBinaryString(dividend));
        System.out.println(tmp);
        System.out.println(Integer.toBinaryString((int) tmp));
    }

}
