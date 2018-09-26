package JDBC;

import Const.Level;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VIP extends Manager {

    public VIP(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String user_id = jsonObject.get("user_id").getAsString();
        String sql = "select money from user where id =  \""+user_id+"\"";
        ResultSet query = control.query(sql);
        double p = -1;
        double money = 0;
        while (query.next()){
            money = query.getDouble("money");
        }

        double v = money - Level.VIP1_COST;
        if (v < 0){
            return false;
        }else {
            System.out.println(v);
            sql = "update user  set money = "+v+",level = "+ Level.VIP1+" where  id = \""+user_id+"\"";
            control.update(sql);
        }

        return true;
    }
}
