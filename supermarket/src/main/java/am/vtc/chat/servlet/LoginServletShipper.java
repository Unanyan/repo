package am.vtc.chat.servlet;

import am.vtc.chat.model.Shipper;
import am.vtc.chat.servlet.util.RequestValidator;
import am.vtc.chat.util.DataValidator;
import am.vtc.chat.util.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/loginShipper")
public class LoginServletShipper extends BaseServletShipper {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("WEB-INF/loginShipper.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      RequestValidator<LoginPayload> validator = LoginServletShipper.validate(req);
      if (validator.isValid()) {
        System.out.println(validator.getEntity());
        Optional<Shipper> shipper = super.shipperService.getEntity(validator.getEntity().email, validator.getEntity().password);
        if (!shipper.isEmpty()) {
          req.getSession().setAttribute("shipper", shipper.get());
          req.getSession().setAttribute("shipperId", shipper.get().getId());
          resp.sendRedirect("/homeShipper");
          return;
        }
        req.setAttribute("errorEmailPasswordShipper", "Incorrect Email/Password shipper.");
      }
      req.getRequestDispatcher("WEB-INF/loginShipper.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }


  private static RequestValidator<LoginPayload> validate(HttpServletRequest req) {

    boolean isValid = true;

    String email = req.getParameter("emailShipper");
    if (DataValidator.isEmpty(email)) {
      req.setAttribute("errorEmailShipperLogin", "Email is Required shipper!");
      isValid = false;
    }

    String password = req.getParameter("passwordShipper");
    if (DataValidator.isEmpty(password)) {
      req.setAttribute("errorPasswordShipperLogin", "Password is Required shipper!");
      isValid = false;
    }
    RequestValidator<LoginPayload> validator = new RequestValidator<>();
    validator.setValid(isValid);
    if (isValid) {
      LoginPayload loginPayload = new LoginPayload();
      loginPayload.email = email;
      loginPayload.password = EncryptionUtil.encrypt(password);

      validator.setEntity(loginPayload);
    }
    return validator;
  }

  private static class LoginPayload {
    String email;
    String password;
  }
}
