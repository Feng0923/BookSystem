package JDBC;

import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderShow extends Manager {
    public OrderShow(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String sql = "select order_number,user_id,time,state,count,book.isbn,title,prize,count,author,amount,user.name,user.phone,user.address from morder,book,user where morder.isbn = book.isbn and morder.user_id = user.id";
        ResultSet query = control.query(sql);
//        control.commit();
        return query;
    }
}
