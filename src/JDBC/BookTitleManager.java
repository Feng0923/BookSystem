package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTitleManager extends Manager {
    public BookTitleManager(Database control) {
        super(control);
    }
    /**
     *
     * @param jsonObject title: 书名
     * @return
     * 该书籍有关信息
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String title = jsonObject.get("title").getAsString();
        String sql = "select isbn,title,author,prize,rest from book where  title like \"%"+title+"%\"";
        ResultSet query = control.query(sql);
        return query;
    }
}
