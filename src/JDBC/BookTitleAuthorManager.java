package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookTitleAuthorManager extends Manager {
    public BookTitleAuthorManager(Database control) {
        super(control);
    }

    /**
     *
     * @param jsonObject title:书名 , author:作者
     * @return 返回有关书籍信息
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String title = jsonObject.get("title").getAsString();
        String author = jsonObject.get("author").getAsString();
        String sql = "select isbn,title,anthor,prize,rest from book where  title like \"%"+ title+"%\" and author like \"%"+author+"%\"";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
