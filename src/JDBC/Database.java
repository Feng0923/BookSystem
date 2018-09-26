package JDBC;


import com.mysql.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 根据传进来的sql语句对数据库的增删改查
 *
 * */
public interface Database {
    public void add(String sql) throws SQLException, ClassNotFoundException,MySQLQueryInterruptedException;
    public void delete(String sql) throws SQLException;
    public ResultSet query(String sql) throws SQLException, ClassNotFoundException;
    public void update(String sql) throws SQLException, ClassNotFoundException;
    public void close() throws SQLException;
    void commit() throws SQLException;
}
