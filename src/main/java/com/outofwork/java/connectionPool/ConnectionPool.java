package com.outofwork.java.connectionPool;

import java.sql.Connection;

/**
 * @author outofwork
 * created on 16/02/21
 */
public interface ConnectionPool {

    Connection acquireConnection();

    boolean releaseConnection(Connection connection);

}
