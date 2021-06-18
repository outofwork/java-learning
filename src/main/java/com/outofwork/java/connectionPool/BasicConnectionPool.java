package com.outofwork.java.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author outofwork
 * created on 16/02/21
 */
public class BasicConnectionPool implements ConnectionPool {

    private final List<Connection> connectionPool;
    private final List<Connection> usedConnections;
    private final int INITIAL_POOL_SIZE;
    private final int MAX_POOL_SIZE;


    public BasicConnectionPool(SQLConnectionObject connectionObject, int initialSize, int maxSize) {
        this.connectionPool = new ArrayList<>();
        this.usedConnections = new ArrayList<>();
        this.INITIAL_POOL_SIZE = initialSize;
        this.MAX_POOL_SIZE = maxSize;

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(connectionObject));
        }
    }

    private static Connection createConnection(SQLConnectionObject connectionObject) {
        try {
            return DriverManager.getConnection(connectionObject.getUrl(),
                    connectionObject.getDbUsername(),
                    connectionObject.getDbPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connection acquireConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}