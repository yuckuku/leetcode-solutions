package leetcode_solutions;

import org.junit.Test;

public class BinaryStringWithSubstringsRepresenting1ToN1016 {
    class Solution {
        public boolean queryString(String S, int N) {
            for (int i = N; i >= 1; i--) {
                if (!S.contains(binaryF(i))) return false;
            }
            return true;
        }

        private String binaryF(int i) {
            StringBuilder sb = new StringBuilder();
            while (i != 0) {
                sb.append(i % 2);
                i >>= 1;
            }
            return sb.reverse().toString();
        }
    }

    class Solution1 {
        public boolean queryString(String S, int N) {
            for(int i = 1;i<=N;i++)
                if(!S.contains(Integer.toBinaryString(i)))
                    return false;
            return true;
        }
    }

    @Test
    public void test() {
        String S = "0110";
        int N = 3;
        Solution solution = new Solution();
        boolean re = solution.queryString(S, N);
        System.out.println(re);
    }
}
