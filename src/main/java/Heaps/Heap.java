package Heaps;


import java.util.Arrays;

//Below is max heap
public class Heap  {
    public Heap(int N) {
        keys = new int[N+1];
        heapSize = N;
    }

    int parent(int i) {
        return i/2;
    }

    int left(int i) {
        return 2*i;
    }

    int right(int i) {
        return 2*i+1;
    }

    boolean less(int x,int y) {
        return keys[x] < keys[y];
    }

    void exch(int x,int y) {
        int temp = keys[x];
        keys[x] = keys[y];
        keys[y] = temp;
    }

    void swim(int k) {
        while (k > 1 && less(k/2,k)) {
            exch(k/2,k);
        }
    }

    void increaseKey(int index,int value) {
        keys[index] += value;
        swim(index);
    }
    void decreaseKey(int index,int value) {
        keys[index] -= value;
        sink(index);
    }

    void sink(int x) {
        while (2 * x <= heapSize) {
            int left = 2*x;
            int right = 2*x+1;
            int maxIndex = x;
            if (right <= heapSize) {
                if (less(x,right)) {
                    maxIndex = right;
                }
            }

            if (less(maxIndex,left)) {
                maxIndex = left;
            }

            if (maxIndex == x) {
                //Heap property satisfied
                break;
            }

            exch(maxIndex,x);
            x = maxIndex;
        }
    }

    void insert(int x) {
        keys[++heapIndex] = x;
        swim(heapIndex);
    }


    @Override
    public String toString() {
        return "Heap{" +
                "keys=" + Arrays.toString(keys) +
                '}';
    }




    int heapIndex = -1;
    int heapSize;
    int [] keys;

    public static void main(String[] args) {
        Heap heap = new Heap(5);
        heap.insert(10);
        heap.insert(11);
        heap.insert(9);
        heap.insert(12);
        heap.insert(8);

        System.out.println(heap.toString());
        heap.decreaseKey(0,3);
        System.out.println(heap.toString());
        heap.increaseKey(3, 3);
        System.out.println(heap.toString());
    }


}
