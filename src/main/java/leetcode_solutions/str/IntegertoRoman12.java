package leetcode_solutions.str;

import org.junit.Test;

public class IntegertoRoman12 {
    class Solution {
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            //千
            int a = num / 1000;
            if (a > 0) {
                for (int i = 0; i < a; i++) {
                    sb.append('M');
                }
            }
            //百
            num = num % 1000;
            a = num / 100;
            if (a > 0) {
                switch (a) {
                    case 4:
                        sb.append('C').append('D');
                        break;
                    case 5:
                        sb.append('D');
                        break;
                    case 9:
                        sb.append('C').append('M');
                        break;
//                    case 1:
//                        sb.append('C');
//                        break;
                    default:
                        if (a <= 3) {
                            for (int i = 0; i < a; i++) {
                                sb.append('C');
                            }
                        } else {
                            sb.append('D');
                            for (int i = 0; i < a - 5; i++) {
                                sb.append('C');
                            }
                        }
                        break;
                }
            }
            //十
            num = num % 100;
            a = num / 10;
            if (a > 0) {
                switch (a) {
                    case 4:
                        sb.append('X').append('L');
                        break;
                    case 5:
                        sb.append('L');
                        break;
                    case 9:
                        sb.append('X').append('C');
                        break;
//                    case 1:
//                        sb.append('X');
//                        break;
                    default:
                        if (a <= 3) {
                            for (int i = 0; i < a; i++) {
                                sb.append('X');
                            }
                        } else {
                            sb.append('L');
                            for (int i = 0; i < a - 5; i++) {
                                sb.append('X');
                            }
                        }
                        break;
                }
            }
            //个
            num = num % 10;
            if (num > 0) {
                switch (num) {
                    case 4:
                        sb.append('I').append('V');
                        break;
                    case 5:
                        sb.append('V');
                        break;
                    case 9:
                        sb.append('I').append('X');
                        break;
                    default:
                        if (num <= 3) {
                            for (int i = 0; i < num; i++) {
                                sb.append('I');
                            }
                        } else {
                            sb.append('V');
                            for (int i = 0; i < num - 5; i++) {
                                sb.append('I');
                            }
                        }
                        break;
                }
            }
            return sb.toString();
        }
    }

    class Solution1 {
        public String intToRoman(int num) {
            int[] val = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] rom = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (num > 0) {
                if (num >= val[i]) {
                    num = num - val[i];
                    sb.append(rom[i]);
                } else {
                    i++;
                }
            }
            return sb.toString();
        }


    }

    @Test
    public void test() {
//        int num = 1994;//MCMXCIV
        int num = 3;
        String re = new Solution().intToRoman(num);
        System.out.println(re);
    }
}
