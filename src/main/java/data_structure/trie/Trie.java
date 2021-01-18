package data_structure.trie;

class Trie {
    public TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public class TrieNode {
        boolean isEnd;
        TrieNode[] son;
        int number;

        public TrieNode() {
            isEnd = false;
            son = new TrieNode[26];
            number = 0;
        }
    }

    public void insert(String word) {
        char[] w = word.toCharArray();
        TrieNode p = root;
        for (char c : w) {
            int u = c - 'a';
            if (p.son[u] == null) {
                p.son[u] = new TrieNode();
            }
            p = p.son[u];
        }
        p.isEnd = true;
        p.number++;
    }

    public int query(String word) {
        char[] w = word.toCharArray();
        TrieNode p = root;
        for (char c : w) {
            int u = c - 'a';
            if (p.son[u] == null) {
                return 0;
            }
            p = p.son[u];
        }
        if (p.isEnd == true) {
            return p.number;
        }
        return 0;
    }
}
