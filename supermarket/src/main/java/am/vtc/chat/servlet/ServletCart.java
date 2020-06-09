package am.vtc.chat.servlet;

import am.vtc.chat.model.*;
import am.vtc.chat.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cart")
public class ServletCart extends HttpServlet {
    private UserService userService;
    private CartService cartService;
    private ShopingService shopingService;
    private User_CartService user_cartService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserService();
        this.cartService = new CartService();
        this.shopingService = new ShopingService();
        this.user_cartService = new User_CartService();
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*int idUser = (int)req.getSession().getAttribute("idUser");

            System.out.println("id is : "+ idUser);*/

        req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
    }


    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            if(req.getSession().getAttribute("idUser") == null) {
                this.userService.save(user);
                user.setId(this.userService.getLastIntex());
                req.getSession().setAttribute("idUser", this.userService.getLastIntex());
            } else {
                user.setId((int)req.getSession().getAttribute("idUser"));
            }
            Shoping shoping = new Shoping();
            shoping.setCount(Integer.parseInt(req.getParameter("countProduct")));
            shoping.setProduct(productService.findById(Integer.parseInt(req.getParameter("productId"))).get());
            this.shopingService.save(shoping);
            shoping.setId(this.shopingService.getLastIntex());
            Cart cart = new Cart();
            cart.setShoping(shoping);
            cart.setLastTimeToAdd(System.currentTimeMillis());
            if(req.getParameter("isFromCart") != null) {
                System.out.println("from cart");
                cartService.updateNotSum(cart, user, shoping);
                req.getRequestDispatcher("WEB-INF/cart.jsp").forward(req, resp);
            } else {
                boolean isSave = this.cartService.save(cart, user, shoping);
                cart.setId(this.cartService.getLastIndex());
                if (isSave) {
                    User_Cart user_cart = new User_Cart();
                    user_cart.setUser(user);
                    user_cart.setCart(cart);
                    this.user_cartService.save(user_cart);
                }
                req.getRequestDispatcher("WEB-INF/homeUser.jsp").forward(req, resp);
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
