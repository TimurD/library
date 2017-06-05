<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<c:import url="head.jsp"/>
<c:import url="header.jsp"/>
<div class="container">

    <form name="registrationForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="addBook"/>
        <input type="hidden" name="newBook" value="yes"/>
        <div class="form-group">

            Name:
            <input class="form-control" type="text" name="name" placeholder="Book Name" required="required"><br/>
        </div>
        <div class="form-group">

            Amount:
            <input class="form-control" type="number" name="amount" required="required" placeholder="Book Amount" onchange="handleChange(this);"> <br/>
        </div>
        <div class="form-group">
        <select class="selectpicker" name="selectedGenre">
            <c:forEach var="g" items="${genres}">
                <option value="${g.id}">${g.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="form-group">
        <select name="selectedAuthors" class="selectpicker" required="required" multiple>
            <c:forEach var="a" items="${authors}">
                <option value="${a.id}">${a.name}</option>
            </c:forEach>
        </select>
        </div>


        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    ${error}




</div>
</body>
</html>
