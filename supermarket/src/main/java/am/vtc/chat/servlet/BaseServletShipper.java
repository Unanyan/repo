package am.vtc.chat.servlet;

import am.vtc.chat.service.Service;
import am.vtc.chat.service.impl.ShipperServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServletShipper extends HttpServlet {

  protected ShipperServiceImpl shipperService;

  @Override
  public void init() throws ServletException {
    this.shipperService = new ShipperServiceImpl();
  }
}
