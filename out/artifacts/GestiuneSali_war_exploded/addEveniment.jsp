<%@ page import="java.util.List" %>
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
    <title>Adaugare Eveniment</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organizator Evenimente 1.0</h2>
    </div>
</div>

<div id="container">
    <h3>Adaugare Eveniment</h3>
    <%
        List<Sala> sali = (List<Sala>) request.getAttribute("sali_list");
    %>

    <form action="EventControllerServlet" method="POST">
        <input type="hidden" name="command" value="ADD" />
        <input type="hidden" name="sali_list" value="<%= request.getAttribute("sali_list")%>">

        <table>
            <tbody>
            <tr>
                <td><label>Denumire Eveniment:</label></td>
                <td><input type="text" name="denumire" /></td>
            </tr>
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
<%--            <tr>--%>
<%--                <td><label>Id Sala:</label></td>--%>
<%--                <td><input type="number" name="idSala" /></td>--%>
<%--            </tr>--%>
            <tr>
                <td><label>Sala:</label></td>
                <td>
                    <select name="sala">
                        <%
                            if(sali != null)
                            for(Sala tempSala : sali){
                        %>
                        <option><%= tempSala.getNume() %></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Adaugare"></td>
                <td></td>
            </tr>
            </tbody>
        </table>
        <p style="color:red;">${errorMessage}</p>
        <p style="color:green;">${successMessage}</p>
    </form>
    <form action="EventControllerServlet" method="POST">
        <input type="hidden" name="command" value="LIST">
        <input type="submit" value="Inapoi">
    </form>
</div>
</body>
</html>
