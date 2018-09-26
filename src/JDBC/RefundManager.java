package JDBC;

import Const.ShopState;
import com.google.gson.JsonObject;

import java.sql.SQLException;

public class RefundManager extends Manager {
    public RefundManager(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String order_number = jsonObject.get("order_number").toString();
        System.out.println(order_number);
        String sql = "update morder set state = \""+ ShopState.REFUNDING+"\" where order_number = "+order_number;
        control.update(sql);
        return true;
    }
}
