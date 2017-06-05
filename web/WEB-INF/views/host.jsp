<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 05.06.2017
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>
<div class="container">


    <form name="searchForm" method="POST" action="/Controller">


        <div class="col-lg-11">
            <div class="input-group">

                <input type="hidden" name="command" value="searchReaders"/>

                <input type="text" name="search" class="form-control">

                <div class="input-group-btn">
                    <button id="sendButton" class="btn btn-default" type="submit" style="width:95px;"/>
                </div>
            </div>
        </div>


    </form>

    <div class="col-md-12">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>#</th>
                <th>Email</th>
                <th>Name</th>
                <th>Debt</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach var="r" items="${users}" varStatus="loop">
            <tr>
                <td>${(loop.index+1)}</td>
                <td><a href="/Controller?command=readerInfo&id=${r.id}"> ${r.email} </a></td>
                <td>
                        ${r.name}
                </td>
                <td>
                    <form name="setAmount" method="POST" action="/Controller">
                        <input type="hidden" name="command" value="setRole"/>
                        <input type="hidden" name="userId" value="${r.id}"/>
                        <input type="hidden" name="admin" value="${r.admin}">
                        <c:choose>
                            <c:when test="${r.admin}">
                                <button type="submit" class="btn btn-danger">Unmake admin</button>

                            </c:when>

                            <c:otherwise>
                                <button type="submit" class="btn btn-primary">Make admin</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </tr>
            </c:forEach>
    </div>
</div>

</body>
</html>
