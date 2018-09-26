package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert extends Manager {
    public BookInsert(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {

        String isbn = jsonObject.get("isbn").getAsString();
        String title = jsonObject.get("title").getAsString();
        String author = jsonObject.get("author").getAsString();
        double prize = jsonObject.get("prize").getAsDouble();
        int count = jsonObject.get("rest").getAsInt();

        String sql = "select isbn from book where isbn = \""+isbn+"\"";
        ResultSet query = control.query(sql);
        if (query.next()){
            sql = "update book set rest = rest +"+count+" where isbn = \""+isbn+"\"";
            control.update(sql);
//            control.commit();
        }else{
            sql = "insert into book(isbn,title,author,prize,rest) values("+"\""+isbn+"\",\""+title+"\",\""+author+"\",\""+prize+"\",\""+count+"\")";
            control.add(sql);
//            control.commit();
        }
        return true;
    }
}
