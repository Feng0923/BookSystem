package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SuggestionShow extends Manager {
    public SuggestionShow(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String sql = "select user_id,content,time " +
                "from suggestion";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
