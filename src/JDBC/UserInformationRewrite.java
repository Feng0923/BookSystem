package JDBC;

import com.google.gson.JsonObject;

import java.sql.SQLException;

public class UserInformationRewrite extends Manager {

    public UserInformationRewrite(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String name = jsonObject.get("name").getAsString();
        String user_id = jsonObject.get("user_id").getAsString();
        String password = jsonObject.get("password").getAsString();
        String address = jsonObject.get("address").getAsString();
        String phone = jsonObject.get("phone").getAsString();

        String sql = "";
        if (!password.isEmpty()){
            sql = "update user set password = \""+password+"\"where id = \""+user_id+"\"";
            control.update(sql);
        }
        if (!name.isEmpty()){
            sql = "update user set name = \""+name+"\"where id = \""+user_id+"\"";
            control.update(sql);
        }
        if (!address.isEmpty()){
            sql = "update user set address = \""+address+"\"where id = \""+user_id+"\"";
            control.update(sql);
        }

        if (!phone.isEmpty()){
            sql = "update user set phone = \""+phone+"\"where id = \""+user_id+"\"";
            control.update(sql);
        }

        return true;
    }
}
