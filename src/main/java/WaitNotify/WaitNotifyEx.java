package WaitNotify;

/**
 * Created by rasrivastava on 12/9/15.
 */

class Worker {
    boolean empty = false;
    int value = 0;
    synchronized void produce() {
        while (empty == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Produced 1");
        empty = false;
        value = 1;
        System.out.println("Prodduced value " + value);
        notifyAll();

    }

    synchronized void consume() {
        while (empty == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        empty = true;
        System.out.println("Consuming " + value);
        value = 0;
        notifyAll();
    }
}
public class WaitNotifyEx {

}
