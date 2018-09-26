package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *查询接口
 * */
public interface QueryAble {
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException;
}
