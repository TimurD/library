<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 31.05.2017
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="head.jsp"/>


    <div class="container">

        <form class="form-inline" name="addGenreForm" method="POST" action="/Controller">
            <input type="hidden" name="command" value ="addGenre"/>

            <div class="form-group">
                <label for="name">Genre name:</label>
                <input type="text" class="form-control" id="name" placeholder="Genre name" name="name" required="required" pattern="[A-Za-z]{1,20}">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
            <h3>${result}</h3>
        </form>
    </div>


</body>
</html>
