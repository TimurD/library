<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 28.05.2017
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:import url="../WEB-INF/views/head.jsp"/>
<c:import url="/WEB-INF/views/header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

<div class="navbar navbar-inverse navbar-fixed-left">
    <h4 class="navbar-brand"> <fmt:message bundle="${lang}" key="main.Genres"/> </h4>
    <ul class="nav navbar-nav">
        <c:forEach items="${genres}" var="g">
            <li><a href="/Controller?command=searchBooks&id=${g.id}">${g.name}</a></li>
        </c:forEach>
    </ul>
</div>


<div class="container">
    <form name="searchForm" method="POST" action="/Controller">


        <div class="col-lg-11">
            <div class="input-group">

                <input type="hidden" name="command" value="searchBooks"/>

                <input type="text" name="search" class="form-control">

                <div class="input-group-btn">
                    <select name="selected" class="form-control" style="width: 155px;">
                        <option value="bookName">
                            <fmt:message key="main.bookName" bundle="${lang}"/>
                        </option>
                        <option value="authorName">
                            <fmt:message key="main.authorName" bundle="${lang}"/>
                        </option>
                    </select>

                    <button id="sendButton" class="btn btn-default" type="submit">
                        <fmt:message key="main.search" bundle="${lang}"/>
                    </button>
                </div>
            </div>
        </div>


    </form>


    <br/>
    <br/>
    ${message}
    <c:remove var="message" scope="session"/>
    <br/>
    <br/>
    <div class="row">
        <c:forEach var="b" items="${books}">
            <div class="product col-md-12 col-sm-4 col-xs-12">
                <div class="thumbnail">
                    <h4>
                        <c:choose>
                            <c:when test="${user.admin}">
                                <a href="/Controller?command=bookLenders&id=${b.id}"> ${b.name}</a>
                            </c:when>
                            <c:otherwise>
                                ${b.name}
                            </c:otherwise>
                        </c:choose>
                    </h4>
                    <p><fmt:message key="main.authors" bundle="${lang}"/>:
                        <c:forEach items="${b.authors}" var="a">
                            <a href="/Controller?command=allOfAuthor&id=${a.id}"> ${a.name}</a> &nbsp;
                        </c:forEach>
                    </p>
                    <p><fmt:message key="main.genre" bundle="${lang}"/>:${b.genre.name}</p>
                    <p><fmt:message key="main.amount" bundle="${lang}"/>:
                        <c:choose>
                        <c:when test="${user.admin}">
                    <form name="setAmount" method="POST" action="/Controller">
                        <input type="hidden" name="command" value="setBookAmount"/>
                        <input type="hidden" name="bookId" value="${b.id}"/>
                        <input class="form-control" type="number" name="amount" required="required" value="${b.amount}"
                               placeholder="Book Amount" onchange="handleChange(this);">
                    <button type="submit" class="btn btn-default"><fmt:message key="main.setAmount" bundle="${lang}"/></button>
                    </form>
                    </c:when>
                    <c:otherwise>
                        ${b.amount}
                    </c:otherwise>
                    </c:choose>
                    </p>
                    <c:choose>
                        <c:when test="${b.amount>0}">
                            <p><a class="btn btn-default" href="/Controller?command=orderBook&id=${b.id}" role="button">
                                <fmt:message key="main.orderBook" bundle="${lang}"/>
                                »</a></p>
                        </c:when>
                        <c:otherwise>
                            <p>
                                <fmt:message key="main.bookNotAvailable" bundle="${lang}"/>
                            </p>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${user.admin}">
                        <a href="/Controller?command=deleteBook&bookId=${b.id}" role="button" class="btn btn-danger"
                           onclick="return confirm('Are you sure you want to delete?')">
                            <fmt:message key="main.delete" bundle="${lang}"/>
                        </a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
        </table>
    </div>


    <hr/>
</div>

<script>
    function handleChange(input) {
        if (input.value < 0) input.value = 0;
        if (input.value > 100) input.value = 100;
    }

    $("#regions").change(function () {
        if ($("option:selected:last", this).val() == 99) {
            $('#regions option').prop('selected', true);
        } else {
            $('#regions option').prop('selected', false);
        }
    });
</script>

</body>
</html>



