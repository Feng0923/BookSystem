package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountShow extends Manager {
    public DiscountShow(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {

        String sql = "select level,discount_size from discount";
        ResultSet query = control.query(sql);
        return query;
    }
}
