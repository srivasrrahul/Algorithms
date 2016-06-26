package KMP;

/**
 * Created by rasrivastava on 3/21/16.
 */
public class KMPDemo {

    //ATAG
    //00
    private String pat;
    private int[][] dfa;
    public KMPDemo(String pat) {
        // Build DFA from pattern.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {  // Compute dfa[][j].
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt)
        {  // Simulate operation of DFA on txt.
        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;  // found (hit end of pattern)
        else        return N;      // not found (hit end of text)
    }



    public static void main(String[] args) {
        KMPDemo kmpDemo = new KMPDemo("HELLOWORLD");
        System.out.println(kmpDemo.search("ELLO"));
    }

}
