package am.vtc.chat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logoutShipper")
public class LogoutServletShipper extends BaseServletShipper {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getSession().removeAttribute("shipper");
    resp.sendRedirect("/loginShipper");
  }
}
