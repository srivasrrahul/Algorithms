package LCS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

class CacheEntry {
    private int x;
    private int y;


    CacheEntry(int x,int y) {
        this.x = x;
        this.y = y;
    }



    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheEntry that = (CacheEntry) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}


public class Main {


    public String lcs(String string1,String string2,int x,int y,Cache cache) {
        if (x == string1.length() || y== string2.length()) {
            return "";
        }

        if (cache.isPresent(x,y)) {
            return cache.getValue(x,y);
        }

        char ch1 = string1.charAt(x);
        char ch2 = string2.charAt(y);

        String arr[] = new String[3];
        arr[0] = "";
        arr[1] = "";
        arr[2] = "";
        String lcs1 = "";
        if (ch1 == ch2) {
            arr[0] = lcs(string1,string2,x+1,y+1,cache);
            arr[0] = Character.toString(ch1) + arr[0];
        }

        arr[1] = lcs(string1,string2,x+1,y,cache);
        arr[2] = lcs(string1,string2,x,y+1,cache);

        //System.out.println(Arrays.asList(arr));

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //return o1.length() < o2.length();
                if (o1.length() == o2.length()) {
                    return 0;
                }

                if (o1.length() < o2.length()) {
                    return -1;
                }
                if (o1.length() > o2.length()) {
                    return 1;
                }

                return 0;
            }
        });


        cache.updateEntry(new CacheEntry(x,y),arr[2]);
        return arr[2];

    }
    void handleLine(String line) {

        String [] arr = line.split(";");
        Cache cache = new Cache();
        System.out.println(lcs(arr[0],arr[1],0,0,cache));





    }
    void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                handleLine(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Main main = new Main();
        main.readFile(args[0]);
    }
}
