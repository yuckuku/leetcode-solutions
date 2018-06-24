package leetcode_solutions;

public class PowerofThree326 {
    public boolean isPowerOfThree(int n) {
        if (n == 1 || n == 3) {
            return true;
        }

        if (n < 3) {
            return false;
        }

        while (n > 3) {
            if (n % 3 == 1 || n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        if (n % 3 == 1 || n % 3 == 2) {
            return false;
        }
        return true;
    }

    public boolean isPowerOfThree0(int n) {
        if (n == 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}
