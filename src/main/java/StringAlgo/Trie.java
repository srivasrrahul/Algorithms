package StringAlgo;

import java.util.HashMap;

class TrieNode {
    private char data;
    private HashMap<Byte,TrieNode> byteTrieNodeHashMap;
    private String val;

    TrieNode(char data) {
        this.data = data;
    }

    public char getData() {
        return data;
    }

    public HashMap<Byte,TrieNode> getByteHashSet() {
        return byteTrieNodeHashMap;
    }

    public void setByteHashSet(HashMap<Byte,TrieNode> byteTrieNodeHashMap) {
        this.byteTrieNodeHashMap = byteTrieNodeHashMap;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}

//String is only ascii
public class Trie {


    public void put(String key, String val) {


    }

    public String getVal(String key) {
        return null;
    }

    public boolean isPresent(String key) {
        return false;
    }

    private TrieNode root;
}
