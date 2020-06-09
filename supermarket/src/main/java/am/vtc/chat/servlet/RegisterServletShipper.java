package am.vtc.chat.servlet;

import am.vtc.chat.model.Shipper;
import am.vtc.chat.servlet.util.RequestValidator;
import am.vtc.chat.util.DataValidator;
import am.vtc.chat.util.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/registerShipper")
public class RegisterServletShipper extends BaseServletShipper {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("WEB-INF/registerShipper.jsp").forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      RequestValidator<Shipper> validator = validate(req);
      if (validator.isValid()) {
        Shipper shipper = validator.getEntity();
        if (this.shipperService.exist(shipper.getEmail())) {
          req.setAttribute("errorEmailShipper", "Shipper Already Exist.");
        } else {
          this.shipperService.save(shipper);
          req.getSession().setAttribute("successfullyForShipper", "Shipper successfully registered.");
          resp.sendRedirect("/homeAdmin");
          return;
        }
      }
      req.getRequestDispatcher("WEB-INF/registerShipper.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }


  private static RequestValidator<Shipper> validate(HttpServletRequest req) {

    boolean isValid = true;
    String name = req.getParameter("name");
    if (DataValidator.isEmpty(name)) {
      req.setAttribute("errorNameShipper", "Name shipper is Required!");
      isValid = false;
    }

    String surname = req.getParameter("surname");
    if (DataValidator.isEmpty(surname)) {
      req.setAttribute("errorSurnameShipper", "Surname shipper is Required!");
      isValid = false;
    }

    String email = req.getParameter("email");
    if (DataValidator.isEmpty(email)) {
      req.setAttribute("errorEmailShipper", "Email is Required!");
      isValid = false;
    } else if(!DataValidator.isValidEmail(email)) {
      req.setAttribute("errorEmailShipper", "Incorrect email format!");
      isValid = false;
    }

    String password = req.getParameter("password");
    if (DataValidator.isEmpty(password)) {
      req.setAttribute("errorPasswordShipper", "Password is Required!");
      isValid = false;
    } else {
      String confirmPassword = req.getParameter("confirmPassword");
      if (!password.trim().equals(confirmPassword)) {
        req.setAttribute("errorConfirmPasswordShipper", "Passwords doesn't match!");
        isValid = false;
      }
    }
    RequestValidator<Shipper> validator = new RequestValidator<>();
    validator.setValid(isValid);
    if (isValid) {
      Shipper shipper = new Shipper();
      shipper.setName(name);
      shipper.setSurname(surname);
      shipper.setEmail(email);
      shipper.setPassword(EncryptionUtil.encrypt(password));

      validator.setEntity(shipper);
    }
    return validator;
  }
}
