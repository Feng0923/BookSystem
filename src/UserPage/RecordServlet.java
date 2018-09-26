package UserPage;

import Bean.Order;
import Manager.UserManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "RecordServlet" ,urlPatterns = "/record")
public class RecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String user_id = request.getParameter("user_id");
        System.out.println("record");
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user_id",user_id);
            UserManager userManager = new UserManager();
            ArrayList<Order> orderJson = userManager.findOrder(user_id);
            String json = new Gson().toJson(orderJson);
            response.getWriter().print(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
