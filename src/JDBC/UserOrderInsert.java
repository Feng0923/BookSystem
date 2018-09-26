package JDBC;

import Const.RandomFactory;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.Date;


public class UserOrderInsert extends Manager implements InsertAble {
    public UserOrderInsert(Database control) {
        super(control);
    }

    @Override
    public boolean insert(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        String user_id = jsonObject.get("user_id").getAsString();
        String isbn = jsonObject.get("isbn").getAsString();
        int count = jsonObject.get("count").getAsInt();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        String sql = "select prize from book where isbn = "+isbn;
        ResultSet query = control.query(sql);
        double prize = 0;
        if (query.next()){
            prize = query.getDouble("prize");
        }
        sql = "select money,discount_size from user,discount where id = \""+user_id+"\" and user.level = discount.level";
        ResultSet resultSet = control.query(sql);
        double i = -1;
        System.out.println(prize);
        double amount = 0 ;
        while (resultSet.next()){
            double discount_size = resultSet.getDouble("discount_size");
            amount = prize*count*discount_size;
            i =  (resultSet.getInt("money") - amount);
        }
        if (i<0){
            return false;
        }
//        control.commit();

        JDBCConnected connected = new MySQLConnection();
        Connection connection = connected.getConnection();
        connection.setAutoCommit(false);
        String order_number = RandomFactory.getOrderIdByUUId();
        sql = "insert into morder(user_id,isbn,time,order_number,count,amount) values(\""+user_id+"\",\""+isbn+"\",\""+time+"\",\""+order_number+"\",\""+count+"\",\""+amount+"\");";
//                + "update book set rest = rest - 1 where isbn = \""+isbn+"\"";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        sql = "update book set rest = rest - "+count+" where isbn = \""+isbn+"\"";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
        preparedStatement1.executeUpdate();
        preparedStatement.close();
        preparedStatement1.close();
        sql = "update user set money = \""+i*count+"\" where id = \""+user_id+"\"";
        connection.prepareStatement(sql).executeUpdate();
        connection.commit();
        connection.close();
        return true;
    }
}
