package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInformationManager extends Manager {
    public UserInformationManager(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String user_id = jsonObject.get("user_id").getAsString();
        System.out.println(user_id);
        String sql = "select id,name,money,address,phone,level from user where id = \""+user_id+"\"";
        ResultSet resultSet = control.query(sql);
//        control.commit();
        return resultSet;
    }
}
