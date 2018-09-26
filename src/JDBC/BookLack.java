package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLack extends Manager {
    public BookLack(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String sql = "select isbn,title,author,prize,rest from book where rest = 0 ";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
