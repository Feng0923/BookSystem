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

@WebServlet(name = "AdminBookListServlet" ,urlPatterns = "/bookList")
public class AdminBookListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        AdminManager adminManager = new AdminManager();
        ArrayList<Book> books = null;
        String requestData = request.getParameter("request");
        try {
            if (requestData.equals("all")){
                books = adminManager.showALL();
            }else if(requestData.equals("lack")){
                System.out.println("lack");
                books = adminManager.lackBook();
            }else{

                String isbn = request.getParameter("isbn");
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                if (isbn.isEmpty()){
                    if (title.isEmpty()){
                        if (author.isEmpty()){
                            books = adminManager.showALL();
                        }else{
                            books = adminManager.findBookByAuthor(author);
                        }
                    }else{
                        if (author.isEmpty()){
                            books = adminManager.findBookByTitle(title);
                        }else{
                            books = adminManager.findBook(title,author);
                        }
                    }
                }else{
                    books = adminManager.findBookByISBN(isbn);
                }
            }
            Gson gson = new Gson();
            String json = gson.toJson(books);
            System.out.println(json);
            response.getWriter().print(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin_bookList.jsp").forward(request,response);
    }
}
