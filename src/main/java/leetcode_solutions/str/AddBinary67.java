package leetcode_solutions.str;

import org.junit.Test;

public class AddBinary67 {
    class Solution {
        public String addBinary(String a, String b) {
            if (a.length() < b.length())
                return add(a, b);
            else
                return add(b, a);
        }

        private String add(String a, String b) {
            char[] achs = a.toCharArray();
            char[] bchs = b.toCharArray();
            int aLen = achs.length;
            int bLen = bchs.length;
            char[] chs = new char[bLen + 1];
            char carry = '0';
            char tmp = '0';
            int aIdx = aLen - 1, bIdx = bLen - 1;
            for (; aIdx >= 0; aIdx--, bIdx--) {
                int t = achs[aIdx] + bchs[bIdx] + carry;
                switch (t) {
                    case 144:
                        carry = '0';
                        tmp = '0';
                        break;
                    case 145:
                        carry = '0';
                        tmp = '1';
                        break;
                    case 146:
                        carry = '1';
                        tmp = '0';
                        break;
                    case 147:
                        carry = '1';
                        tmp = '1';
                        break;
                    default:
                        break;
                }
                chs[bIdx + 1] = tmp;
            }
            for (; bIdx >= 0; bIdx--) {
                int t = '0' + bchs[bIdx] + carry;
                switch (t) {
                    case 144:
                        carry = '0';
                        tmp = '0';
                        break;
                    case 145:
                        carry = '0';
                        tmp = '1';
                        break;
                    case 146:
                        carry = '1';
                        tmp = '0';
                        break;
                    case 147:
                        carry = '1';
                        tmp = '1';
                        break;
                    default:
                        break;
                }
                chs[bIdx + 1] = tmp;
            }
            chs[0] = carry;
            if (carry == '1') {
                return String.valueOf(chs);
            } else {
                return String.valueOf(chs, 1, bLen);
            }
        }



//        private void add(char a, char b, Character carry, Character tmp) {
//            int i = a + b + carry;
//            switch (i) {
//                case 144:
//                    carry = '0';
//                    tmp = '0';
//                    break;
//                case 145:
//                    carry = '0';
//                    tmp = '1';
//                    break;
//                case 146:
//                    carry = '1';
//                    tmp = '0';
//                    break;
//                case 147:
//                    carry = '1';
//                    tmp = '1';
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    class SolutionOnLeetcode1 {
        public String addBinary(String a, String b) {
            int shift = 0;
            int maxLen = a.length() > b.length() ? a.length() : b.length();
            char[] result = new char[maxLen + 1];

            for (int i = 0; i < maxLen; i++) {
                int sum = shift;
                if (i < a.length()) {
                    int val = a.charAt(a.length() - 1 - i) - '0';
                    sum += val;
                }
                if (i < b.length()) {
                    int val = b.charAt(b.length() - 1 - i) - '0';
                    sum += val;
                }
                int v = sum % 2;
                shift = sum / 2;

                result[maxLen - i] = (char) (v + '0');
            }
            if (shift > 0) {
                result[0] = (char) (shift + '0');
                shift = 0;
            }
            return result[0] == 0 ? new String(result, 1, result.length - 1) : new String(result);
        }
    }

    @Test
    public void test() {
        String a = "11";
        String b = "1";
        SolutionOnLeetcode1 solution = new SolutionOnLeetcode1();
        String re = solution.addBinary(a, b);
        System.out.println(re);

//        char a = '0';
//        System.out.println(a);
//        System.out.println((int) a);
//        System.out.println((int) '1');
//
//        System.out.println('0' + '0' + '0');
//        System.out.println('1' + '0' + '0');
//        System.out.println('1' + '1' + '0');
//        System.out.println('1' + '1' + '1');

    }
}
