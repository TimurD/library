<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>

<body data-gr-c-s-loaded="true">
<div class="container">
<form class="form-signin" name="registrationForm" method="POST" action="/Controller">
    <input class="form-control" type="hidden" name="command" value ="registration"/>
    Name:<br/>
    <input class="form-control" type="text" name="name" placeholder="Name" required="required" value="${name}"> ${nameError} <br/>
    Email:<br/>
    <input class="form-control" type="text" name="email" placeholder="Email" required="required" value="${email}"> ${emailError} <br/>
    Password:<br/>
    <input class="form-control" type="password" name ="password" placeholder="Password" required="required"> ${passwordError}
    <br/>
    <button class="btn btn-lg btn-primary btn-block"  type="submit" >Registration</button>

    ${error}
</form>
</div>
</body>
</html>
