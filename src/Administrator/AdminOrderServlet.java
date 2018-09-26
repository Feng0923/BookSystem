package Administrator;

import Bean.Order;
import Manager.AdminManager;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminOrderServlet" ,urlPatterns = "/admin_order")
public class AdminOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String requestParameter = request.getParameter("request");
        AdminManager manager = new AdminManager();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            if (requestParameter.equals("all")){
                orders = manager.orderShow();
            }else if (requestParameter.equals("order_number")){
                String order_number = request.getParameter("order_number");
                orders = manager.orderByNumber(order_number);
            }else if(requestParameter.equals("notAssign")){
                orders = manager.getOrderNotAssign();
            }else if(requestParameter.equals("refunding")){
                orders = manager.getOrderRefunding();
            }
            Gson gson = new Gson();
            String json = gson.toJson(orders);
            response.getWriter().print(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
