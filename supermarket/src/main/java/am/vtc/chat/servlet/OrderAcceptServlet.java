package am.vtc.chat.servlet;

import am.vtc.chat.model.Shipper;
import am.vtc.chat.model.Shipper_User;
import am.vtc.chat.model.User;
import am.vtc.chat.service.Shipper_userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/orderAccept")
public class OrderAcceptServlet extends HttpServlet {
    private Shipper_userService shipper_userService;

    @Override
    public void init() {
        this.shipper_userService = new Shipper_userService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/orderAccept.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("shipperUserId") != null) {
            int shipperUserId = Integer.parseInt(req.getParameter("shipperUserId"));
            try {
                this.shipper_userService.updateWasShipped(shipperUserId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("WEB-INF/orderAccept.jsp").forward(req, resp);
        } else {
            int shipperId = (int) req.getSession().getAttribute("shipperId");
            Shipper shipper = new Shipper();
            shipper.setId(shipperId);
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = new User();
            user.setId(userId);
            Shipper_User shipper_user = new Shipper_User();
            shipper_user.setShipper(shipper);
            shipper_user.setUser(user);
            try {
                shipper_userService.save(shipper_user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("WEB-INF/homeShipper.jsp").forward(req, resp);
        }
    }
}
