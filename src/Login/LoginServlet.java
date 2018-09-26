package Login;

import Const.Level;
import Manager.UserManager;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet" ,urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("utf-8 ");
        System.out.println("login");
        String id = request.getParameter("user_id");
        String password = request.getParameter("password");
//        System.out.println(id);
        if (!id.isEmpty()&&!password.isEmpty()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("user_id",id);
            jsonObject.addProperty("password",password);
            try {
                UserManager userManager = new UserManager();
                int verify = userManager.verify(jsonObject);
                if (verify > 0){
                    if (verify == Level.ADMIN){
                        Cookie cookie = new Cookie("admin_id", id);
                        response.getWriter().print("admin");
                    }else if (verify > Level.ADMIN){
                        response.getWriter().print("user");
//                        request.getRequestDispatcher("user_information.jsp").forward(request,response);
                    }
                }else {
                    PrintWriter writer = response.getWriter();
                    writer.print("false");
                    writer.close();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("login");
        doPost(request,response);
    }
}
