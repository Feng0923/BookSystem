package Manager;

import Bean.Book;
import Bean.Order;
import Bean.User;
import JDBC.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.util.ArrayList;

public class UserManager {
    private Manager manager;
    private Database control;
    public UserManager() throws SQLException, ClassNotFoundException {
        control = new DataManager(new MySQLConnection());
    }

    public int verify(JsonObject jsonObject) throws SQLException, ClassNotFoundException {
        manager = new UserVerify(control);
        int verify = manager.verify(jsonObject);
        control.close();
        return verify;
    }


    public boolean register(String id,String password,String name,String address,String phone) throws SQLException, ClassNotFoundException {
        manager = new UserInsert(control);
        JsonObject userjson = new JsonObject();
        userjson.addProperty("user_id",id);
        userjson.addProperty("password",password);
        userjson.addProperty("name",name);
        userjson.addProperty("address", address);
        userjson.addProperty("phone",phone);
        boolean insert = manager.insert(userjson);
        control.close();
        return insert;
    }
    /**
     *
     * @param   user_id:用户帐号
     * @return 返回该用户的订单信息 order:订单
     * order;[{isbn,title,author,prize,time,state}]
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Order> findOrder(String user_id) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        manager = new UserOrderManager(control);
        jsonObject.addProperty("user_id",user_id);
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet resultSet = this.manager.query(jsonObject);
        JsonArray array = new JsonArray();
        while (resultSet.next()){
            String isbn = resultSet.getString("isbn");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            double prize = resultSet.getDouble("prize");
            String time = resultSet.getString("time");
            int state = resultSet.getInt("state");
            int count = resultSet.getInt("count");
            String order_number = resultSet.getString("order_number");
            double amount = resultSet.getDouble("amount");
            Order order = new Order(user_id, isbn, title, author, time, prize, state, order_number,count,amount);
            orders.add(order);
        }

        control.close();
        return orders;
    }

    public boolean addOrder(String user_id,String isbn,int count) throws SQLException, ClassNotFoundException {
//        System.out.println("success");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isbn",isbn);
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("count",count);
        manager = new UserOrderInsert(control);
        boolean insert = manager.insert(jsonObject);
        control.close();
        return insert;
    }

    public void submitSuggestion(String user_id,String suggestion) throws SQLException, ClassNotFoundException {
        manager = new UserSuggestionInsert(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("content",suggestion);
        manager.insert(jsonObject);
        control.close();

    }

    public boolean refund(String order_number) throws SQLException, ClassNotFoundException {
        manager = new RefundManager(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_number",order_number);
        boolean insert = manager.insert(jsonObject);
        control.close();
        return insert;
    }

    public void finishOrder(String order_number) throws SQLException, ClassNotFoundException {
        manager = new FinishOrder(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_number",order_number);
        manager.insert(jsonObject);
        control.close();
    }

    public ArrayList<Book> bookShow() throws SQLException, ClassNotFoundException {
        manager = new BookShow(control);
        ResultSet query = manager.query(null);
        ArrayList<Book> bookList = getBookList(query);
        return bookList;
    }
    private ArrayList<Book> getBookList( ResultSet query) throws SQLException {
        ArrayList<Book> arrayList = new ArrayList<>();
        while (query.next()){
            String isbn = query.getString("isbn");
            String author = query.getString("author");
            double prize = query.getDouble("prize");
            String title = query.getString("title");
            int rest = query.getInt("rest");
            if (rest == 0){
                continue;
            }
            Book book = new Book(isbn, title, prize, author, rest);
            arrayList.add(book);
        }
        return arrayList;
    }
    public User getInformation(String user_id) throws SQLException, ClassNotFoundException {
        manager = new UserInformationManager(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        User user = null;
        ResultSet resultSet = manager.query(jsonObject);
        while (resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            double money = resultSet.getDouble("money");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            int level = resultSet.getInt("level");
            user = new User(id,"**",name,phone,address,money,level);
        }
        control.close();
        return user;
    }
    /**
     *
     * @param  title:书名
     * @return
     * book[{isbn：isbn，title:书名，author:作者,prize: 价格, rest: 剩余量}]
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Book> findBookOnTitle(String title) throws SQLException, ClassNotFoundException {
        manager = new BookTitleManager(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title",title);
        ResultSet resultSet = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(resultSet);
        control.close();
        return bookList;
    }

    public ArrayList<Book> findBookOnAuthor(String author) throws SQLException, ClassNotFoundException {
        manager = new BookAuthorManager(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("author",author);
        ResultSet resultSet = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(resultSet);
        control.close();
        return bookList;
    }

    public ArrayList<Book> findBookOnTitleAndAuthor(String title, String author) throws SQLException, ClassNotFoundException {
        manager = new BookTitleAuthorManager(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title",title);
        jsonObject.addProperty("author",author);
        ResultSet resultSet = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(resultSet);
        control.close();
        return bookList;
    }

    private JsonObject findBook(ResultSet resultSet) throws SQLException {
        JsonObject object = new JsonObject();
        JsonArray array = new JsonArray();
        while (resultSet.next()){
            String isbn = resultSet.getString("isbn");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String prize = resultSet.getString("prize");
            String rest = resultSet.getString("rest");
            JsonObject book = new JsonObject();
            book.addProperty("isbn",isbn);
            book.addProperty("title",title);
            book.addProperty("author",author);
            book.addProperty("prize",prize);
            book.addProperty("rest",rest);
            array.add(book);
        }
        object.add("book",array);
        return object;
    }

    public boolean rewrite(String user_id, String password, String name, String address, String phone) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("password",password);
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("phone",phone);

        manager = new UserInformationRewrite(control);
        boolean insert = manager.insert(jsonObject);
        return insert;
    }

    public boolean charge(String user_id, String money) throws SQLException,ClassCastException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        jsonObject.addProperty("money",money);
        manager = new UserCharge(control);
        boolean insert = manager.insert(jsonObject);
        return insert;
    }

    public boolean up(String user_id) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id",user_id);
        manager = new VIP(control);
        boolean insert = manager.insert(jsonObject);
        return insert;
    }
}
