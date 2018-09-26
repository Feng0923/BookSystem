package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookISBN extends Manager {
    public BookISBN(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {

        String isbn = jsonObject.get("isbn").getAsString();
        String sql = "select isbn,title,author,prize,rest" +
                " from book" +
                " where isbn = \""+isbn+"\"";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
