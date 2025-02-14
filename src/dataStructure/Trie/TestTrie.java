package src.dataStructure.Trie;

import java.util.HashMap;
import java.util.Map;

class WordDictionary {

    private class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }


    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;

    }

    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    private boolean searchHelper(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (searchHelper(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        }

        if (node.children.containsKey(c)) {
            return searchHelper(word, index + 1, node.children.get(c));

        }
        return false;
    }
}

public class TestTrie {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        // Add words
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        // Search for words
        System.out.println(wordDictionary.search("pad")); // false
        System.out.println(wordDictionary.search("bad")); // true
        System.out.println(wordDictionary.search(".ad")); // true
        System.out.println(wordDictionary.search("b..")); // true
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */