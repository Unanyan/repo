<%@ page import="am.vtc.chat.service.User_CartService" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="am.vtc.chat.service.impl.ShipperServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="am.vtc.chat.model.*" %>
<%@ page import="am.vtc.chat.service.Shipper_userService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

<%!User_CartService user_cartService = new User_CartService();
    ShipperServiceImpl shipperService = new ShipperServiceImpl();
    Shipper_userService shipper_userService = new Shipper_userService();
%>
<%Shipper shipper = shipperService.findById((int)request.getSession().getAttribute("shipperId")).get(); %>
<%="Hello " +shipper.getName()%>
<br>

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
                <li class="active"><a href="/orderAccept">Home</a></li>
                <li><a href="/homeShipper">Orders</a></li>
            </ul>
        </nav>

    </header>
    <!-- Header Area End -->


    <div class="cart-table-area section-padding-100">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8">

                    <div class="cart-title mt-50">
                        <h1>My accept orders</h1>
                    </div>
                    <%
                        List<Shipper_User> shipper_users = shipper_userService.findByIdShipper(shipper.getId());
                        Iterator<Shipper_User> shipper_userIterator = shipper_users.iterator();
                        User user;
                        while (shipper_userIterator.hasNext()) {
                            Shipper_User shipper_user = shipper_userIterator.next();
                            if(shipper_user.isWasShipped()){continue;}
                            user = shipper_user.getUser();
                        Iterator<Cart> cartIterator = this.user_cartService.findByIdUserReturnCarts(user.getId()).iterator();
                        /*ArrayList<Integer> usersid = new ArrayList<>();
                        while (iterator.hasNext()) {
                            User_Cart user_cart = iterator.next();
                            User user = user_cart.getUser();
                            if(this.shipper_userService.findByIdUser(user.getId()).size() != 0){continue;}
                            if(user.getName() == null) {continue;}
                            if(usersid.contains(user.getId())) { continue; }
                            usersid.add(user.getId());
                            List<Cart> carts = user_cartService.findByIdUserReturnCarts(user.getId());
                            Iterator<Cart> cartIterator = carts.iterator();*/
                            long total = 0;
                    %>
                    <br>
                    <br>
                    <h4><%=user.getName() +
                            " (" + user.getPhoneNumber() +
                            ", " + user.getAdress() + ")"%>
                    </h4>
                    <form method="post" action="/orderAccept">
                        <input type="hidden"  name="shipperUserId" value="<%=shipper_user.getId()%>"/>
                        <input type="submit" value="was shipped"/>
                    </form>
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

                            <%
                                while (cartIterator.hasNext()) {
                                    Cart cart = cartIterator.next();
                                    List<Shoping> shopings = cart.getShoping();
                                    Iterator<Shoping> shopingIterator = shopings.iterator();
                                    while (shopingIterator.hasNext()){
                                        Shoping shoping = shopingIterator.next();
                                        Product product = shoping.getProduct();
                                        String name = product.getName();
                                        int count = product.getCount();
                                        long price = product.getPrice();
                                        total += count * price;
                            %>


                            <tr>
                                <td class="cart_product_img">
                                    <a href="#"><img src="<%=product.getImagePath()%>" alt="Product"></a>
                                </td>
                                <td class="cart_product_desc">
                                    <h4><%=name%></h4>
                                    <h5>(<%=product.getAboutProduct()%>)</h5>
                                </td>
                                <td class="price">
                                    <span><%=price%>դր</span>
                                </td>
                                <td class="qty">
                                    <div class="qty-btn d-flex">
                                        <p><%=product.getCount()%></p>
                                        <!--<div class="quantity">
                                            <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                            <input type="number" class="qty-text" id="qty" step="1" min="1" max="300" name="quantity" value="1">
                                            <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                        </div>-->
                                        <!--<form method="post" action="/cart">
                                            <input type="hidden" id="productId" name="productId" value="<%=product.getId()%>"/>
                                            <input type="hidden" id="isFromCart" name="isFromCart" value="fromCart"/>
                                            <div class="quantity-block">
                                                <input class="quantity-num" type="number" name="countProduct" value="<%//count%>"/>
                                                <input type="submit" value="Add"/>
                                            </div>
                                        </form>-->
                                    </div>
                                </td>
                            </tr>

                            <%}}%>

                            </tbody>
                        </table>
                    </div>
                    <h4><%="Total: " + total%></h4>
                    <br>
                    <br>
                    <%}%>
                    <!--</div>
                <div class="col-12 col-lg-4">
                    <div class="cart-summary">
                        <h5>Cart Total</h5>
                        <ul class="summary-table">
                            <li><span>total:</span> <span><%//%>total%>դր</span></li>
                            <li><span>delivery:</span> <span>Free</span></li>

                        </ul>
                        <div class="cart-btn mt-100">
                            <a href="/order" class="btn amado-btn w-100">To Order</a>
                        </div>
                    </div>
                </div>
            </div>-->
                </div>
            </div>

        </div>

</body>
</html>