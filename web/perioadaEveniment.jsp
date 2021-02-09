<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 26-Aug-20
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="Titlu.css">
<html>
<head>
    <title>Verificare disponibilitate sali</title>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Organizator Evenimente 1.0</h2>
        </div>
    </div>

    <div id="container">
        <h3>Verificare disponibilitate</h3>
        <p>Alege perioada dorita pentru eveniment:</p>
        <form action="EventControllerServlet" method="post">
            <input type="hidden" name="command" value="CHECK">
            <table>
                <tbody>
                <tr>
                    <td><label>Data Inceperii:</label></td>
                    <td><input type="date" name="inc_d" /></td>
                </tr>
                <tr>
                    <td><label>Ora Inceperii:</label></td>
                    <td><input type="time" name="inc_t" /></td>
                </tr>
                <tr>
                    <td><label>Data Incheiere:</label></td>
                    <td><input type="date" name="fin_d" /></td>
                </tr>
                <tr>
                    <td><label>Ora Incheiere:</label></td>
                    <td><input type="time" name="fin_t" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Verifica"></td>
                </tr>
                </tbody>
            </table>
            <p style="color:red;">${errorMessage}</p>
        </form>
        <form action="EventControllerServlet" method="POST">
            <input type="hidden" name="command" value="LIST">
            <input type="submit" value="Inapoi">
        </form>
    </div>
  </body>
</html>
