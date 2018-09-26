package JDBC;

import java.sql.*;

public class DataManager implements Database {

    public void setConnected(JDBCConnected connected) {
        this.connected = connected;
    }



    private JDBCConnected connected;

    public DataManager(JDBCConnected jdbcConnected) {
        connected = jdbcConnected;
    }

    @Override
    public void add(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = connected.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(String sql) throws SQLException {

    }

    @Override
    public ResultSet query(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = connected.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    @Override
    public void update(String sql) throws SQLException, ClassNotFoundException {
        Connection connection = connected.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    @Override
    public void close() throws SQLException {
        connected.close();
    }

    @Override
    public void commit() throws SQLException {
        connected.commit();
    }
}
