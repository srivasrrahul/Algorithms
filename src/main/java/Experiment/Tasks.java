package Experiment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rasrivastava on 9/23/15.
 */
public class Tasks {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        executorService.shutdown();
    }
}
