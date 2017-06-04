<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">


    <form name="searchForm" method="POST" action="/Controller">


        <div class="col-lg-11">
            <div class="input-group">

                <input type="hidden" name="command" value="searchReaders"/>

                <input type="text" name="search" class="form-control">

                <div class="input-group-btn">
                    <select name="selected" class="form-control" style="width: 145px;">
                        <option>Reader name</option>
                        <option>Reader email</option>
                    </select>

                    <button id="sendButton" class="btn btn-default" type="submit"  style="width:95px;">Search</button>
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
