package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;

public class UserCharge extends Manager {
    public UserCharge(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException,ClassCastException, ClassNotFoundException {
        String user_id = jsonObject.get("user_id").getAsString();
        double money = jsonObject.get("money").getAsDouble();
        if (money>0){
            String sql = "update user set money = money + "+money+" where id = \""+user_id+"\"";
            control.update(sql);
        }
        return true;
    }
}
