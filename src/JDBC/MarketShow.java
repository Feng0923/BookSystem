package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketShow extends Manager {
    public MarketShow(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String sql = "select book.isbn,book.title,book.author,book.prize,sum from book,bookmarket where book.isbn = bookmarket.isbn";
        ResultSet query = control.query(sql);
        return query;
    }
}
