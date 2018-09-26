package JDBC;

import Const.ShopState;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Refunding extends Manager{

    public Refunding(Database control) {
        super(control);
    }

    @Override
    public ResultSet query(JsonObject jsonObject) throws SQLException, ClassNotFoundException {

        String sql = "select order_number,user_id,time,state,count,book.isbn,title,prize,count,author,amount,user.name,user.phone,user.address from morder,book,user where user.id = morder.user_id and state = "+ ShopState.REFUNDING+" and morder.isbn = book.isbn";
        ResultSet query = control.query(sql);
        return query;
    }
}
