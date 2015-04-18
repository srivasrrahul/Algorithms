package Piramid;

public class TestClass {
    int basicSum(int height) {
        int sampleHeight = height;
        int totalEncountered = 0;
        int sum = 0;
        for (int i = 1;i<=height;++i) {
            int low = totalEncountered+1;
            int high = low + sampleHeight-1;
            int mid = (low + high)/2;
            System.out.println("Low " + low + " High " + high);
            if (((low + high) % 2) == 0) {
                //Skip
                System.out.println("Adding for " + i + " mid " + mid);
                sum += mid;
            }

            --sampleHeight;
            totalEncountered = high;
        }

        return sum;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        System.out.println(testClass.basicSum(6));
    }
}
