package JDBC;

import com.google.gson.JsonObject;
import com.mysql.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.SQLException;

public class UserInsert extends Manager implements InsertAble {
    public UserInsert(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws ClassNotFoundException, SQLException {
        String user_id = jsonObject.get("user_id").getAsString();
        String password = jsonObject.get("password").getAsString();
        String name = jsonObject.get("name").getAsString();
        String address = jsonObject.get("address").getAsString();
        String phone = jsonObject.get("phone").getAsString();
        String sql = "insert into user(id,password,name,address,phone) values(\""+user_id+"\",\""+password+"\",\""+name+"\",\""+address+"\",\""+phone+"\")";
        control.add(sql);
        return true;
    }
}
