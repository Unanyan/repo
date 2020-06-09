package am.vtc.chat.servlet;

import am.vtc.chat.model.Product;
import am.vtc.chat.servlet.util.RequestValidator;
import am.vtc.chat.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends BaseServletUser {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/addProduct.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    try {
        RequestValidator<Product> validator = validate(req);
        if (validator.isValid()) {
            Product product = validator.getEntity();


                this.productService.save(product);

                req.getSession().setAttribute("successfullyAddProduct", "Product successfully add.");
            resp.sendRedirect("/homeAdmin");
                return;
        }
        req.getRequestDispatcher("WEB-INF/addProduct.jsp").forward(req, resp);
    } catch (Exception e) {
        e.printStackTrace();
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}


    private static RequestValidator<Product> validate(HttpServletRequest req) {

        boolean isValid = true;
        String name = req.getParameter("nameProduct");
        if (DataValidator.isEmpty(name)) {
            req.setAttribute("errorNameProduct", "Name product is Required!");
            isValid = false;
        }

        String imagePath = req.getParameter("imagePathProduct");
        if (DataValidator.isEmpty(imagePath)) {
            req.setAttribute("errorImagePathProduct", "Image path product is Required!");
            isValid = false;
        }

        String aboutProduct = req.getParameter("aboutProduct");
        if (DataValidator.isEmpty(aboutProduct)) {
            req.setAttribute("erroraboutProduct", "about Product is Required!");
            isValid = false;
        }

        String type = req.getParameter("typeProduct");
        if (DataValidator.isEmpty(type)) {
            req.setAttribute("errorTypeProduct", "Product type is Required!");
            isValid = false;
        }

        String count = req.getParameter("countProduct");
        if (DataValidator.isEmpty(count)) {
            req.setAttribute("errorCountProduct", "Product count is required!");
            isValid = false;
        }

        String weight = req.getParameter("weightProduct");
        if (DataValidator.isEmpty(count)) {
            req.setAttribute("errorWeightProduct", "Product weight is required!");
            isValid = false;
        }

        String price = req.getParameter("priceProduct");
        if (DataValidator.isEmpty(count)) {
            req.setAttribute("errorPriceProduct", "Product price is required!");
            isValid = false;
        }

        RequestValidator<Product> validator = new RequestValidator<>();
        validator.setValid(isValid);
        if (isValid) {
            Product product = new Product();
            product.setName(name);
            product.setImagePath(imagePath);
            product.setAboutProduct(aboutProduct);
            product.setType(type);
            product.setCount(Integer.parseInt(count));
            product.setWeight(Integer.parseInt(weight));
            product.setPrice(Long.parseLong(price));

            validator.setEntity(product);
        }
        return validator;
    }
}
