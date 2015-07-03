package SubStringSearchAlgos;


public class BruteForce {
    static int find(String pattern,String text) {
        int m = pattern.length();
        int n = text.length();
        for (int i = 0;i<n-m;++i) {
            int j = 0;
            for (;j<m;++j) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                }else {
                    continue;
                }
            }

            if (j == m) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(BruteForce.find("hello","sghellasgdhagsd"));
    }
}
