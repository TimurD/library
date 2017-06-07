<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 29.05.2017
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

<div class="container">

    <form class="form-inline" name="addGenreForm" method="POST" action="/Controller">
        <input type="hidden" name="command" value="addGenre"/>

        <div class="form-group">
            <label for="name"> <fmt:message bundle="${lang}" key="addAG.genreName"/>:</label>
            <input type="text" class="form-control" placeholder="<fmt:message bundle="${lang}" key="addAG.genreName"/>"
                   name="name" required="required" pattern="[A-Za-z]{1,20}">
        </div>
        <button type="submit" class="btn btn-default"><fmt:message bundle="${lang}" key="submit"/></button>
        <p style="color:blue">${resultForGenre}</p>
    </form>


    <form class="form-inline" name="addAuthorForm" method="POST" action="/Controller">
        <input type="hidden" name="command" value="addAuthor"/>

        <div class="form-group">
            <label for="name"><fmt:message bundle="${lang}" key="addAG.authorName"/></label>
            <input type="text" class="form-control" id="name"
                   placeholder="<fmt:message bundle="${lang}" key="addAG.authorName"/>" required="required" name="name">
        </div>
        <button type="submit" class="btn btn-default"><fmt:message bundle="${lang}" key="submit"/></button>
        <p style="color:blue">${resultForAuthor}</p>
    </form>

</div>

<hr/>
<form name="searchForm" method="POST" action="/Controller">
    <input type="hidden" name="command" value="searchAuthor"/>

    <div class="col-lg-11">
        <div class="input-group">

            <input type="hidden" name="command" value="searchBooks"/>

            <input type="text" name="search" class="form-control" value="${searchText}" pattern="[A-Za-z]{1,20}">

            <div class="input-group-btn">

                <button id="sendButton" class="btn btn-default" type="submit"><fmt:message bundle="${lang}" key="addAG.searchAuthor"/></button>
            </div>
        </div>
    </div>
</form>
<br/><br/><br/><br/>
<hr/>

<c:forEach var="a" items="${authors}">
    ${a.name}<br/>
</c:forEach>

</div>

</body>
</html>
