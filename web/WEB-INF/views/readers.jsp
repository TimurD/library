<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 28.05.2017
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>
<c:import url="header.jsp"/>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>

<div class="container">


    <form name="searchForm" method="POST" action="/Controller">


        <div class="col-lg-11">
            <div class="input-group">

                <input type="hidden" name="command" value="searchReaders"/>

                <input type="text" name="search" class="form-control" value="${searchText}">

                <div class="input-group-btn">
                    <select name="selected" class="form-control" style="width: 145px;">
                        <option><fmt:message bundle="${lang}" key="readers.readerName"/></option>
                        <option><fmt:message bundle="${lang}" key="orders.readerEmail"/></option>
                    </select>

                    <button id="sendButton" class="btn btn-default" type="submit"  style="width:95px;"><fmt:message bundle="${lang}" key="main.search"/></button>
                </div>
            </div>
        </div>


    </form>

    <div class="col-md-12">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th><fmt:message bundle="${lang}" key="login.email"/></th>
                <th><fmt:message bundle="${lang}" key="registration.name"/></th>
                <th><fmt:message bundle="${lang}" key="debt"/></th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="r" items="${readers}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="/Controller?command=readerInfo&id=${r.id}"> ${r.email} </a></td>
                <td>
                        ${r.name}
                </td>
                <td>
                        ${r.debt}
                </td>
            </tr>
            </c:forEach>
    </div>
</div>
</body>
</html>
