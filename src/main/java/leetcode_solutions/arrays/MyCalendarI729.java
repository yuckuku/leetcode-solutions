package leetcode_solutions.arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyCalendarI729 {
    /**
     * Your MyCalendar object will be instantiated and called as such:
     * MyCalendar obj = new MyCalendar();
     * boolean param_1 = obj.book(start,end);
     */
    //my solution:use one map
    //proved to be right answer
    class MyCalendar {

        private Map<Integer, Integer> map;

        public MyCalendar() {
            map = new HashMap<>();
        }

        public boolean book(int start, int end) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                if (start >= entry.getKey() && start < entry.getValue()) {
                    return false;
                }
                if (end > entry.getKey() && end <= entry.getValue()) {
                    return false;
                }
                if (start < entry.getKey() && end > entry.getValue()) {
                    return false;
                }
            }
            map.put(start, end);
            return true;
        }
    }

    //use tree structure
    class MyCalendar0 {
        private SigmentTree root;

        private class SigmentTree {
            int start;
            int end;
            SigmentTree left;
            SigmentTree right;

            public SigmentTree(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public MyCalendar0() {
        }

        public boolean book(int start, int end) {
            if (root == null) {
                root = new SigmentTree(start, end);
                return true;
            } else {
                SigmentTree cur = root;
                SigmentTree pre = null;
                boolean isLeft = false;
                while (cur != null) {
                    pre = cur;
                    if (cur.start >= end) {
                        cur = cur.left;
                        isLeft = true;
                    } else if (cur.end <= start) {
                        cur = cur.right;
                        isLeft = false;
                    } else {
                        return false;
                    }
                }
                if (isLeft) {
                    pre.left = new SigmentTree(start, end);
                    return true;
                } else {
                    pre.right = new SigmentTree(start, end);
                    return true;
                }
            }
        }
    }


}
