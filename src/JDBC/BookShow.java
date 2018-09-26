package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookShow extends Manager {
    public BookShow(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String sql = "select isbn,title,author,prize,rest from book";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
