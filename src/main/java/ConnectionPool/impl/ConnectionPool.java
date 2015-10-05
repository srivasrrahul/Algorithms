package ConnectionPool.impl;

import ConnectionPool.ConfigParam;
import ConnectionPool.IfConnection;
import ConnectionPool.IfConnectionPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * dhiraj.kumar@flipkart.com
 * k.dhruv@flipkart.com
 *
 */

public class ConnectionPool implements IfConnectionPool {

    @Override
    public IfConnection createConnection() {
        MyConnection myConnection = null;
        try {
            myConnection = createConnectionThreadSafe();
        } catch (InterruptedException e) {
            //Bad condition
          return null;
        }


        if (myConnection == null) {
            //Not possible condition
            //but still check if its working
            return null;
        }
        myConnection.init();
        return myConnection;
    }

    @Override
    public int getPoolSize() {
        return currentPoolSize;
    }

    MyConnection createConnectionThreadSafe() throws InterruptedException {
        //Assume it exists
        lock.lock();
        try {

            if (currentPoolSize >= configParam.getMax()) {
                System.out.println("Pool is reached at max and hence can't increase further" + currentPoolSize);
                while (connectionPoolList.size() == 0) {
                    System.out.println("Waiting for it to become free");
                    notFull.await();
                }
            }else {
                if (connectionPoolList.size() == 0) {
                    //Nothing left
                    expandPoolThreadSafe();
                }

                assert(connectionPoolList.size() > 0);
                //Since I am inside lock and I have only increase connection I don't need to block
            }

            if (connectionPoolList.size() == 0) {
                System.out.println("major problem");
                return null;
            }

            MyConnection connection = connectionPoolList.remove(0);
            return connection;

        }finally {

            lock.unlock();


        }


    }

    //Make sure it gets called under lock
    //and 0 is present inside pool
    void expandPoolThreadSafe() {
        System.out.println("Increasing pool size " + currentPoolSize);
        int possibleToInCrease = currentPoolSize + configParam.getExpansionFactor();
        System.out.println("Possible to increase " + possibleToInCrease);

        for (int i = currentPoolSize;i<=possibleToInCrease && i < configParam.getMax();++i) {
            connectionPoolList.add(new MyConnection(this));

            ++currentPoolSize;
            System.out.println("Current pool size is " + currentPoolSize);
        }

        //System.out.println("Increasing pool size from currentPoolSize = " + currentPoolSize + " to " + (currentPoolSize + connectionPoolList.size()));

    }

    void returnConnectionThreadSafe(MyConnection myConnection) {
        lock.lock();
        try {

            connectionPoolList.add(myConnection);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }


    public void init(ConfigParam configParam) {
        this.configParam = configParam;
        if (false == Policy.validate(configParam)) {
            System.out.println("Invalid params need to throw exception here in futuere");
        }

        createPool();
    }

    void createPool() {
        for (int i = 0;i<configParam.getMin();++i) {
            MyConnection myConnection = new MyConnection(this);
            connectionPoolList.add(myConnection);
        }

        currentPoolSize = configParam.getMin();
    }


    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private List<MyConnection> connectionPoolList = new LinkedList<>();
    private ConfigParam configParam;
    volatile private int currentPoolSize;



}
