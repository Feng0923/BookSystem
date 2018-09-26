package UserPage;

import Bean.User;
import JDBC.Manager;
import Manager.UserManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "InformationServlet" ,urlPatterns = "/information")
public class InformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("information");
        String requestParameter = request.getParameter("request");
        try {
            UserManager manager = new UserManager();
            String user_id = request.getParameter("user_id");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            if (requestParameter.equals("rewrite")){
                boolean rewrite = manager.rewrite(user_id, password, name, address, phone);
                response.getWriter().print(rewrite);
            }else if (requestParameter.equals("all")){

                User information = manager.getInformation(user_id);
                String json = new Gson().toJson(information);
                response.getWriter().print(json);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
