package am.vtc.chat.servlet;

import am.vtc.chat.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/homeUser", "/"})
public class HomeServletUser extends BaseServletUser{

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> products = super.productService.findAll(100);
            req.getSession().setAttribute("products", products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/homeUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOfProduct = req.getParameter("typeProduct");
        try {
            List<Product> products = super.productService.findByType(typeOfProduct);
            req.getSession().setAttribute("products", products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/homeUser.jsp").forward(req, resp);
    }
}
