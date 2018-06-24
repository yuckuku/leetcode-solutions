package leetcode_solutions;

public class PowerofTwo231 {
    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        String str = Integer.toBinaryString(n);

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                continue;
            } else {
                count++;
            }
        }
        if (count != 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isPowerOfTwo0(int n) {
        if (n <= 0)
            return false;
        int ones = 0;
        for (int i = 1; i <= 32; ++i) {
            ones += n & 1;
            n = n >> 1;
        }
        return ones == 1;
    }
}
