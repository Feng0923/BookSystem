package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户订单查询实现类
 */
public class UserOrderManager extends Manager {

    public UserOrderManager(Database control) {
        super(control);
    }

    /**
     * 返回购买book的, isbn,time,state的ResultSet
     * @param jsonObject user_id:用户帐号
     * @return 返回该用户的订单信息
     */

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        String user_id = jsonObject.get("user_id").getAsString();
        String sql = "select book.isbn,title,author,prize,time,state,order_number,count,amount from morder,book where morder.isbn = book.isbn and user_id = \""+user_id+"\"";
        resultSet = control.query(sql);
//        control.commit();
        return resultSet;
    }
}
