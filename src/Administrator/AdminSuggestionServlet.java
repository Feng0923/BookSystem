package Administrator;

import Bean.Suggestion;
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

@WebServlet(name = "AdminSuggestionServlet" ,urlPatterns = "/admin_suggestion")
public class AdminSuggestionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String requestParameter = request.getParameter("request");
        if (requestParameter.equals("all")){
            AdminManager manager = new AdminManager();
            try {
                ArrayList<Suggestion> arrayList = manager.suggestionsShow();
                Gson gson = new Gson();
                String json = gson.toJson(arrayList);
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
