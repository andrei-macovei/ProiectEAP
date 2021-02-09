<%@ page import="application.Sala" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 12-Aug-20
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="Titlu.css">
<html>
<head>
    <title>Editare Sala</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organizator Evenimente 1.0</h2>
    </div>
</div>

<div id="container">
    <h3>Editare Sala</h3>

    <% Sala sala = (Sala) request.getAttribute("SALA"); %>
    <form action="SalaControllerServlet" method="GET">
        <input type="hidden" name="command" value="UPDATE" />
        <input type="hidden" name="salaID" value="<%= sala.getId()%>" />

        <table>
            <tbody>
            <tr>
                <td><label>Nume Sala:</label></td>
                <td><input type="text" name="name" value="<%= sala.getNume()%>" /></td>
            </tr>
            <tr>
                <td><label>Capacitate:</label></td>
                <td><input type="number" name="cap" value="<%= sala.getCapacitate()%>" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Confirma schimbari"></td>
            </tr>
            </tbody>
        </table>
        <p style="color:red;">${errorMessage}</p>
    </form>
    <form action="SalaControllerServlet" method="GET">
        <input type="hidden" name="command" value="LIST">
        <input type="submit" value="Inapoi">
    </form>
</div>
</body>
</html>
