package leetcode_solutions;

import org.junit.Test;

public class OptimalDivision553 {
    class Solution {
        public String optimalDivision(int[] nums) {
            int len = nums.length;
            StringBuilder sb = new StringBuilder();
            switch (len) {
                case 1:
                    sb.append(nums[0]);
                    break;
                case 2:
                    sb.append(nums[0]).append("/").append(nums[1]);
                    break;
                case 3:
                    sb.append(nums[0]).append("/(").append(nums[1]).append("/").append(nums[2]).append(")");
                    break;
//                case 4://"1000/(100/10/2)"
//                      sb.append(nums[0]).append("/(").append(nums[1]).append("/").append(nums[2]).append("/").append(nums[3]).append(")");
//                    break;
//                case 5:
//
//                    break;
//                case 6:
//
//                    break;
//                case 7:
//
//                    break;
//                case 8:
//
//                    break;
//                case 9:
//
//                    break;
//                case 10:
//
//                    break;
                default:
                    sb.append(nums[0]).append("/(");
                    for (int i = 1; i < nums.length - 1; i++) {
                        sb.append(nums[i]).append("/");
                    }
                    sb.append(nums[nums.length - 1]).append(")");
                    break;
            }
            return sb.toString();
        }
    }

    @Test
    public void test(){
        int[] nums =new int[]{1000,100,10,2};
        Solution solution=new Solution();
        String re=solution.optimalDivision(nums);
        System.out.println(re);
    }
}
