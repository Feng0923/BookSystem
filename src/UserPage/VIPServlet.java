package UserPage;

import Manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VIPServlet" ,urlPatterns = "/vip")
public class VIPServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String user_id = request.getParameter("user_id");
        try {
            UserManager manager = new UserManager();
            boolean up = manager.up(user_id);
            if (up){
                response.getWriter().print("true");
            }else{
                response.getWriter().print("false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().print("false");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().print("false");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
