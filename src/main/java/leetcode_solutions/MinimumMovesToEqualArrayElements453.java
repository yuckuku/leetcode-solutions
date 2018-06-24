package leetcode_solutions;

import org.junit.Test;

/**
 * Created by Administrator on 2017/10/30.
 */
public class MinimumMovesToEqualArrayElements453 {

    //time limit exceeded
    public int minMoves(int[] nums) {
        int moves = 0;
        while (!check(nums)) {
            move(nums);
            moves++;
            System.out.println("---------");
            for (int num : nums
                    ) {
                System.out.println(num);
            }
        }
        return moves;
    }

    //one move
    public void move(int[] nums) {
        int max = nums[0];
        int maxId = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                nums[maxId]++;
                max = nums[i];
                maxId = i;
            } else {
                nums[i]++;
            }
        }
    }

    //check elements all same or not
    public boolean check(int[] nums) {
        for (int num : nums
                ) {
            if (num != nums[0]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        int moves = minMoves(nums);
        System.out.println(check(nums));
        System.out.println(moves);
    }

    // 给n-1个数字加1，效果等同于给那个未被选中的数字减1
    //reverse thinking
    public int minMoves1(int[] nums) {
        int min = nums[0];
        int total = 0;
        for (int num :
                nums) {
            min = num < min ? num : min;
            total += num;
        }
        return total - min * nums.length;
    }
}
