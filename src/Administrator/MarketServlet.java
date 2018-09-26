package Administrator;

import Bean.Book;
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

@WebServlet(name = "MarketServlet" ,urlPatterns = "/market")
public class MarketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("market");
        String requestParameter = request.getParameter("request");
        if (requestParameter.equals("all")){
            AdminManager manager = new AdminManager();
            try {
                ArrayList<Book> books = manager.marketShow();
                String json = new Gson().toJson(books);
                response.getWriter().print(json);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
