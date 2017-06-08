<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

<div class="container">

    <form name="addBookForm" method="POST" action="Controller">
        <input type="hidden" name="command" value="addBook"/>
        <input type="hidden" name="newBook" value="yes"/>
        <div class="form-group">

            <fmt:message bundle="${lang}" key="addBook.bookName"/>:
            <input class="form-control" type="text" name="name"
                   placeholder="<fmt:message bundle="${lang}" key="addBook.bookName"/>" required="required"><br/>
        </div>
        <div class="form-group">

            <fmt:message bundle="${lang}" key="main.amount"/>:
            <input class="form-control" type="number" name="amount" required="required"
                   placeholder="<fmt:message bundle="${lang}" key="addBook.bookAmount"/>:"
                   onchange="handleChange(this);"> <br/>
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


        <button type="submit" class="btn btn-default"><fmt:message bundle="${lang}" key="submit"/></button>
    </form>
    ${error}


</div>
</body>
</html>
