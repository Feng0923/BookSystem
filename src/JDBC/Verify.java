package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;

public interface Verify {
    public int verify(JsonObject object) throws SQLException, ClassNotFoundException;
}
