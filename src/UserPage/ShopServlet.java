package UserPage;


import Manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ShopServlet",urlPatterns = "/shop")
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("shop");
        String isbn = request.getParameter("isbn");
        int count = Integer.parseInt(request.getParameter("count"));
        String user_id = request.getParameter("user_id");
        try {

            UserManager userManager = new UserManager();
            boolean b = userManager.addOrder(user_id,isbn,count);
            if(b){
                PrintWriter writer = response.getWriter();
                writer.print("true");
//                writer.close();
            }else{
                PrintWriter writer = response.getWriter();
                writer.print("false");
//                writer.close();
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
