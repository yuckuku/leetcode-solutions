package leetcode_solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
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

    class Solution1 {
        public String getPermutation_2(int n, int k) {
            StringBuilder sb = new StringBuilder();
            //每个阶层的值
            int factor[] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};
            List<Integer> list = new LinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
            list.add(6);
            list.add(7);
            list.add(8);
            list.add(9);
            //从第一位开始算每一位的值
            for (int i = n; i > 0; i--) {
                //这是每一位的值所在的具体位置
                /**
                 * 举个例子：4，9
                 * 具体第一位的排列是
                 * 1xxx、2xxx、3xxx、4xxx
                 * 这四种情况每一种的所得到的情况数都是3!，数目都是一样的
                 * 所以我们可以用k/(n-1)!由此得知首位的值的情况
                 *对应代码也就是这三行
                 * int sv = k / factor[i - 1];
                 * k = k % factor[i - 1];
                 * sv = k > 0 ? sv + 1 : sv;如果取余大于0就对应在前面一个，和分页原理一样
                 *接着我们循环计算第二的情况，前一位的余数就是第二位对应的k值
                 * 以此循环得到所有的位置的值
                 * */
                int sv = k / factor[i - 1];
                k = k % factor[i - 1];
                sv = k > 0 ? sv + 1 : sv;
                if (k == 0) k = factor[i - 1];
                sb.append(list.remove(sv - 1));
            }
            return sb.toString();
        }

    }

    class Solution2 {
        String getPermutation = "";
        int result1 = 0;

        public String getPermutation(int n, int k) {
            if (n == 1) return "1";
            int factor[] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};
            int sv = k / factor[n - 1];
            k = k % factor[n - 1];
            sv = k > 0 ? sv + 1 : sv;
            if (k == 0) k = factor[n - 1];
            backMethod("", n, k, sv);
            return getPermutation;
        }

        public void backMethod(String val, int n, int k, int sv) {
            if (!"".equals(getPermutation)) return;
            if (val.length() == n) {
                result1++;
                if (result1 == k && "".equals(getPermutation)) {
                    getPermutation = new String(val);
                }
                return;
            } else {
                for (int i = sv; i <= n; i++) {
                    if (val.contains(i + "")) {
                        continue;
                    }
                    backMethod(val + i, n, k, 1);
                }
            }
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
