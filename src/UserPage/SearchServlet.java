package UserPage;

import Bean.Book;
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

@WebServlet(name = "SearchServlet" ,urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        System.out.println("search");
        String request1 = request.getParameter("request");
        ArrayList<Book> books = null;
        try {
            UserManager manager = new UserManager();
            if (request1.equals("all")){
                System.out.println("all");
                books = manager.bookShow();
            }else if (request1.equals("target")){
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                if (!title.isEmpty()){
                    if (!author.isEmpty()){
                        books = manager.findBookOnTitleAndAuthor(title,author);
                    }else{
                        books = manager.findBookOnTitle(title);
                    }
                }else if(!author.isEmpty()){
                    System.out.println(author);
                    books = manager.findBookOnAuthor(author);
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(books);
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
