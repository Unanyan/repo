package am.vtc.chat.servlet;

import am.vtc.chat.service.Service;
import am.vtc.chat.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServletAdmin extends HttpServlet {
    protected Service adminService;

    @Override
    public void init() throws ServletException {
        this.adminService = new AdminServiceImpl();
    }
}
