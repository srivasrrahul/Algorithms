package NextLargest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class TestClass {
    int findMin(ArrayList<Integer> arr,int x,int low,int high) {
        //do binary search
        //System.out.println("Low " + low + " High " + high);
        if (low >= arr.size()) {
            return -1;
        }

        if (low >= high) {
            if (arr.get(low) <= x) {
                return -1;
            }else {
                return arr.get(low);
            }
        }

        int mid = (low + high)/2;

        int midValue = arr.get(mid);

        if (midValue > x) {
            //System.out.println("Inside left");
            return findMin(arr,x,low,mid);
        }else {

            //System.out.println("Inside right");
            return findMin(arr,x,mid+1,high);
        }

    }

    public static void main(String[] args) throws IOException {
        TestClass testClass = new TestClass();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String data[] = line.split(" ");
        int N = Integer.parseInt(data[0]);
        int low = Integer.parseInt(data[1]);
        int high = Integer.parseInt(data[2]);
        int x = Integer.parseInt(data[3]);
        line = br.readLine();
        data = line.split(" ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0;i<N;++i) {
            if (i >= low && i <= high) {
                arrayList.add(Integer.parseInt(data[i]));
            }
        }

        Collections.sort(arrayList);
        //System.out.println(Arrays.asList(arrayList));


        data = br.readLine().split(" ");
        for (int i = 0;i<data.length;++i) {
            int tempX = Integer.parseInt(data[i]);
            int actualX = tempX;
            System.out.println(testClass.findMin(arrayList,actualX,0,arrayList.size()-1));
        }


    }
}
