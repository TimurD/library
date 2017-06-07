<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 05.06.2017
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>
<div class="container">
    <div class="row">
        <div class="error-template">
            <h1>Oops!</h1>
            <div class="error-details">
                Sorry, an error has occurred, Requested page not found!<br>
            </div>
            <div class="error-actions">
                <a href="/Controller?command=mainMenu">Main page</a>
            </div>
        </div>
    </div>
</body>
</html>
