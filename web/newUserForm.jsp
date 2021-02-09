<%@ page import="servlets.UserControllerServlet" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 31-Jul-20
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="Titlu.css">
<html>
<head>
    <title>Creare cont</title>
</head>
<body>
     <div id="wrapper">
         <div id="header">
             <h2>Organizator Evenimente 1.0</h2>
         </div>
     </div>

    <div id="container">
        <h3>Detalii cont nou</h3>
        <form action="UserControllerServlet" method="post">

            <input type="hidden" name="command" value="ADD"/>

            <table>
                 <tbody>
                    <tr>
                        <td><label>Nume Institutie:</label></td>
                        <td><input type="text" name="name"/></td>
                    </tr>
                    <tr>
                         <td><label>Username:</label></td>
                         <td><input type="text" name="user"/></td>
                    </tr>
                     <tr>
                         <td><label>Password:</label></td>
                         <td><input type="password" name="pass"/></td>
                     </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Creaza cont nou!"/></td>
                    </tr>
                 </tbody>
            </table>
            <p style="color:red;">${errorMessage}</p>
        </form>
    </div>
    <p>
        <a href="index.jsp">Inapoi la pagina de login</a>
    </p>
</body>
</html>
