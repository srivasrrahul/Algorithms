package SegdewickAlgo;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Rahul on 11/12/15.
 */
public class Outcast {
    private WordNet wordNet;
    public Outcast(WordNet wordnet){
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int maxDistance = Integer.MIN_VALUE;
        String maxDistanceStr = null;
        for (String x : nouns) {
            int distance = 0;
            for (String y : nouns) {
                //System.out.println("Distance for " + x + " " + y);
                int val = wordNet.distance(x, y);
                //System.out.println("Distance for " + x + " " + y + " => " + val);
                distance += val;

            }

            //System.out.println("Distance for " + x + " => " + distance);
            if (distance > maxDistance) {
                maxDistanceStr = x;
                maxDistance = distance;
            }
        }

        return maxDistanceStr;
    }
    public static void main(String[] args)  {
        WordNet wordNet = new WordNet(args[0],args[1]);
        Outcast outcast = new Outcast(wordNet);
        for (int t = 2; t < args.length; t++) {
            //System.out.println("For " + args[t]);
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }


//        String options[] = new String[4];
//        options[0] = "1830s";
//        options[1] = "1840s";
//        options[2] = "1750s";
//        options[3] = "22-karat_gold";
//
//        System.out.println(outcast.outcast(options));


    } // see test client below
}
