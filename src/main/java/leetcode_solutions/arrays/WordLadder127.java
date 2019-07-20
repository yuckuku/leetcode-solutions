package leetcode_solutions.arrays;

import org.junit.Test;

import java.util.*;

public class WordLadder127 {
    class Solution {
        private boolean[][] graph;
        private int[] d;
        private final int INF = 1000000000;
        private int[] countInq;
        private boolean[] inq;
        private int size;

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            HashSet<String> hashSet = new HashSet<>();
            hashSet.addAll(wordList);
            hashSet.add(beginWord);
            if (!hashSet.contains(endWord)) {
                return 0;
            }
            List<String> list = new ArrayList<>();
            int index = 0;
            int start = 0, end = 0;
            for (String s : hashSet) {
                list.add(s);
                if (s.equals(beginWord)) {
                    start = index;
                }
                if (s.equals(endWord)) {
                    end = index;
                }
                index++;
            }
            size = list.size();
            graph = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (hashPath(list.get(i), list.get(j))) {
                        graph[i][j] = graph[j][i] = true;
                    }
                }
            }
            d = new int[size];
            Arrays.fill(d, INF);
            countInq = new int[size];
            Arrays.fill(countInq, 0);
            inq = new boolean[size];
            spfa(start);
            if (INF == d[end]) {
                return 0;
            }
            return d[end] + 1;
        }

        private boolean hashPath(String s1, String s2) {
            int count = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    count++;
                }
            }
            if (1 == count) {
                return true;
            }
            return false;
        }

        private boolean spfa(int s) {
            d[s] = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            countInq[s]++;
            inq[s] = true;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                inq[u] = false;
                for (int v = 0; v < size; v++) {
                    if (graph[u][v] && d[u] + 1 < d[v]) {
                        d[v] = d[u] + 1;
                        if (!inq[v]) {
                            queue.add(v);
                            countInq[v]++;
                            inq[v] = true;
                            if (countInq[v] > size - 1) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    class SolutionOnLeetcode1 {
        //递归
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || wordList.size() == 0)
                return 0;
            //hashset的好处：去重也完成了
            //开始端
            HashSet<String> start = new HashSet<>();
            //结束端
            HashSet<String> end = new HashSet<>();
            //所有字符串的字典
            HashSet<String> dic = new HashSet<>(wordList);
            start.add(beginWord);
            end.add(endWord);
            if (!dic.contains(endWord))
                return 0;
            //经历过上面的一系列判定，到这里的时候，若是有路径，则最小是2，所以以2开始
            return bfs(start, end, dic, 2);
        }

        public int bfs(HashSet<String> begin, HashSet<String> end, HashSet<String> dic, int l) {
            //双端查找的时候，若是有任意一段出现了“断裂”，也就是说明不存在能够连上的路径，则直接返回0
            if (begin.size() == 0)
                return 0;
            if (begin.size() > end.size()) {//双端查找，为了优化时间，永远用少的去找多的，比如开始的时候塞进了1000个，而结尾只有3个，则肯定是从少的那一端开始走比较好
                return bfs(end, begin, dic, l);
            }
            //BFS的标记行为，即使用过的不重复使用
            dic.removeAll(begin);
            //收集下一层临近点
            HashSet<String> next = new HashSet<>();
            for (String s : begin) {
                char[] arr = s.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char tmp = arr[i];
                    //变化
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (tmp == c)
                            continue;
                        arr[i] = c;
                        String nstr = new String(arr);
                        //只改变一个字母，如果字典里有，则可以转换
                        if (dic.contains(nstr)) {
                            //如果恰好end里也有，则一次转换成功
                            if (end.contains(nstr))
                                return l;
                            else
                                next.add(nstr);
                        }
                    }//第一个字母全换了一遍还是没有，换下一个字母
                    //复原
                    arr[i] = tmp;
                }
            }
            return bfs(next, end, dic, l + 1);
        }
    }

    @Test
    public void test() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        Solution s = new Solution();
        int re = s.ladderLength(beginWord, endWord, wordList);
        System.out.println(re);
    }
}
