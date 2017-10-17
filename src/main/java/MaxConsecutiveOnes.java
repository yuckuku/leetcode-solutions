/**
 * Created by Administrator on 2017/10/13.
 */
public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int maxC = 0;
        int currentC = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentC++;
            } else {
                maxC = Math.max(maxC, currentC);
                currentC = 0;
            }
            if (i == nums.length - 1) {
                maxC = Math.max(maxC, currentC);
                currentC = 0;
            }
        }
        return maxC;
    }
}
