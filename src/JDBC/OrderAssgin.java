package JDBC;

import Const.ShopState;
import com.google.gson.JsonObject;

import java.sql.SQLException;

public class OrderAssgin extends Manager{
    public OrderAssgin(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String order_number = jsonObject.get("order_number").getAsString();
        String sql = "update morder set state =  \""+ ShopState.ASSIGNNED+"\"  where order_number = \""+order_number+"\"";
        control.update(sql);
//        control.commit();
        return true;
    }
}
