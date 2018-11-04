package leetcode_solutions.arrays;

import com.sun.javafx.image.impl.ByteIndexed;
import org.junit.Test;

import java.util.*;

public class MyCalendarII731 {
    /**
     * Your MyCalendarTwo object will be instantiated and called as such:
     * MyCalendarTwo obj = new MyCalendarTwo();
     * boolean param_1 = obj.book(start,end);
     */

    //my solution:wrong answer cause duplicate key exists
    class MyCalendarTwo {

        private Map<Integer, Integer> map;
        private Map<Integer, Integer> doubleMap;

        public MyCalendarTwo() {
            map = new HashMap<>();
            doubleMap = new HashMap<>();
        }

        public boolean book(int start, int end) {
            Iterator<Map.Entry<Integer, Integer>> doubleIterator = doubleMap.entrySet().iterator();

            while (doubleIterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = doubleIterator.next();
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

            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                if (start < entry.getValue() || end > entry.getKey()) {
                    int left = Math.max(start, entry.getKey());
                    int right = Math.min(end, entry.getValue());
                    if (left < right) {
//                        System.out.println("left:" + left + " right:" + right);
                        doubleMap.put(left, right);
                    }
                }
            }

            map.put(start, end);
            return true;

        }

        public Map<Integer, Integer> getMap() {
            return map;
        }

        public Map<Integer, Integer> getDoubleMap() {
            return doubleMap;
        }
    }

    class SigmentTree {
        int start;
        int end;
        SigmentTree left;
        SigmentTree right;

        public SigmentTree(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //my solution: wrong answer
    class MyCalendarTwo0 {

        private Set<SigmentTree> set;

        private SigmentTree root;

        private List<Integer> list;

        public MyCalendarTwo0() {
            set = new HashSet<>();
            list = new ArrayList<>();
        }

        public boolean book(int start, int end) {

            boolean flag = true;

            //traverse set,check if double area
            Iterator<SigmentTree> iterator = set.iterator();
            while (iterator.hasNext()) {
                SigmentTree sigmentTree = iterator.next();
                if (start < sigmentTree.end || end > sigmentTree.start) {
                    int left = Math.max(start, sigmentTree.start);
                    int right = Math.min(end, sigmentTree.end);
                    if (left < right) {
                        list.add(left);
                        list.add(right);
                        if (bookSigmentTree1(left, right)) {
                            flag = true;
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if (flag) {
                set.add(new SigmentTree(start, end));
                for (int i = 0; i < list.size(); i++) {
                    bookSigmentTree(list.get(i++), list.get(i));
                }
            }
            return flag;
        }

        public boolean bookSigmentTree(int start, int end) {
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


        public boolean bookSigmentTree1(int start, int end) {
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
                    return true;
                } else {
                    return true;
                }
            }
        }

        public Set<SigmentTree> getSet() {
            return set;
        }

        public SigmentTree getRoot() {
            return root;
        }
    }

    //right and fast answer
    class MyCalendarTwo1 {

        List<int[]> calendar;
        List<int[]> overlaps;

        MyCalendarTwo1() {
            calendar = new ArrayList();
            overlaps = new ArrayList();
        }

        public boolean book(int start, int end) {
            for (int[] iv: overlaps) {
                if (iv[0] < end && start < iv[1]) return false;
            }
            for (int[] iv: calendar) {
                if (iv[0] < end && start < iv[1])
                    overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
            }
            calendar.add(new int[]{start, end});
            return true;
        }
    }

    @Test
    public void testMyCalendarTwo() {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        boolean flag = false;
        System.out.print("[null,");
        flag = myCalendarTwo.book(51, 58);//1
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(77, 85);//2
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(35, 44);//3
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(53, 61);//4
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(86, 93);//5
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(55, 61);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(43, 50);//7
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(64, 69);//8
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(76, 82);//9
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(98, 100);//10
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(35, 40);//11
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(25, 32);//12
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(8, 17);//13
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(37, 43);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(53, 60);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(86, 91);//16
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(97, 100);//17
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(37, 43);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(41, 50);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(83, 92);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(66, 75);//21
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(42, 48);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(55, 64);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(37, 46);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(92, 97);//25
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(69, 76);//26
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(85, 94);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(60, 66);//28
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(27, 34);//29
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(36, 44);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(32, 38);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(56, 62);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(93, 99);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(11, 18);//34
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(21, 30);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(81, 89);
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(18, 26);//37
        System.out.print(flag + ",");
        flag = myCalendarTwo.book(81, 90);
        System.out.print(flag + ",");
        System.out.println();

        System.out.println("map:");
        printKeyAndValue(myCalendarTwo.getMap());
        System.out.println("double map:");
        printKeyAndValue(myCalendarTwo.getDoubleMap());

        myCalendarTwo.book(91, 96);


    }

    private void printKeyAndValue(Map<Integer, Integer> map) {
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println("map key:" + entry.getKey() + " value:" + entry.getValue());
        }
    }


    @Test
    public void test() {
        MyCalendarTwo0 myCalendarTwo0 = new MyCalendarTwo0();
        boolean flag = false;
        System.out.print("[null,");
        flag = myCalendarTwo0.book(47, 50);//1
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(1, 10);//2
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(27, 36);//3
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(40, 47);//4
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(20, 27);//5
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(15, 23);
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(10, 18);//7
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(27, 36);//8
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(17, 25);//9
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(8, 17);//10
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(24, 33);//11
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(23, 28);//12
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(21, 27);//13
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(47, 50);
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(14, 21);
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(26, 32);//16
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(16, 21);//17
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(2, 7);
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(24, 33);//19
        System.out.print(flag + ",");
        flag = myCalendarTwo0.book(6, 13);//20
//        System.out.print(flag + ",");
        System.out.println();

        System.out.println("set:");
        Set<SigmentTree> set = myCalendarTwo0.getSet();
        Iterator<SigmentTree> iterator = set.iterator();
        while (iterator.hasNext()) {
            SigmentTree sigmentTree = iterator.next();
            System.out.println("start:" + sigmentTree.start + " end:" + sigmentTree.end);
        }

        System.out.println("double map:");
        printSigmentTree(myCalendarTwo0.getRoot());

        myCalendarTwo0.book(44, 50);
    }

    private void printSigmentTree(SigmentTree sigmentTree) {
        if (null == sigmentTree) {
            return;
        }
        SigmentTree left = sigmentTree.left;
        SigmentTree right = sigmentTree.right;
        printSigmentTree(left);
        printSigmentTree(right);
        if (null != sigmentTree) {
            System.out.println("start:" + sigmentTree.start + " end:" + sigmentTree.end);
        }
    }

}
