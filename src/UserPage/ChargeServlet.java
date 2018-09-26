package UserPage;

import Manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ChargeServlet" ,urlPatterns = "/charge")
public class ChargeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset = utf-8");

        String user_id = request.getParameter("user_id");
        String money = request.getParameter("money");
        try {
            UserManager manager = new UserManager();
            boolean charge = manager.charge(user_id, money);
            if (charge){
                response.getWriter().print("true");
            }
        }catch (ClassCastException e){
            response.getWriter().print("false");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
