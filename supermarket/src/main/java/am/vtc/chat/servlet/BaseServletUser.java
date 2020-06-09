package am.vtc.chat.servlet;

import am.vtc.chat.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServletUser extends HttpServlet {
    protected ProductService productService;

    @Override
    public void init() throws ServletException {
        this.productService = new ProductService();
    }
}
