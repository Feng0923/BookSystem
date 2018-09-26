package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements JDBCConnected {
    private Connection connection;
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/booksystem","root","root");
        connection = connection1;
        connection.setAutoCommit(true);
        return connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }
}
