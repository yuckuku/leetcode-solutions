package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/4/28 15:46
 * Description:
 */
public class SummaryRanges228 {
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> re = new ArrayList<>();
            int first = Integer.MIN_VALUE;
            int pre = Integer.MIN_VALUE;
            int count = 0;
            for (int i = 0; i <= nums.length; i++) {
                if (i < nums.length && nums[i] - pre == 1) {
                    pre = nums[i];
                    count++;
                    continue;
                } else {
                    if (count == 0) {
                        count++;
                    } else if (count == 1) {
                        re.add("" + pre);
                        count = 1;
                    } else {
                        re.add(first + "->" + pre);
                        count = 1;
                    }
                    if (i < nums.length) {
                        first = nums[i];
                        pre = nums[i];
                    }
                }
            }
            return re;
        }
    }

    class SolutionOnLeetCode1 {
        public List<String> summaryRanges(int[] nums) {
            List<String> result = new ArrayList<String>();
            int p1 = 0, p2 = 0;
            boolean b1 = false, b2 = false;
            for (int i = 0; i < nums.length; i++) {
                if (!b1) {
                    p1 = nums[i];
                    b1 = true;
                    continue;
                }
                if (!b2) {
                    if (p1 + 1 == nums[i]) {
                        p2 = nums[i];
                        b2 = true;
                    } else {
                        result.add(p1 + "");
                        b1 = false;
                        i--;
                    }
                } else {
                    if (p2 + 1 == nums[i]) {
                        p2 = nums[i];
                    } else {
                        b1 = false;
                        b2 = false;
                        i--;
                        result.add(p1 + "->" + p2);
                    }
                }
            }
            if (b1) {
                if (b2) {
                    result.add(p1 + "->" + p2);
                } else {
                    result.add(p1 + "");
                }
            }
            return result;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] a = new int[]{0, 1, 2, 4, 5, 7};
        List<String> re = solution.summaryRanges(a);
        System.out.println(re);

        a = new int[]{0, 2, 3, 4, 6, 8, 9};
        re = solution.summaryRanges(a);
        System.out.println(re);
    }
}
