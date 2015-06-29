package Trie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {

    public TrieNode() {

    }

    void put(String val) {

        if (val.length() == 0) {
            isWord = true;
            return;
        }

        char index = val.charAt(0);
        //System.out.println((int)index);
        if (next[index] == null) {
            next[index] = new TrieNode();
            next[index].put(val.substring(1));

        }else {
            next[index].put(val.substring(1));
        }

    }

    boolean exists(String word) {
       if (word.length() == 0) {
           return isWord;
       }

        char index = word.charAt(0);
        if (next[index] == null) {
            return false;
        }

        return next[index].exists(word.substring(1));
    }

    void wordsWithPrefix(String prefix,List<String> lst) {
        //First find the node
        TrieNode current = this;
        int index = 0;
        while (index < prefix.length() && current.next[prefix.charAt(index)] != null) {
            current = current.next[prefix.charAt(index)];
            ++index;
        }

        if (index != prefix.length()) {
            System.out.println("No such words");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix);

        current.iterate(stringBuilder,lst);


    }

    //. is considered as special
    String longestPrefix(String key) {
        TrieNode current = this;
        int index = 0;
        while (index < key.length() && current.next[key.charAt(index)] != null) {
            current = current.next[key.charAt(index)];
            ++index;
        }

        return key.substring(0, index);
    }

    void iterate(StringBuilder stringBuilder,List<String> lst) {
        if (isWord) {
            //System.out.println(stringBuilder.toString());
            lst.add(stringBuilder.toString());
        }

        for (int i = 0;i <next.length;++i) {
            if (next[i] != null) {
                StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
                stringBuilder1.append((char)i);
                next[i].iterate(stringBuilder1,lst);
            }
        }
    }




    boolean isWord = false;
    private TrieNode next [] = new TrieNode[255];

    //Test code
    static void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            TrieNode trieNode = new TrieNode();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                trieNode.put(line);
            }

            System.out.println("Dictionary found");
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.println("Enter your word");
//
//                String word = scanner.next();
//                System.out.println(trieNode.exists(word));
//            }

            StringBuilder stringBuilder = new StringBuilder();
            LinkedList<String> words = new LinkedList<>();
            //trieNode.iterate(stringBuilder,words);
            //trieNode.wordsWithPrefix("hel",words);
            System.out.println(trieNode.longestPrefix("helpsomes"));

            for (String word : words) {
                System.out.println(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
//        TrieNode trieNode = new TrieNode();
//        trieNode.put("shell");
//        trieNode.put("she");
//        System.out.println(trieNode.exists("s"));
        readFile("/usr/share/dict/words");
    }
}
