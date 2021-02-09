<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 14-Aug-20
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testing Datetime format</title>
</head>
<body>
    <form action="test.jsp">
        <input type="datetime-local" name="datetime">
        <input type="date" name="date">
        <input type="time" name="time">
        <input type="submit">
    </form>

    <%= request.getParameter("date")%>
    <br>
    <%= request.getParameter("time")%>
</body>
</html>
