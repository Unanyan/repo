package am.vtc.chat.servlet;

import am.vtc.chat.model.User;
import am.vtc.chat.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order")
public class ServletOrder extends HttpServlet {
    UserService userService;

    @Override
    public void init() throws ServletException{
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phoneNumber");
        String adress = req.getParameter("adress");
        int oldUserId = (int)req.getSession().getAttribute("idUser");
        User newUser = new User();
        newUser.setPhoneNumber(Long.parseLong(phoneNumber));
        newUser.setAdress(adress);
        newUser.setName(name);

        try {
            userService.update(oldUserId, newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getSession().setAttribute("orderSuccesfuly", "The order was successfully accepted");
        resp.sendRedirect("/homeUser");
    }
}
