package JDBC;

import Const.ShopState;
import com.google.gson.JsonObject;

import java.sql.SQLException;

public class FinishOrder extends Manager {
    public FinishOrder(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String order_number = jsonObject.get("order_number").getAsString();
        String sql = "update morder set state ="+ ShopState.COMPLETED+" where state = "+ShopState.ASSIGNNED+" and order_number=\""+order_number+"\"";
        control.update(sql);
//        control.commit();
        return true;
    }
}
