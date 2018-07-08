package leetcode_solutions.arrays;

public class ProductofArrayExceptSelf238 {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length, right = 1;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; --i) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public int[] productExceptSelf0(int[] nums) {
        int zeroCount = 0;
        for (int n : nums)
            if (n == 0)
                zeroCount++;
        // 有两个或者两个以上的元素是0，那么数组设为全零返回
        if (zeroCount > 1) {
            for (int i = 0; i < nums.length; i++)
                nums[i] = 0;
        } else if (zeroCount == 0) {
            // 如果没有0，则计算所有的乘积
            int product = 1;
            for (int n : nums)
                product *= n;
            // 每个数组元素置为product / 该位置值即可
            for (int i = 0; i < nums.length; i++)
                nums[i] = product / nums[i];
        } else {
            // 如果元素中有1个0
            int product = 1;
            // 跳过那个元素，计算所有的乘积
            for (int n : nums)
                if (n != 0)
                    product *= n;
            // 元素为0的位置置为product，其他置为0
            for (int i = 0; i < nums.length; i++)
                if (nums[i] == 0)
                    nums[i] = product;
                else
                    nums[i] = 0;
        }
        return nums;
    }
}
