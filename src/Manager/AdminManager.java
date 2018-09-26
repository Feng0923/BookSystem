package Manager;

import Bean.Book;
import Bean.Discount;
import Bean.Order;
import Bean.Suggestion;
import JDBC.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminManager {
    private Manager manager;
    private Database control;


    public AdminManager(){
        control = new DataManager(new MySQLConnection());
    }


    public ArrayList<Book> lackBook() throws SQLException, ClassNotFoundException {
        manager = new BookLack(control);
        ResultSet query = manager.query(null);
        ArrayList<Book> bookList = getBookList(query);
        control.close();
        return bookList;
    }
    public ArrayList<Book> showALL() throws SQLException, ClassNotFoundException {
        manager = new BookShow(control);
        ArrayList<Book> list = new ArrayList<>();
        ResultSet query = manager.query(null);
        while (query.next()){
            String isbn = query.getString("isbn");
            String title = query.getString("title");
            String author = query.getString("author");
            double prize = query.getDouble("prize");
            int rest = query.getInt("rest");
            Book book = new Book(isbn, title, prize, author, rest);
            list.add(book);
        }
        control.close();
        return list;
    }

    public boolean insertBook(Book book) throws SQLException, ClassNotFoundException {
        Gson gson = new Gson();
        JsonObject asJsonObject = gson.toJsonTree(book).getAsJsonObject();
//        System.out.println(asJsonObject);
        manager = new BookInsert(control);
        boolean insert = manager.insert(asJsonObject);

        control.close();
        return true;
    }

    public ArrayList<Order> orderShow() throws SQLException, ClassNotFoundException {
        manager = new OrderShow(control);
        ResultSet query = manager.query(null);
        ArrayList<Order> orders = getOrders(query);
        control.close();
        return orders;
    }

    public ArrayList<Order> getOrderNotAssign() throws SQLException, ClassNotFoundException {
        manager = new OrderQueryNotAssign(control);
        ResultSet query = manager.query(null);
        ArrayList<Order> orders = getOrders(query);
        control.close();
        return orders;
    }
    public boolean assgin(String order_number) throws SQLException, ClassNotFoundException {
        manager = new OrderAssgin(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_number",order_number);
        manager.insert(jsonObject);
        control.close();
        return true;
    }
    private ArrayList<Order> getOrders(ResultSet query) throws SQLException {
        ArrayList<Order> array = new ArrayList<>();
        while (query.next()){
            String order_number = query.getString("order_number");
            String isbn = query.getString("isbn");
            String title = query.getString("title");
            String author = query.getString("author");
            String time = query.getString("time");
            int state = query.getInt("state");
            int count = query.getInt("count");
            String user_id = query.getString("user_id");
            double prize = query.getDouble("prize");
            double amount = query.getDouble("amount");
            String name = query.getString("name");
            String phone = query.getString("phone");
            String address = query.getString("address");
            Order order = new Order(user_id, isbn, title, author, time, prize, state, order_number,count,amount,name,phone,address);
            array.add(order);
        }
        return array;
    }

    public ArrayList<Order> orderByNumber(String order_number) throws SQLException, ClassNotFoundException {
        manager = new OrderQueryByNumber(control);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_number",order_number);
        ResultSet query = manager.query(jsonObject);
        ArrayList<Order> orders = getOrders(query);
        control.close();
        return orders;
    }

    public ArrayList<Suggestion> suggestionsShow() throws SQLException, ClassNotFoundException {
        manager = new SuggestionShow(control);
        ResultSet query = manager.query(null);
        ArrayList<Suggestion> arrayList = new ArrayList<>();
        while (query.next()){
            String user_id = query.getString("user_id");
            String content = query.getString("content");
            String time = query.getString("time");
            Suggestion suggestion = new Suggestion(user_id, content,time);
            arrayList.add(suggestion);
        }
        control.close();

        return arrayList;
    }

    public ArrayList<Book> findBookByTitle(String title) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("title",title);
        manager = new BookTitleManager(control);
        ResultSet query = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(query);
        control.close();

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
            Book book = new Book(isbn, title, prize, author, rest);
            arrayList.add(book);
        }
        return arrayList;
    }

    public ArrayList<Book> findBookByAuthor(String author) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("author",author);
        manager = new BookAuthorManager(control);
        ResultSet query = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(query);
        control.close();

        return bookList;
    }

    public ArrayList<Book> findBook(String title,String author) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("author",author);
        jsonObject.addProperty("title",title);
        manager = new BookTitleAuthorManager(control);
        ResultSet query = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(query);
        control.close();

        return bookList;
    }

    public ArrayList<Book> findBookByISBN(String isbn) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("isbn",isbn);
        manager = new BookISBN(control);
        ResultSet query = manager.query(jsonObject);
        ArrayList<Book> bookList = getBookList(query);
        control.close();
        return bookList;
    }

    public void refund(String order_number) throws SQLException, ClassNotFoundException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("order_number",order_number);
        manager = new Refunded(control);
        manager.insert(jsonObject);
    }

    public ArrayList<Order> getOrderRefunding() throws SQLException, ClassNotFoundException {
        manager = new Refunding(control);
        ResultSet query = manager.query(null);
        ArrayList<Order> orders = getOrders(query);
        return orders;
    }

    public ArrayList<Discount> discountShow() throws SQLException, ClassNotFoundException {
        manager = new DiscountShow(control);
        ResultSet query = manager.query(null);
        ArrayList<Discount> discounts = new ArrayList<>();
        while (query.next()){
            int level = query.getInt("level");
            double discount_size = query.getDouble("discount_size");
            Discount discount = new Discount(level, discount_size);
            discounts.add(discount);
        }
        return discounts;
    }

    public void rewriteDiscount(String discount0, String discount1) throws SQLException, ClassNotFoundException,NumberFormatException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("discount0",discount0);
        jsonObject.addProperty("discount1",discount1);

        manager = new DiscountRewrite(control);
        manager.insert(jsonObject);
    }

    public ArrayList<Book> marketShow() throws SQLException, ClassNotFoundException {
        manager = new MarketShow(control);
        ResultSet query = manager.query(null);
        ArrayList<Book> books = new ArrayList<>();
        while (query.next()){
            String isbn = query.getString("isbn");
            String title = query.getString("title");
            String author = query.getString("author");
            double prize = query.getDouble("prize");
            int sum = query.getInt("sum");
            Book book = new Book(isbn, title, author,prize, sum);
            books.add(book);
        }
        return books;
    }
}
