<%--
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
    <title>Adaugare Sala</title>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Organizator Evenimente 1.0</h2>
        </div>
    </div>

    <div id="container">
        <h3>Adaugare Sala</h3>

        <form action="SalaControllerServlet" method="GET">
             <input type="hidden" name="command" value="ADD" />

            <table>
                <tbody>
                    <tr>
                        <td><label>Nume Sala:</label></td>
                        <td><input type="text" name="name" /></td>
                    </tr>
                    <tr>
                        <td><label>Capacitate:</label></td>
                        <td><input type="number" name="cap" /></td>
                    </tr>
                <tr>
                    <td><input type="submit" value="Adaugare"></td>
                </tr>
                </tbody>
            </table>
            <p style="color:red;">${errorMessage}</p>
            <p style="color:green;">${successMessage}</p>
        </form>
        <form action="SalaControllerServlet" method="GET">
            <input type="hidden" name="command" value="LIST">
            <input type="submit" value="Inapoi">
        </form>
    </div>
</body>
</html>
