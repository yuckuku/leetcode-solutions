package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence60 {
    class NotSolution {
        //dfs
        private List<String> printAll(List<Integer> nums, List<String> lists) {
            if (nums.isEmpty()) return lists;

            List<String> re = new ArrayList<>(nums.size() * lists.size());


            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < nums.size(); j++) {
                    re.add(lists.get(i) + nums.get(j));
                }
            }

            return re;
        }
    }

    //回溯+剪枝
    public class Solution {

        public String getPermutation(int n, int k) {
            int[] nums = new int[n];
            boolean[] used = new boolean[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
                used[i] = false;
            }
            List<String> pre = new ArrayList<>();
            return dfs(nums, used, n, k, 0, pre);
        }

        //阶乘
        private int factorial(int n) {
            // 这种编码方式包括了 0 的阶乘是 1 这种情况
            int res = 1;
            while (n > 0) {
                res *= n;
                n -= 1;
            }
            return res;
        }

        private String dfs(int[] nums, boolean[] used, int n, int k, int depth, List<String> pre) {
            if (depth == n) {
                StringBuilder sb = new StringBuilder();
                for (String c : pre) {
                    sb.append(c);
                }
                return sb.toString();
            }
            int ps = factorial(n - 1 - depth);
            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    continue;
                }
                if (ps < k) {
                    k -= ps;
                    continue;
                }
                pre.add(nums[i] + "");
                used[i] = true;
                return dfs(nums, used, n, k, depth + 1, pre);
            }
            // 如果参数正确的话，代码不会走到这里
            throw new RuntimeException("参数错误");
        }
    }


    class Solution1ms {
        public String getPermutation(int n, int k) {
            if (n == 1) return "1";
            boolean[] tags = new boolean[10];
            int count = 0;
            StringBuilder sb = new StringBuilder();

            while (n-- > 0) {
                int next = getNext(n);//获取阶乘
                int i = (k - count - 1) / next;//当前需要减掉的树枝的数目
                count += i * next;//已经减掉的树枝的路径总数
                for (int j = 1; j <= 9; j++) {
                    if (!tags[j] && i-- <= 0) {
                        tags[j] = true;
                        sb.append(j);
                        break;
                    }
                }
            }
            return sb.toString();
        }


        private int getNext(int k) {
            int sum = 1;
            while (k > 1) {
                sum *= k;
                k--;
            }
            return sum;
        }
    }

    @Test
    public void test() {

        Solution1ms solution1ms = new Solution1ms();
        String re = solution1ms.getPermutation(4, 9);
        System.out.println(re);

        // System.out.println(getPermutation(3, 3));
        // System.out.println(getPermutation(2, 1));
        // 少了个。
        for (int i = 1; i <= 4 * 3 * 2; i++) {
            System.out.println(solution1ms.getPermutation(4, i));
        }
    }

}
