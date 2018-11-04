package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.Arrays;

public class ValidTriangleNumber611 {
    //over time limited
    public int triangleNumber(int[] nums) {
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (isTriangle(nums[i], nums[j], nums[k])) {
                        number++;
                    }
                }
            }
        }
        return number;
    }

    public int triangleNumber0(int[] nums) {
        Arrays.sort(nums);
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (isTriangle(nums[i], nums[j], nums[k])) {
                        number++;
                    } else {
                        break;
                    }
                }
            }
        }
        return number;
    }

    private boolean isTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (c + b > a) ? true : false;
    }

    //better solution
    public int triangleNumber1(int[] nums) {
        int n = nums.length;
        int res = 0;
        Arrays.sort(nums);
        for (int i = n - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 1, 2, 3};
        Arrays.sort(nums);

        for (int num :
                nums) {
            System.out.println(num);
        }
    }
}
