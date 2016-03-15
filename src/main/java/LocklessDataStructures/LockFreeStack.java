package LocklessDataStructures;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Rahul on 2/15/16.
 */
class StackNode {
    private int value;
    private StackNode next;

    public StackNode(int value) {
        this.value = value;
    }

    void setNext(StackNode stackNode) {
        next = stackNode;
    }

    public StackNode getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }
}

class Producer implements  Runnable {

    @Override
    public void run() {
        for (int i = 0;i<Integer.MAX_VALUE;++i) {
            lockFreeStack.addData(i);
        }
    }

    public Producer(LockFreeStack lockFreeStack) {
        this.lockFreeStack = lockFreeStack;
    }

    private LockFreeStack lockFreeStack;
}

class Consumer implements Runnable {

    @Override
    public void run() {
        for (int i = 0;i<Integer.MAX_VALUE;++i) {
            try {
                System.out.println(lockFreeStack.getData());
            } catch (Exception e) {

            }
        }
    }

    public Consumer(LockFreeStack lockFreeStack) {
        this.lockFreeStack = lockFreeStack;
    }

    private LockFreeStack lockFreeStack;
}

public class LockFreeStack {
    private AtomicReference<StackNode> head = new AtomicReference<>();

    void addData(int value) {
        StackNode stackNode = new StackNode(value);
        do {
//            if (head == null) {
//                stackNode.setNext(null);
//            }else {
                stackNode.setNext(head.get());
            //}

        } while (!head.compareAndSet(stackNode.getNext(),stackNode));

    }

    int getData() {
        if (head.get() != null) {
            StackNode stackNode = head.get();
            if (stackNode != null) {
                do {
                    stackNode = head.get();
                }while (!head.compareAndSet(stackNode,stackNode.getNext()));

                if (stackNode != null) {
                    return stackNode.getValue();
                }

            }

        }

        throw new IllegalStateException("Stack is Empty");
    }

    public static void main(String[] args) throws InterruptedException {
        LockFreeStack lockFreeStack = new LockFreeStack();
        Thread thread1 = new Thread(new Producer(lockFreeStack))   ;
        Thread thread12 = new Thread(new Consumer(lockFreeStack))   ;

        thread1.start();
        thread12.start();
        thread1.join();
        thread12.join();

    }

}
