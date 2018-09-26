package Login;

import Manager.UserManager;
import com.google.gson.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet",urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            String id = request.getParameter("user_id");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            if (!id.isEmpty()&&!password.isEmpty()&&!name.isEmpty()){
                UserManager userManager = new UserManager();
                boolean register = userManager.register(id,password,name,address,phone);
                if (register){
                    response.getWriter().print("true");
                }else {
                    response.getWriter().print("false");
                }
            }
        } catch (SQLException e) {
            response.getWriter().print("false");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("register");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user_register.jsp");
        requestDispatcher.forward(request,response);
    }
}
