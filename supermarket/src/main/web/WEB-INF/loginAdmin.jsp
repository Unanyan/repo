<%--
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
    <link rel="stylesheet" type="text/css" href="resources/login.css">
</head>
<body>

            <div class="login-page">
                <div class="form">
                    <form action="/loginAdmin" method="post" class="login-form">
                        <% if (request.getAttribute("errorEmailPasswordAdmin") != null) {%>
                        <span style="color: red">
                             <%=request.getAttribute("errorEmailPasswordAdmin")%>
                         </span>
                        <%}%>
                        <input name="emailAdmin" type="text" placeholder="email"/>
                        <input name="passwordAdmin" type="password" placeholder="password"/>
                        <button>login</button>
                        <!--<p class="message">Not registered? <a href="#">Cre</a></p>-->
                    </form>
                </div>
            </div>
</body>
</html>
