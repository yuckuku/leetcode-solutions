package leetcode_solutions;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPossibleFullBinaryTrees894 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public List<TreeNode> allPossibleFBT(int N) {
            if (N % 2 == 0) return new ArrayList<>();
            List<TreeNode> re = new ArrayList<>();
            if (N == 1) {
                re.add(new TreeNode(0));
                return re;
            }
            ;
            //7=1+3+3 7=1+1+5 7=1+5+1
            int t = N - 1;
            int l = 1, r = t - l;
            while (r > 0) {
                List<TreeNode> leftNodes = allPossibleFBT(l);
                List<TreeNode> rightNodes = allPossibleFBT(r);
                for (int i = 0; i < leftNodes.size(); i++) {
                    for (int i1 = 0; i1 < rightNodes.size(); i1++) {
                        TreeNode root = new TreeNode(0);
                        root.left = leftNodes.get(i);
                        root.right = rightNodes.get(i1);
                        re.add(root);
                    }
                }
                l += 2;
                r = t - l;
            }
            return re;
        }
    }

    class Solution1 {
        Map<Integer, List<TreeNode>> map = new HashMap<Integer, List<TreeNode>>();

        public List<TreeNode> allPossibleFBT(int N) {
            if (!map.containsKey(N)) {
                List<TreeNode> ls = new ArrayList<TreeNode>();
                if (N == 1) {
                    ls.add(new TreeNode(0));
                } else {
                    if (N % 2 == 1) {
                        for (int x = 0; x < N; x++) {
                            int y = N - 1 - x;
                            for (TreeNode left : allPossibleFBT(x)) {
                                for (TreeNode right : allPossibleFBT(y)) {
                                    TreeNode root = new TreeNode(0);
                                    root.left = left;
                                    root.right = right;
                                    ls.add(root);
                                }
                            }
                        }
                    }
                }
                map.put(N, ls);
            }
            return map.get(N);
        }
    }

}
