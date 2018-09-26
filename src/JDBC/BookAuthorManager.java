package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorManager extends Manager {
    public BookAuthorManager(Database control) {
        super(control);
    }

    /**
     *
     * @param jsonObject author:作者
     * @return 返回该作者的书籍信息
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String author = jsonObject.get("author").getAsString();
        System.out.println(author);
        String sql = "select isbn,title,author,prize,rest from book where  author like \"%"+author+"%\"";
        ResultSet query = control.query(sql);
        return query;
    }
}
