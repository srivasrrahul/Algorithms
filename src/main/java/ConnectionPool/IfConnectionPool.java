package ConnectionPool;


public interface IfConnectionPool {
    IfConnection createConnection();
    //Mgmt interace
    int getPoolSize();
}
