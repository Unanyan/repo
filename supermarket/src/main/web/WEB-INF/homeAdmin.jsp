<%@ page import="am.vtc.chat.model.Admin" %><%--
  Created by IntelliJ IDEA.
  User: vrezh
  Date: 21.04.20
  Time: 01:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="resources/homeAdmin.css">
</head>
<body>
<% if (request.getSession().getAttribute("successfullyForShipper") != null) {%>
<span style="color: blue">
            <%=request.getSession().getAttribute("successfullyForShipper")%>
            <%request.getSession().removeAttribute("successfullyForShipper");%>
            <br>
        </span>
<%}%>
<% if (request.getSession().getAttribute("successfullyAddProduct") != null) {%>
<span style="color: blue">
            <%=request.getSession().getAttribute("successfullyAddProduct")%>
            <%request.getSession().removeAttribute("successfullyAddProduct");%>
            <br>
        </span>
<%}%>
<br>
<%Admin admin = (Admin)request.getSession().getAttribute("admin");%>
<%="admin: " + admin.getName()%>
<!--<br>
<a href="/addProduct">Add Product</a>
<br>
<a href="/logoutAdmin">Logout</a>
<br>
<a href="/registerShipper">Register shipper</a>
-->
<div class="login-page">
    <div class="form">

            <a href="/addProduct"><button>Add Product</button></a>
            <a href="/registerShipper"><button>Register shipper</button></a>
            <a href="/logoutAdmin"><button>Logout</button></a>
            <!--<p class="message">Not registered? <a href="#">Cre</a></p>-->

    </div>
</div>
</body>
</html>
