package leetcode_solutions;

public class ShortEncodingofWords820 {
    //字典树
    //执行用时:8ms,在所有Java提交中击败了99.59%的用户
    //内存消耗:42.3MB,在所有Java提交中击败了93.33%的用户
    class Solution {
        public int minimumLengthEncoding(String[] words) {
            Trie trie = new Trie();
            for (int i = 0; i < words.length; i++) {
                trie.addWord(words[i]);
            }
            return trie.count;
        }

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int level = -1;
            boolean hasChild = false;
        }

        class Trie {
            TrieNode root;
            int count = 0;

            Trie() {
                root = new TrieNode();
                root.level = 0;
            }

            public void addWord(String w) {
                TrieNode ws = root;
                char[] chs = w.toCharArray();
                for (int i = chs.length - 1; i >= 0; i--) {
                    if (ws.children[chs[i] - 'a'] == null) {
                        ws.children[chs[i] - 'a'] = new TrieNode();
                        ws.hasChild = true;
                    } else if (ws.children[chs[i] - 'a'].level != -1) {
                        count -= (ws.children[chs[i] - 'a'].level + 1);
                        ws.children[chs[i] - 'a'].level = -1;
                    }
                    ws = ws.children[chs[i] - 'a'];
                }

                if (!ws.hasChild) {
                    ws.level = chs.length;
                    count += (ws.level + 1);
                }
            }
        }
    }

    class Solution7ms {
        class TrieNode {
            TrieNode[] nodes = new TrieNode[26];
            boolean isEnd;
            boolean hasChildren;
            int len;

            TrieNode getChildOrCreate(char c) {
                if (nodes[c - 'a'] == null) {
                    nodes[c - 'a'] = new TrieNode();
                    hasChildren = true;
                }
                return nodes[c - 'a'];
            }
        }

        class Trie {
            private int len = 0;
            private int path = 0;

            /** Inserts a word into the trie. */
            public void insert(String word) {
                TrieNode next = root;

                char[] chars = word.toCharArray();
                for (int i = chars.length - 1; i >=0; i-- ) {
                    next = next.getChildOrCreate(chars[i]);
                    if ( next.isEnd ) {
                        next.isEnd = false;
                        len -= next.len;
                        next.len = 0;
                        path--;
                    }
                }
                if ( next.hasChildren )
                    return;
                next.isEnd = true;
                next.len = word.length();
                len += next.len;
                path++;
            }

            TrieNode root = new TrieNode();
        }

        public int minimumLengthEncoding(String[] words) {
            Trie trie = new Trie();
            for(String w : words) {
                trie.insert(w);
            }
            return trie.len + trie.path;
        }
    }
}
