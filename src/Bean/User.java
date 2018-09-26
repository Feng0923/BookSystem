package Bean;

public class User {
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    private String id;
    private String password;
    private String name;
    private String phone;
    private String address;
    private int level;
    private double money;

    public String getAddress() {
        return address;
    }


    public double getMoney() {
        return money;
    }
    public User(String id, String password, String name,String phone,String address,double money,int level){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.money = money;
        this.level = level;
    }
}
