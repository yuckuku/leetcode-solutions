package leetcode_solutions;

import java.util.Arrays;

public class NumberofLongestIncreasingSubsequence673 {
    //DP

    /**
     * 假设对于以 nums[i] 结尾的序列，我们知道最长序列的长度 length[i]，以及具有该长度的序列的 count[i]。
     * 对于每一个 i<j 和一个 A[i]<A[j]，我们可以将一个 A[j] 附加到以 A[i] 结尾的最长子序列上。
     * 如果这些序列比 length[j] 长，那么我们就知道我们有count[i] 个长度为 length 的序列。
     * 如果这些序列的长度与 length[j] 相等，那么我们就知道现在有 count[i] 个额外的序列（即 count[j]+=count[i]）。
     */
    //执行用时:10ms,在所有Java提交中击败了96.56%的用户
    //内存消耗:38.8MB,在所有Java提交中击败了73.21%的用户
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            int N = nums.length;
            if (N <= 1) return N;
            int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
            int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
            Arrays.fill(counts, 1);

            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < j; ++i)
                    if (nums[i] < nums[j]) {
                        if (lengths[i] >= lengths[j]) {
                            lengths[j] = lengths[i] + 1;
                            counts[j] = counts[i];
                        } else if (lengths[i] + 1 == lengths[j]) {
                            counts[j] += counts[i];
                        }
                    }
            }

            int longest = 0, ans = 0;
            for (int length : lengths) {
                longest = Math.max(longest, length);
            }
            for (int i = 0; i < N; ++i) {
                if (lengths[i] == longest) {
                    ans += counts[i];
                }
            }
            return ans;
        }
        //时间复杂度：O(N^2)。其中NN是nums的长度。有两个for循环是O(1)。空间复杂度：O(N)，lengths和counts所用的空间
    }

    //Fenwick 树(线段树)

    /**
     * 算法：
     * 本文将更详细地讨论如何实现线段树。在这种方法中，我们将尝试一种不使用延迟传播的线段树变体。
     * <p>
     * 首先，让我们称递增子序列的最长长度以及该区间中此类子序列的数量为区间的 “值”。
     * 每个节点都知道它正在考虑的 nums 值的间隔 [node.range_left，node.range_right] 和 node.val
     * 它包含有关区间的信息。节点还具有 node.left 和 node.right 子级，表示区间节点所考虑左右两部分。这些子节点根据需要创建。
     * 现在，query(node, key) 将告诉我们由 node 指定的区间值，除非我们排除 key 以上的任何内容 。当 key 超出节点指定的区间时 ，我们返回答案。否则，我们将把区间划分为两个，查询两个区间，然后merge 结果。
     * merge(v1, v2) 的作用是什么？假设两个节点指定相邻的区间，并具有相应的值 v1 = node1.val, v2 = node2.val。v=merge(v1, v2) 应该是什么？如果一个节点中有较长的子序列，那么 v 就是它。如果两个节点都有长度相等的最长子序列，那么我们应该计算两个节点中的子序列。请注意，我们不必考虑产生较大子序列的情况，因为这些子序列是由 insert 处理的。
     * insert(node, key, val) 是什么功能？我们重复地将 key 插入节点指定的区间（可能是一个点），插入之后，该节点的值可能会更改，因此我们再次将这些值合并在一起。
     * 最后，在我们的主算法中，对于 num in nums，我们 query 值 length、 num 以下区间的 count ，我们将计算 count 长度为 length + 1 的其他序列。然后我们用这些更新我们的树。
     */
    //执行用时:9ms,在所有Java提交中击败了99.71%的用户
    //内存消耗:40MB,在所有Java提交中击败了60.71%的用户
    class Solution1 {
        public Value merge(Value v1, Value v2) {
            if (v1.length == v2.length) {
                if (v1.length == 0) return new Value(0, 1);
                return new Value(v1.length, v1.count + v2.count);
            }
            return v1.length > v2.length ? v1 : v2;
        }

        public void insert(Node node, int key, Value val) {
            if (node.range_left == node.range_right) {
                node.val = merge(val, node.val);
                return;
            } else if (key <= node.getRangeMid()) {
                insert(node.getLeft(), key, val);
            } else {
                insert(node.getRight(), key, val);
            }
            node.val = merge(node.getLeft().val, node.getRight().val);
        }

        public Value query(Node node, int key) {
            if (node.range_right <= key) return node.val;
            else if (node.range_left > key) return new Value(0, 1);
            else return merge(query(node.getLeft(), key), query(node.getRight(), key));
        }

        public int findNumberOfLIS(int[] nums) {
            if (nums.length == 0) return 0;
            int min = nums[0], max = nums[0];
            for (int num : nums) {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            Node root = new Node(min, max);
            for (int num : nums) {
                Value v = query(root, num - 1);
                insert(root, num, new Value(v.length + 1, v.count));
            }
            return root.val.count;
        }
    }

    class Node {
        int range_left, range_right;
        Node left, right;
        Value val;

        public Node(int start, int end) {
            range_left = start;
            range_right = end;
            left = null;
            right = null;
            val = new Value(0, 1);
        }

        public int getRangeMid() {
            return range_left + (range_right - range_left) / 2;
        }

        public Node getLeft() {
            if (left == null) left = new Node(range_left, getRangeMid());
            return left;
        }

        public Node getRight() {
            if (right == null) right = new Node(getRangeMid() + 1, range_right);
            return right;
        }
    }

    class Value {
        int length;
        int count;

        public Value(int len, int ct) {
            length = len;
            count = ct;
        }
    }

    class Solution8ms {
        public int findNumberOfLIS(int[] nums) {

            if (nums.length == 0) {
                return 0;
            }

            int[] dp = new int[nums.length];
            int[] combination = new int[nums.length];

            Arrays.fill(dp, 1);
            Arrays.fill(combination, 1);

            int max = 1, res = 0;

            for (int i = 1; i < dp.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) { //如果+1长于当前LIS 则组合数不变
                            dp[i] = dp[j] + 1;
                            combination[i] = combination[j];
                        } else if (dp[j] + 1 == dp[i]) { //如果+1等于当前LIS 则说明找到了新组合
                            combination[i] += combination[j];
                        }
                    }
                }
                max = Math.max(max, dp[i]);
            }

            for (int i = 0; i < nums.length; i++)
                if (dp[i] == max) res += combination[i];

            return res;
        }
    }

}
