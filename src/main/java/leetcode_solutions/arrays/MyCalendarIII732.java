package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MyCalendarIII732 {
    /**
     * Your MyCalendarThree object will be instantiated and called as such:
     * MyCalendarThree obj = new MyCalendarThree();
     * int param_1 = obj.book(start,end);
     */
    //my solution, seems to be answer, but results on leetcode and my local jvm are not the same when using same testcase
    class MyCalendarThree {

        List<int[]> calendar;

        int max;

        public MyCalendarThree() {
            calendar = new ArrayList();
            max = 0;
        }

        public int book(int start, int end) {
            int k = 1;
            int left = start;
            int right = end;
            for (int[] iv : calendar) {
                if (iv[0] < right && left < iv[1]) {
                    k++;
                    left = Math.max(left, iv[0]);
                    right = Math.min(right, iv[1]);
                }
            }
            calendar.add(new int[]{start, end});
            max = Math.max(max, k);
            return max;
        }
    }

    //use tree map
    class MyCalendarThree0 {

        private Map<Integer, Integer> dmap;

        public MyCalendarThree0() {
            dmap = new TreeMap<>();
        }

        public int book(int start, int end) {
            int cnt = 0, ans = 0;
            dmap.put(start, dmap.getOrDefault(start, 0) + 1);
            dmap.put(end, dmap.getOrDefault(end, 0) - 1);
            for (int key : dmap.keySet()) {
                cnt += dmap.get(key);
                ans = Math.max(ans, cnt);
            }
            return ans;
        }
    }

    //create data structure
    class MyCalendarThree1 {
        SegmentTree segmentTree;

        public MyCalendarThree1() {
            segmentTree = new SegmentTree(0, 1000000000);
        }

        public int book(int start, int end) {
            segmentTree.add(start, end, 1);
            return segmentTree.getMax();
        }
    }

    class SegmentTree {
        TreeNode root;

        public SegmentTree(int left, int right) {
            root = new TreeNode(left, right);
        }

        public void add(int start, int end, int val) {
            TreeNode event = new TreeNode(start, end);
            add(root, event, val);
        }

        private void add(TreeNode root, TreeNode event, int val) {
            if (root == null) {
                return;
            }
            /**
             * If current node's range lies completely in update query range.
             */
            if (root.inside(event)) {
                root.booked += val;
                root.savedres += val;
            }
            /**
             * If current node's range overlaps with update range, follow the same approach as above simple update.
             */
            if (root.intersect(event)) {
                // Recur for left and right children.
                int mid = (root.start + root.end) / 2;
                if (root.left == null) {
                    root.left = new TreeNode(root.start, mid);
                }
                add(root.left, event, val);
                if (root.right == null) {
                    root.right = new TreeNode(mid, root.end);
                }
                add(root.right, event, val);
                // Update current node using results of left and right calls.
                root.savedres = Math.max(root.left.savedres, root.right.savedres) + root.booked;
            }
        }

        public int getMax() {
            return root.savedres;
        }

        /**
         * find maximum for nums[i] (start <= i <= end) is not required.
         * so i did not implement it.
         */
        public int get(int start, int right) {
            return 0;
        }

        class TreeNode {
            int start, end;
            TreeNode left = null, right = null;
            /**
             * How much number is added to this interval(node)
             */
            int booked = 0;
            /**
             * The maximum number in this interval(node).
             */
            int savedres = 0;

            public TreeNode(int s, int t) {
                this.start = s;
                this.end = t;
            }

            public boolean inside(TreeNode b) {
                if (b.start <= start && end <= b.end) {
                    return true;
                }
                return false;
            }

            public boolean intersect(TreeNode b) {
                if (inside(b) || end <= b.start || b.end <= start) {
                    return false;
                }
                return true;
            }
        }
    }

    /**
     * ["MyCalendarThree","book","book","book","book","book","book","book","book","book","book"]
     * [[],[24,40],[43,50],[27,43],[5,21],[30,40],[14,29],[3,19],[3,14],[25,39],[6,19]]
     */
    @Test
    public void test1() {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        myCalendarThree.book(24, 40);//
        myCalendarThree.book(43, 50);//
        myCalendarThree.book(27, 43);//
        myCalendarThree.book(5, 21);//
        myCalendarThree.book(30, 40);//
        myCalendarThree.book(14, 29);//
        myCalendarThree.book(3, 19);//
        myCalendarThree.book(3, 14);//
        myCalendarThree.book(25, 39);//
        int k = myCalendarThree.book(6, 19);//
        System.out.println("k:" + k);
    }

    /**
     * ["MyCalendarThree","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book","book"]
     * [[],[47,50],[1,10],[27,36],[40,47],[20,27],[15,23],[10,18],[27,36],[17,25],[8,17],[24,33],[23,28],[21,27],[47,50],[14,21],[26,32],[16,21],[2,7],[24,33],[6,13],[44,50],[33,39],[30,36],[6,15],[21,27],[49,50],[38,45],[4,12],[46,50],[13,21]]
     */
    @Test
    public void test0() {
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        myCalendarThree.book(47, 50);//1
        myCalendarThree.book(1, 10);//1
        myCalendarThree.book(27, 36);//1
        myCalendarThree.book(40, 47);//1
        myCalendarThree.book(20, 27);//1
        myCalendarThree.book(15, 23);//2
        myCalendarThree.book(10, 18);//2
        myCalendarThree.book(27, 36);//2
        myCalendarThree.book(17, 25);//3
        myCalendarThree.book(8, 17);//3
        myCalendarThree.book(24, 33);//3
        myCalendarThree.book(23, 28);//4
        myCalendarThree.book(21, 27);
    }
}
