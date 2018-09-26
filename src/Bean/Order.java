package Bean;

public class Order {
    public static String[] states = {"等待发货","发货中","订单完成","退款中","退款完成"};

    private String order_number;
    private String isbn;
    private String user_id;
    private String title;
    private String author;
    private String time;
    private double prize;
    private int state;
    private int count;
    private double amount;
    private String name;
    private String phone;

    public Order(String user_id , String isbn, String title, String author, String time, double prize,int state,String order_number,int count,double amount, String name, String phone, String address) {
        this.order_number = order_number;
        this.isbn = isbn;
        this.user_id = user_id;
        this.title = title;
        this.author = author;
        this.time = time;
        this.prize = prize;
        this.state = state;
        this.count = count;
        this.amount = amount;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }


    public String getIsbn() {
        return isbn;
    }

    public String getOrder_number() {
        return order_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTime() {
        return time;
    }

    public double getPrize() {
        return prize;
    }


    public String getState() {
        return states[state];
    }


    public Order(String user_id , String isbn, String title, String author, String time, double prize,int state,String order_number,int count,double amount) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.title = title;
        this.author = author;
        this.time = time;
        this.state = state;
        this.prize = prize;
        this.order_number = order_number;
        this.count = count;
        this.amount = amount;
    }
}
