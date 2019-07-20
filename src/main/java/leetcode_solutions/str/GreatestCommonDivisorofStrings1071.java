package leetcode_solutions.str;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.*;

public class GreatestCommonDivisorofStrings1071 {
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            int len1 = str1.length();
            int len2 = str2.length();

            if (len1 < len2) {
                return findGcd(str1, str2);
            } else {
                return findGcd(str2, str1);
            }
        }

        private String findGcd(String a, String b) {
            int len = a.length();
            List<Integer> list = getResult(len);
            list.add(1);
            Set<Integer> set = new TreeSet<>();
            dfs(list, 1, set);
            System.out.println(set);
            while (!set.isEmpty()) {
                int l = ((TreeSet<Integer>) set).pollLast();
                System.out.println(l);
                String s = a.substring(0, l);
                if (divide(s, b)) return s;
            }

            return "";
        }

        private void dfs(List<Integer> list, int product, Set<Integer> set) {
            if (!set.contains(product)) {
                set.add(product);
            }
            for (int i = 0; i < list.size(); i++) {
                List<Integer> temp = new LinkedList<Integer>(list);
                int item = (int) temp.remove(i);
                dfs(temp, product * item, set);
            }
        }

        private boolean divide(String a, String b) {
            int len1 = a.length();
            int len2 = b.length();
            if (len1 > len2 || len2 % len1 != 0) return false;
            int n = len2 / len1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(a);
            }
            if (sb.toString().equals(b)) return true;

            return false;
        }

        private List<Integer> getResult(int num) {
            List<Integer> list = new ArrayList<>();
            // 一个合数n,如果n = p * q 则  p 和 q 一个小于等于n的平方根，一个大于等于n的平方根
            //在小于Math.sqrt(num)的数中找不到它的因子，则该数本身应该为质数
            //因此循环到Math.sqrt(num)即可
            // 如 8 = 2 * 4; 10 = 2 * 5;12 = 3 * 4
            for (int i = 2; i <= Math.sqrt(num); i++) {
                // i 等于偶数被整除的情况不可能出现
                // 也不可能循环到Math.sqrt(num)以上的数
                // 循环求i是不是它的质因数，直到i不是它的因数为止(i = 4能被整除的不可能出现，因为当i=2时已经被全求出来了)
                // 也就是说number % i==0的情况，只有当i为质数时才有可能出现。
                while (true) {
                    // 如果能整除，就求number/i的质因数
                    if (num % i == 0) {
                        list.add(i);
                        num /= i;
                    } else {
                        break;
                    }
                }
            }
            if (num != 1) {
                list.add(num);
            }
            return list;
        }

    }

    class SolutionOnLeetcode1 {
        public String gcdOfStrings(String str1, String str2) {
            int l = gcd(str1.length(), str2.length());
            String s = str1.substring(0, l);
            for (int i = l; i < str1.length(); i += l) {
                if (!s.equals(str1.substring(i, i + l))) {
                    return "";
                }
            }
            for (int i = 0; i < str2.length(); i += l) {
                if (!s.equals(str2.substring(i, i + l))) {
                    return "";
                }
            }
            return s;
        }

        private int gcd(int a, int b) {
            int mod = a % b;
            return mod == 0 ? b : gcd(b, mod);
        }
    }

    class SolutionOnLeetcode2 {
        public String gcdOfStrings(String str1, String str2) {
            if (str1.length() < str2.length())
                return gcdOfStrings(str2, str1);
            if (str2.length() == 0)
                return str1;
            if (str1.startsWith(str2)) {
                while (str1.startsWith(str2)) {
                    str1 = str1.substring(str2.length());
                }
                return gcdOfStrings(str2, str1);
            } else {
                return "";
            }
        }
    }

    @Test
    public void test() {
        String str1 = "CLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMV";
        String str2 = "CLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMVCLNVSCUBMV";
//        String str1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
//        String str2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        SolutionOnLeetcode1 s = new SolutionOnLeetcode1();
        String re = s.gcdOfStrings(str1, str2);
        System.out.println(re);
        System.out.println( s.gcd(str1.length(),str2.length()));
//        System.out.println(s.getResult(12));
    }
}
