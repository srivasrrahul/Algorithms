package ConnectionPool.impl;


import ConnectionPool.*;
public class MyConnection implements IfConnection{
    MyConnection(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void execute() {
        if (hashBasedCheck == 0) {
            //throw some exception there
            System.out.println("Failure");
        }else {
            System.out.println("Executing successfully");
        }
    }

    @Override
    public void close() {
        hashBasedCheck = 0;
        connectionPool.returnConnectionThreadSafe(this);
    }

    //before returning to client do some init step
    void init() {
        fillMetaData();
    }

    private void fillMetaData() {
        hashBasedCheck = System.currentTimeMillis();

    }

    private ConnectionPool connectionPool;

    private long hashBasedCheck;
}
