/**
 * Created by rasrivastava on 10/18/16.
 */
import java.io.*;
import java.util.*;


/*

An anagram is a word that has the same type and frequency of letters as given word.

For example:
* `anagram` can be rearranged into `nagaram`
* `act` and `cat` are anagrams
* `abcdefg` and `abcgfde` are anagrams
* `racecar` is an anagram of `racecar`, because racecar


Given the following interface and list of words, create functions that would allow you to get all of the anagrams of a given word that were present in the list.


*/

class Solution {


    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("a");
        words.add("aaa");
        words.add("bab");
        words.add("rats");
        words.add("robe");
        words.add("abb");
        words.add("arts");
        words.add("semaphore");
        words.add("tars");
        words.add("bore");
        words.add("semarophe");
        Anagramalyzer ana = new MyAnagramalyzer();
        for (String word : words) {
            ana.addWord(word);
        }

        runAnagramalizer(ana);

    }

    public static void printArr(String[] arr) {
        for (String val : arr) {
            System.out.println(val);
        }
    }

    public static void runAnagramalizer(Anagramalyzer instanceOfYourAnagramalyzer) {
        List<String> testWords = new ArrayList<>();
        testWords.add("bba");
        testWords.add("robe");
        testWords.add("star");
        for (String test : testWords) {
            System.out.println("Anagrams of " + test + ":");
            printArr(instanceOfYourAnagramalyzer.getAnagrams(test));
        }
    }
}

interface Anagramalyzer {

    // Add a word to our collection
    void addWord(String word);

    // Get all the anagrams present in the given list for any word.
    String[] getAnagrams(String word);
}



class MyAnagramalyzer implements Anagramalyzer {
    @Override
    public void addWord(String word) {
        char [] arr = word.toCharArray();
        Arrays.sort(arr);
        String sortedString = new String(arr);
        //System.out.println("SortedString is " + sortedString);
        if (values.containsKey(sortedString)) {
            //System.out.println("Already exists");
            HashSet<String> data = values.get(sortedString);
            data.add(word);
        }else {
            HashSet<String> set = new HashSet<>();
            set.add(word);
            values.put(sortedString,set);
        }
    }

    @Override
    public String[] getAnagrams(String word) {
        char [] arr = word.toCharArray();
        Arrays.sort(arr);
        String sortedString = new String(arr);

//        if (values.containsKey(sortedString)) {
////            char [] arr = word.toCharArray();
////            Arrays.sort(arr);
////            String sortedString = new String(arr);
            if (values.containsKey(sortedString)) {
                HashSet<String> set = values.get(sortedString);
                //String [] returnValue = new String[set.size()];
                LinkedList<String> returnValue = new LinkedList<>();
                int i = 0;
                for (String value : set) {
                    if (false == value.equals(word)) {
                        //System.out.println("Adding value " + value);
                        //returnValue[i] = value;
                        returnValue.add(value);
                        i++;
                    }

                }


                return returnValue.toArray(new String[returnValue.size()]);

            }else {
                return new String[1];
            }


//        }else {
//            System.out.println("Nothing");
//            return new String[1];
//        }
    }
    // your code here

    private HashMap<String,HashSet<String>> values = new HashMap<>();
}

