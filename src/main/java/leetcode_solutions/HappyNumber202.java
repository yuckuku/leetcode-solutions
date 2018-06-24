package leetcode_solutions;

import org.junit.Test;

public class HappyNumber202 {
    public boolean isHappy(int n) {
        if (n == 1||n==7) {
            return true;
        }
        if (n < 0 || n / 10 == 0) {
            return false;
        }
        int sumOfSquares = 0;
        int i = n % 10;
        while (n != 0) {
            sumOfSquares += i * i;
            n = n / 10;
            i = n % 10;
        }
        return isHappy(sumOfSquares);
    }

    @Test
    public void test() {
        HappyNumber202 happyNumber202 = new HappyNumber202();
        happyNumber202.isHappy(2);
    }
}
