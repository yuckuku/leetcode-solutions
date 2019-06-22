package leetcode_solutions.arrays;

import org.junit.Test;

public class FindtheDuplicateNumber287 {
    public int findDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int re = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            re ^= nums[i];
            re ^= (i + 1);
        }
        re ^= nums[nums.length - 1];
        return re;
    }

    //dichotomy
    public int findDuplicate0(int[] nums) {
        int min = 0, max = nums.length - 1;
        while (min <= max) {
            // 找到中间那个数
            int mid = min + (max - min) / 2;
            int cnt = 0;
            // 计算总数组中有多少个数小于等于中间数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            // 如果小于等于中间数的数量大于中间数，说明前半部分必有重复
            if (cnt > mid) {
                max = mid - 1;
                // 否则后半部分必有重复
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    //Cycle detection
    public int findDuplicate1(int[] nums) {
        int slow = 0;
        int fast = 0;
        // 找到快慢指针相遇的地方
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        int find = 0;
        // 用一个新指针从头开始，直到和慢指针相遇
        while (find != slow) {
            slow = nums[slow];
            find = nums[find];
        }
        return find;
    }

    @Test
    public void test() {
        int[] nums = new int[]{3, 1, 3, 4, 2};
        int re = findDuplicate1(nums);
        System.out.println(re);
    }
}
