package leetcode_solutions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImplementTriePrefixTree208 {
    //执行用时:356ms,在所有java提交中击败了6.43%的用户内存消耗:68.7MB,在所有java提交中击败了39.71%的用户
    class Trie {

        private final Set<String> set = new HashSet<>();

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            set.add(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return set.contains(word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Iterator<String> ite = set.iterator();
            while (ite.hasNext()) {
                if (ite.next().startsWith(prefix)) return true;
            }
            return false;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
//执行用时:38ms,在所有java提交中击败了99.64%的用户;内存消耗:52.9MB,在所有java提交中击败了90.07%的用户
    class Trie1 {

        private final TrieNode root = new TrieNode();

        /**
         * Initialize your data structure here.
         */
        public Trie1() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            char[] chs = word.toCharArray();
            TrieNode tmp = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (tmp.next[index] == null) {
                    //如果没有，加入
                    tmp.next[index] = new TrieNode();
                }
                tmp = tmp.next[index];
            }
            tmp.nodeState = 1;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] chs = word.toCharArray();
            TrieNode tmp = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (tmp.next[index] == null) {
                    return false;
                }
                tmp = tmp.next[index];
            }
            return tmp.nodeState == 1 ? true : false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] chs = prefix.toCharArray();
            TrieNode tmp = root;
            for (int i = 0; i < chs.length; i++) {
                int index = chs[i] - 'a';
                if (tmp.next[index] == null) {
                    return false;
                }
                tmp = tmp.next[index];
            }
            return tmp == null ? false : true;
        }

        class TrieNode {
            /**
             * 指向各个子树的指针,存储26个字母[a-z]
             */
            TrieNode[] next = new TrieNode[26];
            /**
             * 当前TrieNode状态 ,默认 0 , 1表示从根节点到当前节点的路径表示一个词
             */
            int nodeState = 0;

            TrieNode() {
                next = new TrieNode[26];
                nodeState = 0;
            }
        }
    }


    class Trie32ms {
        private TrieNode root;

        public Trie32ms() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode node = root;
            int index = 0;
            while (index < word.length()) {
                if (node.children[word.charAt(index) - 'a'] == null) {
                    node.children[word.charAt(index) - 'a'] = new TrieNode();
                }
                node = node.children[word.charAt(index) - 'a'];
                index++;

            }
            node.item = word;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = root;
            int index = 0;
            while (index < word.length()) {
                if (node.children[word.charAt(index) - 'a'] == null) {
                    return false;
                }
                node = node.children[word.charAt(index) - 'a'];
                index++;

            }
            return node.item.equals(word);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            int index = 0;
            while (index < prefix.length()) {
                if (node.children[prefix.charAt(index) - 'a'] == null) {
                    return false;
                }
                node = node.children[prefix.charAt(index) - 'a'];
                index++;

            }
            return true;
        }

        class TrieNode {
            public TrieNode[] children = new TrieNode[26];
            public String item = "";

            public TrieNode() {

            }
        }
    }

    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

}
