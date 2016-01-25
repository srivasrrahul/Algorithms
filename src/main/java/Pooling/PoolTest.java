package Pooling;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by rasrivastava on 11/30/15.
 */
public class PoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long t1 = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        long t2 = System.currentTimeMillis();
        ArrayList<Future<Integer>> arrList = new ArrayList<>();
        for (int i = 0;i<10;++i) {
            Future<Integer> f = executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 1;
                }
            });
            arrList.add(f);

        }

        for (int i = 0;i<10;++i) {
            int z = arrList.get(i).get();
            System.out.println(z);
        }
        System.out.println(t2-t1);

    }
}
