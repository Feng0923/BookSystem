package Administrator;

import Bean.Discount;
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

@WebServlet(name = "DiscountServlet" ,urlPatterns = "/discount")
public class DiscountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String requestParameter = request.getParameter("request");
        try {
            if (requestParameter.equals("all")){
                AdminManager manager = new AdminManager();
                ArrayList<Discount> discounts = manager.discountShow();
                String json = new Gson().toJson(discounts);
                response.getWriter().print(json);
            }else if(requestParameter.equals("save")){
                String discount0 = request.getParameter("discount0");
                String discount1 = request.getParameter("discount1");
                AdminManager manager = new AdminManager();
                manager.rewriteDiscount(discount0,discount1);
                response.getWriter().print("true");
            }
        } catch (NumberFormatException e){
            response.getWriter().print("number");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
