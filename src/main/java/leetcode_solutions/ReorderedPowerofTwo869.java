package leetcode_solutions;

import org.junit.Test;

import java.util.Arrays;

public class ReorderedPowerofTwo869 {
    class Solution {
        //穷举法
        //执行用时:1ms,在所有Java提交中击败了100.00%的用户
        //内存消耗:33.5MB,在所有Java提交中击败了68.57%的用户
        public boolean reorderedPowerOf2(int N) {
            String s = String.valueOf(N);
            char[] chs = s.toCharArray();
            int len = chs.length;
            String t = null;
            switch (len) {
                case 1:
                    if (chs[0] == '1' || chs[0] == '2' || chs[0] == '4' || chs[0] == '8') return true;
                    break;
                case 2:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("16") || t.equals("23") || t.equals("46")) return true;
                    break;
                case 3:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("128") || t.equals("256") || t.equals("125")) return true;
                    break;
                case 4:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("0124") || t.equals("0248") || t.equals("0469") || t.equals("1289")) return true;
                    break;
                case 5:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("13468") || t.equals("23678") || t.equals("35566")) return true;
                    break;
                case 6:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("011237") || t.equals("122446") || t.equals("224588")) return true;
                    break;
                case 7:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("0145678") || t.equals("0122579") || t.equals("0134449") || t.equals("0368888"))
                        return true;
                    break;
                case 8:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("11266777") || t.equals("23334455") || t.equals("01466788")) return true;
                    break;
                case 9:
                    Arrays.sort(chs);
                    t = String.valueOf(chs);
                    if (t.equals("112234778") || t.equals("234455668") || t.equals("012356789")) return true;
                    break;
                default:
                    break;
            }

            return false;
        }
    }

    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        int i = 1;
        while (i < 1000000000) {
            System.out.println(i);
            i <<= 1;
        }
    }

 /*   class Solution1ms {
        private static Set<String> set = new HashSet<>();
        static{
            for(int i = 0; i <= 32; i++){
                int temp = 1 << i;
                String temp_string = String.valueOf(temp);
                char [] temp_chars = temp_string.toCharArray();
                Arrays.sort(temp_chars);
                set.add(new String(temp_chars));
            }
        }
        public boolean reorderedPowerOf2(int N) {
            String temp_string = String.valueOf(N);
            char [] temp_chars = temp_string.toCharArray();
            Arrays.sort(temp_chars);
            String newString = new String(temp_chars);
            if(set.contains(newString)) return true;
            return false;
        }
    }*/
}
