package leetcode_solutions.arrays;

import java.util.*;

public class WordLadderII126 {
    class Solution {
        private List<List<String>> listList = new ArrayList<>();
        private List<String> list = new ArrayList<>();
        private boolean[][] graph;
        private int[] d;
        private final int INF = 1000000000;
        private int[] countInq;
        private boolean[] inq;
        private int size;
        private ArrayList<HashSet<Integer>> pre;
        private List<Integer> tempPath = new ArrayList<>();
        private int start = 0, end = 0;

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            HashSet<String> hashSet = new HashSet<>();
            hashSet.addAll(wordList);
            hashSet.add(beginWord);
            if (!hashSet.contains(endWord)) {
                return listList;
            }
            int index = 0;
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
            pre = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                pre.add(new HashSet<>());
            }
            spfa(start);
            dfs(end);
            return listList;
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
                    if (graph[u][v]) {
                        if (d[u] + 1 < d[v]) {
                            pre.get(v).clear();
                            pre.get(v).add(u);
                            d[v] = d[u] + 1;
                            if (!inq[v]) {
                                queue.add(v);
                                countInq[v]++;
                                inq[v] = true;
                                if (countInq[v] > size - 1) {
                                    return false;
                                }
                            }
                        } else if (d[u] + 1 == d[v]) {
                            pre.get(v).add(u);
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
            }
            return true;
        }

        private void dfs(int nowVisit) {
            tempPath.add(nowVisit);
            if (nowVisit == start) {
                List<String> path = new ArrayList<>();
                for (int i = tempPath.size() - 1; i >= 0; i--) {
                    path.add(list.get(tempPath.get(i)));
                }
                listList.add(path);
                tempPath.remove(tempPath.size() - 1);
                return;
            }
            for (Integer integer : pre.get(nowVisit)) {
                dfs(integer);
            }
            tempPath.remove(tempPath.size() - 1);
        }
    }

    class SolutionOnLeetcode1 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            List<List<String>> res = new ArrayList<>();
            if (wordList == null || wordList.size() == 0) return res;
            Set<String> dict = new HashSet<>(wordList);
            if (!dict.contains(endWord)) return res;
            Set<String> beginSet = new HashSet<>();
            beginSet.add(beginWord);
            Set<String> endSet = new HashSet<>();
            endSet.add(endWord);
            Map<String, List<String>> map = new HashMap<>();
            bfs(map, beginSet, endSet, dict, false);

            List<String> tempList = new ArrayList<>();
            tempList.add(beginWord);
            dfs(map, res, tempList, beginWord, endWord);
            return res;
        }

        public void bfs(Map<String, List<String>> map, Set<String> beginSet, Set<String> endSet, Set<String> dict, boolean reverse) {
//双端查找的时候，若是有任意一端无法再继续扩展下去，说明不存在路径
            if (beginSet.size() == 0) return;
// 将已遍历过的单词从字典中移除
            boolean findMin = false;
            dict.removeAll(beginSet);
// 记录下一层遍历的结果
            HashSet<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] arr = word.toCharArray(); //转成char数组，后面大量循环比较的操作会比较快
                for (int i = 0; i < arr.length; i++) {
                    char temp = arr[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (temp == ch) continue;
                        arr[i] = ch;
                        String nextWord = new String(arr);
                        if (dict.contains(nextWord)) {
// 如果下一层的结点同样存在于endSet中，那么说明找到了一个最短的路径
                            if (endSet.contains(nextWord)) findMin = true;
                            else nextSet.add(nextWord);
                            String key = reverse ? nextWord : word;
                            String value = reverse ? word : nextWord;
                            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
                            map.get(key).add(value);
                        }
                    }
// 最后记得复原当前的字符
                    arr[i] = temp;
                }
            }
            if (!findMin) {
                if (nextSet.size() > endSet.size()) bfs(map, endSet, nextSet, dict, !reverse);
                else bfs(map, nextSet, endSet, dict, reverse);
            }
        }

        public void dfs(Map<String, List<String>> map, List<List<String>> res, List<String> tempList, String beginWord, String endWord) {
            if (beginWord.equals(endWord)) {
                res.add(new ArrayList<>(tempList));
                return;
            }
            if (!map.containsKey(beginWord)) return;
            for (String word : map.get(beginWord)) {
                tempList.add(word);
                dfs(map, res, tempList, word, endWord);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
