package leetcode_solutions;

import org.junit.Test;

import java.util.*;

public class WordBreak139 {
    //暴力法 TLE
    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            return backTrace(s, wordDict);
        }

        private boolean backTrace(String s, List<String> wordDict) {
            if (s.length() == 0) return true;
            boolean re = false;
            for (int i = 0; i < wordDict.size(); i++) {
                if (s.startsWith(wordDict.get(i))) {
                    re = backTrace(s.substring(wordDict.get(i).length(), s.length()), wordDict);
                }
                //trace back
                if (re) return re;
            }
            return false;
        }
    }

    @Test
    public void test() {
        String s = "abcdefgabcdefg";
        System.out.println(s.indexOf("abcdefg"));
        System.out.println(s.substring("abcdefg".length(), s.length()));
    }

    //字典树
    //TLE for
    //"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
    //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
    class Solution1 {
        public boolean wordBreak(String s, List<String> wordDict) {
            //build trie
            Trie trie = new Trie();
            for (int i = 0; i < wordDict.size(); i++) {
                trie.addWord(wordDict.get(i));
            }

            char[] chs = s.toCharArray();
            boolean re = searchTrie(chs, 0, trie.root, trie.root);
            return re;
        }

        private boolean searchTrie(char[] chs, int l, TrieNode node, TrieNode root) {
            if (l >= chs.length) {
                if (node.isWord)
                    return true;
                else return false;
            }
            TrieNode child = node.children[chs[l] - 'a'];
            if (child != null) {
                if (child.isWord) {
                    return searchTrie(chs, l + 1, child, root) || searchTrie(chs, l + 1, root, root);
                } else {
                    return searchTrie(chs, l + 1, child, root);
                }
            }
            return false;
        }

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isWord = false;
//            String word;
        }

        class Trie {
            TrieNode root;

            Trie() {
                root = new TrieNode();
//                root.word = "";
            }

            public void addWord(String w) {
                TrieNode ws = root;
                for (char c : w.toCharArray()) {
                    if (ws.children[c - 'a'] == null) ws.children[c - 'a'] = new TrieNode();
                    ws = ws.children[c - 'a'];
                }
                ws.isWord = true;
            }
        }
    }

    //recursion & backtrace & Violence
    //时间复杂度：O(n^n)。考虑最坏情况 ss = aaaaaaa 。每一个前缀都在字典中，此时回溯树的复杂度会达到 n^n
    //空间复杂度：O(n) 。回溯树的深度最深达到 n。
    //
    public class Solution2 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return word_Break(s, new HashSet(wordDict), 0);
        }

        public boolean word_Break(String s, Set<String> wordDict, int start) {
            if (start == s.length()) {
                return true;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end)) {
                    return true;
                }
            }
            return false;
        }
    }

    //记忆化回溯 memorized backtrace

    /**
     * 在先前的方法中，我们看到许多函数调用都是冗余的，也就是我们会对相同的字符串调用多次回溯函数。
     * 为了避免这种情况，我们可以使用记忆化的方法，其中一个 memo 数组会被用来保存子问题的结果。
     * 每当访问到已经访问过的后缀串，直接用 memo 数组中的值返回而不需要继续调用函数。
     * 通过记忆化，许多冗余的子问题可以极大被优化，回溯树得到了剪枝，因此极大减小了时间复杂度。
     */
    public class Solution3 {
        public boolean wordBreak(String s, List<String> wordDict) {
            return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
        }

        public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
            if (start == s.length()) {
                return true;
            }
            if (memo[start] != null) {
                return memo[start];
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                    return memo[start] = true;
                }
            }
            return memo[start] = false;
        }
    }


    //bfs
    public class Solution4 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);
            Queue<Integer> queue = new LinkedList<>();
            int[] visited = new int[s.length()];
            queue.add(0);
            while (!queue.isEmpty()) {
                int start = queue.remove();
                if (visited[start] == 0) {
                    for (int end = start + 1; end <= s.length(); end++) {
                        if (wordDictSet.contains(s.substring(start, end))) {
                            queue.add(end);
                            if (end == s.length()) {
                                return true;
                            }
                        }
                    }
                    visited[start] = 1;
                }
            }
            return false;
        }
    }


    //DP
    public class Solution5 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }


    class Solution1ms {
        public boolean wordBreak(String s, List<String> wordDict) {
            return wordSearch(s, 0, wordDict, new HashMap<>());
        }

        private boolean wordSearch(String s, int index, List<String> wordDict, Map<Integer, Boolean> cache) {
            if (index >= s.length()) return true;
            Boolean res;
            if ((res = cache.get(index)) != null) {
                return res;
            }
            for (String word : wordDict) {
                if (s.startsWith(word, index) && wordSearch(s, index + word.length(), wordDict, cache)) {
                    cache.put(index, true);
                    return true;
                }
            }
            cache.put(index, false);
            return false;
        }
    }
}
