package JDBC;

import Const.ShopState;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.SQLException;

public class Refunded extends Manager {

    public Refunded(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String order_number = jsonObject.get("order_number").toString();

        JDBCConnected jdbcConnected = new MySQLConnection();
        Connection connection = jdbcConnected.getConnection();
        connection.setAutoCommit(false);
        System.out.println(order_number);
        String sql = "update morder set state = \""+ ShopState.REFUNDED+"\" where state = "+ShopState.REFUNDING+" and order_number = "+order_number;
        connection.prepareStatement(sql).executeUpdate();
        sql = "update user,morder set money = money+morder.amount where user.id = morder.user_id and order_number = "+order_number;
        connection.prepareStatement(sql).executeUpdate();
        connection.commit();
        connection.close();
        return true;

    }
}
