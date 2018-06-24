package leetcode_solutions;

import java.util.Arrays;

public class LongestWordinDictionary720 {
    public String longestWord(String[] words) {
        String re = "";
        for (String str :
                words) {
            if (isBuiltByOneCharacterAtaTime(str, words)) {
                if (str.length() > re.length() || (str.length() == re.length() && str.compareTo(re) < 0)) {
                    re = str;
                }
            }
        }
        return re;
    }

    private boolean isBuiltByOneCharacterAtaTime(String str, String[] words) {
        boolean flag = true;
        while (!str.equals("")) {
            flag = flag & Arrays.asList(words).contains(str);
            str = str.substring(0, str.length() - 1);
        }
        return flag;
    }

    //better solution
    //Trie + DFS traversal trie
    //Time: O(n * wL); Space : O(n * wL)

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode();
            root.word = "";
        }
        public void addWord(String w) {
            TrieNode ws = root;
            for(char c : w.toCharArray()){
                if(ws.children[c - 'a'] == null) ws.children[c - 'a'] = new TrieNode();
                ws = ws.children[c - 'a'];
            }
            ws.word = w;
        }
    }



    public String longestWord0(String[] words) {
        //build trie
        Trie trie = new Trie();
        for(String w : words) trie.addWord(w);
        //DFS Traverse Trie
        String[] res = new String[]{""};
        dfs(trie.root, res);
        return res[0];
    }

    public void dfs(TrieNode root, String[] res) {
        if(root.word == null) return;
        if(root.word.length() > res[0].length()) res[0] = root.word;
        for(TrieNode child : root.children){//from 'a' to 'z' to make sure the smallest lexicographical order
            if(child != null) dfs(child, res);
        }
    }

}
