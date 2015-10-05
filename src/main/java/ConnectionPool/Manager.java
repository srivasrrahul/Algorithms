package ConnectionPool;

import ConnectionPool.impl.*;
public class Manager {
    static IfConnectionPool createConnectionPool(ConfigParam configParam) {
        ConnectionPool connectionPool = new  ConnectionPool();

        connectionPool.init(configParam);
        return connectionPool;
    }

    public static void main(String[] args) {
        IfConnectionPool connectionPool = createConnectionPool(new ConfigParam(5,3,10));
        IfConnection connection = connectionPool.createConnection();
        connection.execute();
        connection.close();
    }
}
