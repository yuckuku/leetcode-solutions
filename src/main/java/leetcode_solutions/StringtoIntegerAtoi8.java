package leetcode_solutions;

import org.junit.Test;

public class StringtoIntegerAtoi8 {
    class Solution {
        public int myAtoi(String str) {

            long re = 0;
            int i = 0;
            long max = 2147483647L;
            long min = 2147483648L;
            //过滤空格
            str = str.trim();
            if (str.length() < 1) return 0;
//            for (; i < str.length(); i++) {
//                if (str.charAt(i) != ' ') break;
//            }

            //校验第一个非空格字符
            if (str.charAt(i) == '-') {
                for (i++; i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i++) {
                    //把数字追加到末尾
                    re *= 10;
                    re += (long) str.charAt(i) - 48;
                    //超出范围，返回最小值
                    if (re > min) return Integer.MIN_VALUE;
                }
                re = -re;
            } else if (str.charAt(i) == '+') {
                for (i++; i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i++) {
                    //把数字追加到末尾
                    re *= 10;
                    re += (long) str.charAt(i) - 48;
                    //超出范围，返回最小值
                    if (re > max) return Integer.MAX_VALUE;
                }
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                for (; i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9'; i++) {
                    //把数字追加到末尾
                    re *= 10;
                    re += (long) str.charAt(i) - 48;
                    //超出范围，返回最小值
                    if (re > max) return Integer.MAX_VALUE;
                }
            } else {
                return 0;
            }

            return (int) re;
        }
    }

    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println("--------------");
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println("--------------");
        System.out.println((int) ' ');//32
        System.out.println((int) '0');//48
        System.out.println((int) '9');//57
        System.out.println((int) '-');//45
        System.out.println((int) '+');//43
        System.out.println((long) '+');//43
        System.out.println("--------------");
        String s = "   -42";
        Solution solution = new Solution();
        int re = solution.myAtoi(s);
        System.out.println(re);
    }

    @Test
    public void test1() {
        String s = "42";
        System.out.println((int) s.charAt(0) - 48);
    }
}
