package WaitNotify;

import java.util.LinkedList;

/**
 * Created by rasrivastava on 1/8/16.
 */
public class ProducerConsumer implements Runnable{
    private int val = 0;
    private LinkedList<Integer> lst = new LinkedList<>();
    private void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                ++val;
                lst.add(val);
                notifyAll();
            }

            Thread.sleep(100);
        }
    }

    private void consumer() throws InterruptedException {
        while (true) {
            int val = 0;
            synchronized (this) {
                while (lst.size() == 0) {
                    wait();
                }

                val = lst.remove();

            }
            System.out.println(val);
        }


    }


    @Override
    public void run() {
        if (mode == 0) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private int mode;

    public ProducerConsumer(int mode) {
        this.mode = mode;
    }

    void setMode(int mode) {
        this.mode = mode;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer producerConsumer = new ProducerConsumer(0);
        Thread t1 = new Thread(producerConsumer);
        t1.start();
        Thread.sleep(1000);
        producerConsumer.setMode(1);
        Thread t2 = new Thread(producerConsumer);
        t2.start();

        Thread t3 = new Thread(producerConsumer);
        t1.join();
        t1.join();
        t3.join();

    }
}
