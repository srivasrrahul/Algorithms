package ConnectionPool;

import java.util.Random;


public class TestClass implements Runnable {
    @Override
    public void run() {
        Random random = new Random(System.currentTimeMillis());

        while (true) {
            try {
                Thread.sleep(100);
                IfConnection connection = ifConnectionPool.createConnection();
                if (connection != null) {
                    connection.execute();
                    if (random.nextBoolean()) {
                        Thread.sleep(1000);
                    }
                    connection.close();
                    System.out.println("Pool size is " + ifConnectionPool.getPoolSize());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    TestClass(IfConnectionPool ifConnectionPool) {
        this.ifConnectionPool = ifConnectionPool;
    }

    private IfConnectionPool ifConnectionPool;

    public static void main(String[] args) throws InterruptedException {
        IfConnectionPool connectionPool = Manager.createConnectionPool(new ConfigParam(5,10,3));

        Thread [] threads = new Thread[10];
        for (int i = 0;i<10;++i) {
            threads[i] = new Thread(new TestClass(connectionPool));
        }

        for (int i = 0;i<10;++i) {
            threads[i].start();
        }

        for (int i = 0;i<10;++i) {
            threads[i].join();
        }

//        for (int i = 0;i<100;i++) {
//            connectionPool.createConnection();
//        }
    }
}

