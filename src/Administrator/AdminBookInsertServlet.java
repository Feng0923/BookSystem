package Administrator;

import Bean.Book;
import Manager.AdminManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminBookInsertServlet" ,urlPatterns = "/bookInsert")
public class AdminBookInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");


        try {
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            double prize = Double.parseDouble(request.getParameter("prize"));
            int count = Integer.parseInt(request.getParameter("count"));

            AdminManager manager = new AdminManager();
            Book book = new Book(isbn, title, prize, author, count);

            boolean b = manager.insertBook(book);

            response.getWriter().print("true");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NumberFormatException e){
            response.getWriter().print("false");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
