package JDBC;

import java.sql.Connection;
import java.sql.SQLException;

public interface JDBCConnected {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
    public void close() throws SQLException;
    void commit() throws SQLException;
}
