package leetcode_solutions.str;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LexicographicalNumbers386 {
    class Solution {
        public List<Integer> lexicalOrder(int n) {
            return null;
        }
    }

    class Solution1 {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                if (i <= n) {
                    list.add(i);
                    add(list, n, i);
                } else {
                    break;
                }
            }
            return list;
        }

        private void add(List<Integer> list, int n, int startNum) {
            startNum *= 10;
            for (int i = 0; i < 10; i++, startNum++) {
                if (startNum <= n) {
                    list.add(startNum);
                    add(list, n, startNum);
                } else {
                    return;
                }
            }
        }
    }

    //树
    class Solution2 {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> res = new ArrayList<Integer>();
            Stack<Integer> tree = new Stack<Integer>();
            if (n < 10) {
                for (int i = n; i > 0; i--) tree.push(i);
            } else for (int i = 9; i > 0; i--) tree.push(i);
            int t, m;
            while (!tree.empty()) {
                t = tree.peek();
                tree.pop();
                res.add(t);
                if (t * 10 > n) continue;
                else {
                    m = n - t * 10;
                    if (m > 9) m = 9;
                }
                for (int i = m; i >= 0; i--)
                    if (t * 10 + i <= n)
                        tree.push(t * 10 + i);
            }
            return res;
        }
    }

    class SolutionOnLeetcode1 {
        public List<Integer> lexicalOrder(int n) {
            if (n < 1) {
                return new ArrayList();
            }
            List<Integer> res = new ArrayList(n);
            lexicalOrderHelper(res, 0, n);
            return res;
        }

        private void lexicalOrderHelper(List<Integer> list, int start, int n) {
            for (int i = 0; i < 10; i++) {
                int cur = start + i;
                if (cur == 0) {
                    continue;
                } else if (cur <= n) {
                    list.add(cur);
                    lexicalOrderHelper(list, cur * 10, n);
                } else {
                    return;
                }
            }
            return;
        }
    }


    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        //133 [1,10,100,101,102,103,104,105,106,107,108,109,11,110,111,112,113,114,115,116,117,118,119,12,120,121,122,123,124,125,126,127,128,129,13,130,131,132,133,14,15,16,17,18,19,2,20,21,22,23,24,25,26,27,28,29,3,30,31,32,33,34,35,36,37,38,39,4,40,41,42,43,44,45,46,47,48,49,5,50,51,52,53,54,55,56,57,58,59,6,60,61,62,63,64,65,66,67,68,69,7,70,71,72,73,74,75,76,77,78,79,8,80,81,82,83,84,85,86,87,88,89,9,90,91,92,93,94,95,96,97,98,99]
    }
}
