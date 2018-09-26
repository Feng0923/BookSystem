package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UserSuggestionInsert extends Manager{
    public UserSuggestionInsert(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String user_id = jsonObject.get("user_id").toString();
        String content = jsonObject.get("content").toString();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        String sql = "insert into suggestion(user_id,content,time) values("+user_id+","+content+",\""+time+"\")";
        control.add(sql);
        return true;
    }
}
