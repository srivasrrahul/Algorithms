package BinSearch;


public class BinarySearchVariants {
    static int search(int [] arr,int k,int low,int high) {
        if (low > high) {
            return -1;
        }

        if (low == high) {
            if (arr[low] == k) {
                return low;
            }else {
                return -1;
            }
        }

        int mid = low + (high-low)/2;
        if (arr[mid] > k) {
            return search(arr,k,low,mid-1);
        }else {
            if (arr[mid] < k) {
                return search(arr,k,mid+1,high);
            }else {
                //equal
                return mid;
            }
        }
    }

    static int binsearch(int [] arr,int k) {
        return search(arr,k,0,arr.length-1);
    }


    //0 means its unimodal
    //1 means increasing
    //-1 means decreasing
    static int isUnimodalIndex(int arr[],int index) {
        //First index
        if (index == 0) {
            return arr[0] > arr[1]?0:1;
        }

        //Last index
        if (index == arr.length-1) {
            //Last index means its always true
            return 0;
        }

        if (arr[index] > arr[index-1] && arr[index] > arr[index+1]) {
            return 0;
        }

        //Upward slope
        if (arr[index] > arr[index-1] && arr[index] < arr[index+1]) {
            return 1;
        }


        //Downward slope
        if (arr[index] < arr[index-1] && arr[index] > arr[index+1]) {
            return -1;
        }

        System.out.println("Invalid condition");
        assert false;

        return -1;

    }
    static int unimodalSearch(int arr[],int low,int high) {
        if (low > high) {
            return -1;
        }

        if (low == high) {
            //special cases
            //assume unimodal is at least on index 1 and n-2
            if (arr[low-1] < arr[low] && arr[low] > arr[high]) {
                return low;
            }else {
                return -1;
            }
        }

        int mid = low + (high - low)/2;
        int unimodalValue = isUnimodalIndex(arr,mid);
        if (unimodalValue == 0) {
            return mid;
        }

        if (unimodalValue > 0) {
            return unimodalSearch(arr,mid+1,high);
        }else {
            return unimodalSearch(arr,low,mid);
        }

    }


    static int findRotationPoint(int [] arr,int low,int high) {
        //Leave error conditions
        int mid = low + (high -low)/2;
        if (arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]) {
            return mid;
        }

        if (arr[mid] > arr[low]) {
            return findRotationPoint(arr,mid+1,high);
        }else {
            return findRotationPoint(arr,low,mid);
        }
    }

    public static void testBinarySearch(String[] args) {
        int arr[ ] = new int[] {10,20,30,31,32,33,34,35};
        for (int i = 0;i<arr.length;++i) {
            int x = arr[i];
            int index = binsearch(arr,x);
            if (index != i) {
                System.out.println("Algorithm not correct");
            }else {
                System.out.println("Correct algo");
            }
        }
    }

    static void testRotationPoint() {
        int arr[] = new int[]{7,8,9,10,11,12,13,5};
        System.out.println(findRotationPoint(arr,0,arr.length-1));
    }
    static void testUnimodalSearch() {
        int arr[ ] = new int[] {10,20,19,18,17,16,15,11};
        System.out.println(unimodalSearch(arr,0,arr.length-1));
    }
    public static void main(String[] args) {
        //testBinarySearch(null);
        //testUnimodalSearch();
        testRotationPoint();
    }
}
