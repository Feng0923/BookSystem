package Const;

public final class ShopState {
    public static int NOT_ASSIGN = 0;
    public static int ASSIGNNED = 1; // 订单未完成
    public static int COMPLETED = 2; // 订单完成
    public static int REFUNDING = 3; //退款中
    public static int REFUNDED = 4; //退款完成
}
