package am.vtc.chat.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(filterName = "AuthFilterAdmin",
        urlPatterns = {"/homeAdmin", "/addProduct", "/logoutAdmin", "/registerShipper"})
public class AuthFilterAdmin implements Filter {


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }


  @Override
  public void doFilter(ServletRequest servletRequest,
                       ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    System.out.println(request.getSession().getAttribute("admin"));
    if (request.getSession().getAttribute("admin") == null) {
      System.out.println("//////" + request.getSession().getAttribute("admin"));
      response.sendRedirect("/loginAdmin");
      return;
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
