package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderQueryByNumber extends Manager {
    public OrderQueryByNumber(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String order_number = jsonObject.get("order_number").getAsString();
        String sql = "select order_number,user_id,time,state,count,book.isbn,title,prize,count,amount,user.name,user.phone,user.address " +
                "from morder,book,user " +
                "where user.id = morder.user_id and morder.isbn = book.isbn and order_number=\"% "+order_number+"%\"";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
