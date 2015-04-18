package XSquareArrays;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class TestClass {
    long findSum1(ArrayList<Integer> arrayList,ArrayList<Integer> arrayList1,int low,int high) {
        boolean toggle = true;
        long sum = 0;
        for (int i = low-1;i<high;++i) {
            if (toggle == true) {
                //System.out.println("i = " + i + " " + arrayList.get(i));
                sum += arrayList.get(i);

            }else {
                //System.out.println("i = " + i + " " + arrayList1.get(i));
                sum += arrayList1.get(i);
            }

            toggle = !toggle;
        }

        return sum;
    }

    long findSum2(ArrayList<Integer> arrayList,ArrayList<Integer> arrayList1,int low,int high) {
        boolean toggle = false;
        long sum = 0;
        for (int i = low-1;i<high;++i) {
            if (toggle == true) {
                sum += arrayList.get(i);

            }else {
                sum += arrayList1.get(i);
            }

            toggle = !toggle;
        }

        return sum;
    }





    public static void main(String args[] ) throws Exception {

        TestClass testClass = new TestClass();
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//        arrayList.add(5);
//
//        ArrayList<Integer> arrayList1 = new ArrayList<>();
//        arrayList1.add(5);
//        arrayList1.add(4);
//        arrayList1.add(3);
//        arrayList1.add(2);
//        arrayList1.add(1);
//
//        System.out.println(testClass.findSum1(arrayList, arrayList1, 3, 5));




        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N;
        int Q;
        String arr[] = line.split(" ");
        N = Integer.parseInt(arr[0]);
        Q = Integer.parseInt(arr[1]);

        ArrayList<Integer> arrayList = new ArrayList<>();
        String string = br.readLine();
        arr = string.split(" ");
        for (int i = 0;i<arr.length;++i) {
            arrayList.add(Integer.parseInt(arr[i]));
        }

        ArrayList<Integer> arrayList1= new ArrayList<>();
        string = br.readLine();
        arr = string.split(" ");
        for (int i = 0;i<arr.length;++i) {
            arrayList1.add(Integer.parseInt(arr[i]));
        }

        for (int i = 0;i<Q;++i) {
            arr = br.readLine().split(" ");
            if (arr[0].equals("1")) {
                System.out.println(testClass.findSum1(arrayList,arrayList1,Integer.parseInt(arr[1]),Integer.parseInt(arr[2])));
            }else {
                System.out.println(testClass.findSum2(arrayList,arrayList1,Integer.parseInt(arr[1]),Integer.parseInt(arr[2])));
            }

        }




    }
}
