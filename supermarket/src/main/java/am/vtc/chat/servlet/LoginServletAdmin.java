package am.vtc.chat.servlet;

import am.vtc.chat.model.Admin;
import am.vtc.chat.servlet.util.RequestValidator;
import am.vtc.chat.util.DataValidator;
import am.vtc.chat.util.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/loginAdmin")
public class LoginServletAdmin extends BaseServletAdmin{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            RequestValidator<LoginPayload> validator = LoginServletAdmin.validate(req);
            if (validator.isValid()) {
                Optional<Admin> admin = super.adminService.getEntity(validator.getEntity().emailAdmin, validator.getEntity().passwordAdmin);
                if (!admin.isEmpty()) {
                    req.getSession().setAttribute("admin", admin.get());
                    resp.sendRedirect("/homeAdmin");
                    return;
                }
                req.setAttribute("errorEmailPasswordAdmin", "Incorrect Email/Password for Admin.");
            }
            req.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private static RequestValidator<LoginPayload> validate(HttpServletRequest req) {

        boolean isValid = true;

        String emailAdmin = req.getParameter("emailAdmin");
        if (DataValidator.isEmpty(emailAdmin)) {
            req.setAttribute("errorEmailAdmin", "Email is Required Admin!");
            isValid = false;
        }

        String passwordAdmin = req.getParameter("passwordAdmin");
        if (DataValidator.isEmpty(passwordAdmin)) {
            req.setAttribute("errorPasswordAdmin", "Password is Required Admin!");
            isValid = false;
        }
        RequestValidator<LoginPayload> validator = new RequestValidator<>();
        validator.setValid(isValid);
        if (isValid) {
            LoginPayload loginPayload = new LoginPayload();
            loginPayload.emailAdmin = emailAdmin;
            loginPayload.passwordAdmin = EncryptionUtil.encrypt(passwordAdmin);

            validator.setEntity(loginPayload);
        }
        return validator;
    }

    private static class LoginPayload {
        String emailAdmin;
        String passwordAdmin;
    }
    ///////////////////////////////////////
    /*
      private RequestValidator<User> validate(HttpServletRequest req) {
    boolean hasError = false;
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    if (DataValidator.isEmpty(email)) {
      req.setAttribute("errorEmail", "Email is required!");
      hasError = true;
    }
    if (DataValidator.isEmpty(password)) {
      req.setAttribute("errorPassword", "Password is required!");
      hasError = true;
    }
    RequestValidator<User> requestValidator = new RequestValidator<>();
    if (!hasError) {
      User user = new User();
      user.setEmail(email);
      user.setPassword(EncryptionUtil.encrypt(password));
      requestValidator.setValue(user);
    } else {
      requestValidator.setHasError(true);
    }
    return requestValidator;
  }
   */
}
