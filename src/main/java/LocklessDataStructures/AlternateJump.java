package LocklessDataStructures;

import java.util.LinkedList;

/**
 * Created by Rahul on 2/15/16.
 */

public class AlternateJump implements Runnable{
    volatile boolean threadOneWants = false;
    volatile boolean threadTwoWants = false;
    volatile int threadTurn = 0; //alternates between zero and one
    volatile int index = 0; //producer increments by one each time

    private int mode = 0;
    LinkedList<Integer> linkedList = new LinkedList<>();

    void addData(int data) {
        linkedList.add(data);
    }

    int removeData() {
        return linkedList.removeLast();
    }
    void threadOne() {
        while (true) {
            threadOneWants = true;
            threadTurn = 1;
            while (threadTwoWants && threadTurn == 1);
            addData(++index);
            threadOneWants = false;
        }


    }

    void threadTwo() {
        while (true) {
            threadTwoWants = true;
            threadTurn = 0;
            while (threadOneWants && threadTurn == 0);
            System.out.println(removeData());
            threadTwoWants = false;
        }
    }
    AlternateJump(int mode) {
        this.mode = mode;
    }



    public static void main(String[] args) throws InterruptedException {
        AlternateJump alternateJump = new AlternateJump(0);

        Thread thread1 = new Thread(alternateJump);
        thread1.start();

        Thread.sleep(1);
        Thread thread2 = new Thread(alternateJump);
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Override
    public void run() {
        if (mode == 0) {
            mode = 1;
            threadOne();
        }else {
            mode = 0;
            threadTwo();
        }
    }


}
