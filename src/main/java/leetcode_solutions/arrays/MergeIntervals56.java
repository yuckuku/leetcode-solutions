package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: L'Nan
 * Date: 2019/5/16 14:51
 * Description:
 */
public class MergeIntervals56 {

    class TreeNode {
        protected int leftV;
        protected int rightV;
        protected TreeNode leftNode;
        protected TreeNode rightNode;

        public TreeNode(int leftV, int rightV) {
            this.leftV = leftV;
            this.rightV = rightV;
        }
    }

    /**
     *
     */
    class Solution1 {
        public int[][] merge(int[][] intervals) {
            if (intervals.length <= 1)
                return intervals;
            TreeNode root = new TreeNode(intervals[0][0], intervals[0][1]);
            for (int i = 1; i < intervals.length; i++) {
                add(root, intervals[i]);
            }
            List<TreeNode> list = new ArrayList<>();
            order(root, list);
            int count = list.size();
            int[][] res = new int[count][2];
            for (int i = 0; i < count; i++) {
                int[] temp = new int[]{list.get(i).leftV, list.get(i).rightV};
                res[i] = temp;
            }

            return res;
        }

        private void add(TreeNode root, int[] s) {

            if (root.leftV <= s[0] && root.rightV >= s[1]) {//inside
                return;
            }
            if (root.rightV < s[0]) {//right
                if (null != root.rightNode) add(root.rightNode, s);
                else {
                    TreeNode right = new TreeNode(s[0], s[1]);
                    root.rightNode = right;
                }
                return;
            }
            if (root.leftV > s[1]) {//left
                if (null != root.leftNode) add(root.leftNode, s);
                else {
                    TreeNode left = new TreeNode(s[0], s[1]);
                    root.leftNode = left;
                }
                return;
            }
            if (root.leftV > s[0] && root.leftV <= s[1]) {//left cross
                root.leftV = s[0];
                leftAdjust(root);
            }
            if (root.rightV < s[1] && root.rightV >= s[0]) {//right cross
                root.rightV = s[1];
                rightAdjust(root);
            }
        }

        private void order(TreeNode root, List<TreeNode> list) {
            if (null != root) {
                list.add(root);
                order(root.leftNode, list);
                order(root.rightNode, list);
            }
        }

        private void leftAdjust(TreeNode root) {
            //find left biggest
            if (root.leftNode == null) {
                return;
            }
            TreeNode big = root.leftNode;
            TreeNode pre = root;
            while (null != big.rightNode) {
                pre = big;
                big = big.rightNode;
            }
            if (big.rightV >= root.leftV) {//adjust
                root.leftV = Math.min(big.leftV, root.leftV);
                if (root.leftNode == big) {
                    root.leftNode = big.leftNode;
                    big.leftNode = null;
                } else {
                    pre.rightNode = big.leftNode;
                    big.leftNode = null;
                }
                leftAdjust(root);
            }
        }

        private void rightAdjust(TreeNode root) {
            //find right biggest
            if (root.rightNode == null) {
                return;
            }
            TreeNode big = root.rightNode;
            TreeNode pre = root;
            while (null != big.leftNode) {
                pre = big;
                big = big.leftNode;
            }
            if (big.leftV <= root.rightV) {//adjust
                System.out.println("rightV:" + big.rightV + " " + root.rightV);
                root.rightV = Math.max(big.rightV, root.rightV);
                if (root == pre) {
                    root.rightNode = big.rightNode;
                    big.rightNode = null;
                } else {
                    pre.leftNode = big.rightNode;
                    big.rightNode = null;
                }
                rightAdjust(root);
            }
        }
    }

    /**
     * wrong answer
     */
    class Solution {
        public int[][] merge(int[][] intervals) {
            int[][] re = new int[intervals.length][2];
            int j = 0;
            int i = 0;
            while (i < intervals.length) {
                int[] temp = new int[2];
                int left = intervals[i][0];
                int right = intervals[i][1];
                while (i + 1 < intervals.length && intervals[i + 1][0] <= right) {
                    right = intervals[++i][1];
                }
                temp[0] = left;
                temp[1] = right;
                re[j++] = temp;
                i++;
            }

            int[][] res = new int[j][2];
            System.arraycopy(re, 0, res, 0, j);

            return res;
        }
    }

    class SolutionOnLeetcode1 {
        public int[][] merge(int[][] intervals) {
            int rows = intervals.length;
            if (rows == 0) {
                return intervals;
            }
            int count = rows;
            for (int i = 0; i < rows - 1; i++) {
                for (int j = i + 1; j < rows; j++) {
                    if (canMerge(intervals, i, j)) {
                        count--;
                        break;
                    }
                }
            }
            int[][] res = new int[count][2];
            int index = 0;
            for (int[] row : intervals) {
                if (row != null) {
                    res[index] = row;
                    index++;
                }
            }
            return res;
        }

        public boolean canMerge(int[][] intervals, int first, int second) {
            if (!((intervals[first][0] < intervals[second][0] && intervals[first][1] < intervals[second][0])
                    || (intervals[second][0] < intervals[first][0] && intervals[second][1] < intervals[first][0]))) {
                intervals[second] = new int[]{Math.min(intervals[first][0], intervals[second][0]),
                        Math.max(intervals[first][1], intervals[second][1])};
                intervals[first] = null;
                return true;
            }
            return false;
        }
    }

    @Test
    public void test() {
        int[][] intervals = new int[][]{
                {321, 336}, {421, 427}, {170, 184}, {6, 21}, {178, 193}, {412, 417}, {136, 141}, {244, 247}, {0, 3}, {172, 175}, {223, 234}, {368, 376}, {180, 197}, {101, 108}, {442, 460}, {213, 216}, {153, 159}, {369, 385}, {481, 488}, {411, 430}, {363, 378}, {197, 216}, {453, 454}, {463, 476}, {256, 271}, {336, 355}, {186, 203}, {47, 65}, {254, 254}, {458, 474}, {238, 249}, {311, 315}, {10, 22}, {272, 275}, {259, 262}, {354, 356}, {211, 222}, {474, 478}, {492, 509}, {117, 117}, {424, 430}, {79, 81}, {363, 370}, {180, 197}, {479, 489}, {165, 183}, {14, 30}, {314, 318}, {8, 14}, {367, 386}, {121, 122}, {242, 246}, {125, 141}, {348, 357}, {5, 23}, {70, 71}, {124, 133}, {243, 250}, {128, 143}, {456, 464}, {266, 279}, {178, 178}, {449, 461}, {156, 165}, {430, 433}, {428, 439}, {96, 98}, {88, 89}, {90, 106}, {191, 206}, {106, 119}, {376, 386}, {114, 125}, {69, 72}, {247, 264}, {203, 208}, {350, 363}, {145, 156}, {225, 226}, {460, 467}, {176, 177}, {410, 414}, {263, 281}, {346, 360}, {287, 296}, {301, 314}, {354, 364}, {124, 132}, {1, 6}, {211, 211}, {275, 281}, {465, 470}, {499, 501}, {129, 145}, {115, 124}, {83, 94}, {182, 188}, {416, 434}, {344, 358}, {4, 15}, {355, 360}, {377, 386}, {187, 200}, {29, 30}};
        Solution1 s = new Solution1();

        int[][] re = s.merge(intervals);
        for (int[] ints :
                re) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
