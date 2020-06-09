<%@ page import="am.vtc.chat.model.Cart" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="am.vtc.chat.service.User_CartService" %>
<%@ page import="am.vtc.chat.repo.User_CartRepoSql" %>
<%@ page import="am.vtc.chat.model.Shoping" %>
<%@ page import="java.util.List" %>
<%@ page import="am.vtc.chat.model.User_Cart" %>
<%@ page import="am.vtc.chat.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: vrezh
  Date: 24.04.20
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    User_CartService user_cartService = new User_CartService();
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>store</title>

    <link rel="icon" href="img/core-img/favicon.ico">

    <link rel="stylesheet" href="resources/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>

<%
    long total = 0;
   /* User_CartService user_cartService = new User_CartService();
        int idUser = (int) request.getSession().getAttribute("idUser");

        System.out.println("id is : " + idUser);
        List<User_Cart> users_carts = null;
        try {
            users_carts = this.user_cartService.findByIdUser(idUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    long total = 0;*/
%>

<div class="main-content-wrapper d-flex clearfix">

    <!-- Mobile Nav (max width 767px)-->
    <div class="mobile-nav">
        <!-- Navbar Brand -->
        <div class="amado-navbar-brand">
            <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
        </div>
        <!-- Navbar Toggler -->
        <div class="amado-navbar-toggler">
            <span></span><span></span><span></span>
        </div>
    </div>

    <!-- Header Area Start -->
    <header class="header-area clearfix">
        <!-- Close Icon -->
        <div class="nav-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <!-- Logo -->
        <div class="logo">
            <a href="index.html"><img src="img/core-img/logo.png" alt=""></a>
        </div>
        <!-- Amado Nav -->
        <nav class="amado-nav">
            <ul>
                <li><a href="/homeUser">Home</a></li>
                <li class="active"><a href="/cart">Cart</a></li>
            </ul>
        </nav>

    </header>
    <!-- Header Area End -->


    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8">
                    <div class="cart-title mt-50">
                        <h1>My Cart</h1>
                    </div>

                    <div class="cart-table clearfix">
                        <table class="table table-responsive">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%if(request.getSession().getAttribute("idUser") != null) {
                                User_CartService user_cartService = new User_CartService();
                                int idUser = (int) request.getSession().getAttribute("idUser");

                                System.out.println("id is : " + idUser);
                                List<User_Cart> users_carts = null;
                                try {
                                    users_carts = this.user_cartService.findByIdUser(idUser);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                for(User_Cart user_cart: users_carts) {
                                    List<Shoping> shopings = user_cart.getCart().getShoping();
                                    for(Shoping shoping: shopings) {
                                        Product product = shoping.getProduct();
                                        int count = shoping.getCount();
                                        total += product.getPrice() * count;
                            %>


                            <tr>
                                <td class="cart_product_img">
                                    <a href="#"><img src="<%=product.getImagePath()%>" alt="Product"></a>
                                </td>
                                <td class="cart_product_desc">
                                    <h4><%=product.getName()%></h4>
                                    <h5>(<%=product.getAboutProduct()%>)</h5>
                                </td>
                                <td class="price">
                                    <span><%=product.getPrice()%>դր</span>
                                </td>
                                <td class="qty">
                                    <div class="qty-btn d-flex">
                                        <!--<p>Qty</p>
                                        <div class="quantity">
                                            <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                            <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                            <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                        </div>-->
                                        <form method="post" action="/cart">
                                            <input type="hidden" id="productId" name="productId" value="<%=product.getId()%>"/>
                                            <input type="hidden" id="isFromCart" name="isFromCart" value="fromCart"/>
                                            <div class="quantity-block">
                                                <input class="quantity-num" size="1" type="number" name="countProduct" min="1" step="1" value="<%=count%>"/>
                                                <input type="submit" value="Ok"/>
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>

                            <%}}}%>

                            </tbody>
                        </table>
                    </div>
                </div>
               <div class="col-12 col-lg-4">
                    <div class="cart-summary">
                        <h5>Cart Total</h5>
                        <ul class="summary-table">
                                 <li><span>total:</span> <span><%=total%>դր</span></li>
                            <%if(request.getSession().getAttribute("idUser") != null) {%>
                                 <li><span>delivery:</span> <span>Free</span></li>
                            <%}%>

                        </ul>
                <%if(request.getSession().getAttribute("idUser") != null) {%>
                        <div class="cart-btn mt-100">
                            <a href="/order" class="btn amado-btn w-100">To Order</a>
                        </div>
                <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>