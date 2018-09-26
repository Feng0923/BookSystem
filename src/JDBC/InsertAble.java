package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;

public interface InsertAble {
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException;
}
