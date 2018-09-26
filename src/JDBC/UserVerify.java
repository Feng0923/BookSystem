package JDBC;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class  UserVerify extends Manager  {
    public UserVerify (Database control){
        super(control);
    }

    /**
     *
     * @param object
     * 存有用户的user_id,password的jsonobject
     * @return
     * 是否存在该用户
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    @Override
    public int verify(JsonObject object) throws SQLException, ClassNotFoundException {
        String user_id = object.get("user_id").getAsString();
        String password = object.get("password").getAsString();
        String sql = "select level from user where id = \""+user_id+ "\" and password = \""+password+"\"";
        ResultSet query = control.query(sql);
        if (query.next()){
            int level = query.getInt("level");
            return level;
        }
        return 0;
    }
}
