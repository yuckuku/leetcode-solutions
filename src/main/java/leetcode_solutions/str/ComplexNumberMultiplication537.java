package leetcode_solutions.str;

import org.junit.Test;

public class ComplexNumberMultiplication537 {
    class Solution {
        public String complexNumberMultiply(String a, String b) {
            int i, j, k, f;
            String[] ss = a.split("\\+");
            i = Integer.parseInt(ss[0]);
            j = Integer.parseInt(ss[1].substring(0, ss[1].length() - 1));
            ss = b.split("\\+");
            k = Integer.parseInt(ss[0]);
            f = Integer.parseInt(ss[1].substring(0, ss[1].length() - 1));
            int e, g;
            e = i * k - j * f;
            g = k * j + i * f;

            return e == 0 ? "0" + "+" + g + "i" : e + "+" + g + "i";
        }
    }
    class Solution1 {
        public String complexNumberMultiply(String a, String b) {
            int[] index=new int[4];
            String[] n1=a.split("\\+");
            index[0]=Integer.parseInt(n1[0]);
            index[1]=Integer.parseInt(n1[1].substring(0,n1[1].length()-1));
            String[] n2=b.split("\\+");
            index[2]=Integer.parseInt(n2[0]);
            index[3]=Integer.parseInt(n2[1].substring(0,n2[1].length()-1));
            int res1=index[0]*index[2]-index[1]*index[3];
            int res2=index[0]*index[3]+index[1]*index[2];
            return ""+res1+"+"+res2+"i";
            // System.out.println(index[1]+" "+index[3]);
            // return "";
        }
    }


    @Test
    public void test() {
        String s = "1+1i";
        String re = new Solution().complexNumberMultiply(s, s);
        System.out.println(re);
    }
}
