<%@ page import="application.Sala" %>
<%@ page import="application.Eveniment" %>
<%@ page import="java.util.List" %><%--
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
    <title>Editare Eveniment</title>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organizator Evenimente 1.0</h2>
    </div>
</div>

<div id="container">
    <h3>Editare Eveniment</h3>

    <%  List<Sala> sali = (List<Sala>) request.getAttribute("sali_list");
        Eveniment event = (Eveniment) request.getAttribute("EVENT");
        String[] inc = event.getTimp_inceput().split(" ");
        String[] fin = event.getTimp_final().split(" ");
    %>
    <form action="EventControllerServlet" method="POST">
        <input type="hidden" name="command" value="UPDATE" />
        <input type="hidden" name="eventID" value="<%= event.getId()%>" />

        <table>
            <tbody>
            <tr>
                <td><label>Denumire:</label></td>
                <td><input type="text" name="denumire" value="<%= event.getDenumire() %>" /></td>
            </tr>
            <tr>
                <td><label>Data Inceperii:</label></td>
                <td><input type="date" name="inc_d" value="<%= inc[0] %>"/></td>
            </tr>
            <tr>
                <td><label>Ora Inceperii:</label></td>
                <td><input type="time" name="inc_t" value="<%= inc[1] %>"/></td>
            </tr>
            <tr>
                <td><label>Data Incheiere:</label></td>
                <td><input type="date" name="fin_d" value="<%= fin[0] %>"/></td>
            </tr>
            <tr>
                <td><label>Ora Incheiere:</label></td>
                <td><input type="time" name="fin_t" value="<%= fin[1] %>" /></td>
            </tr>
            <tr>
                <td><label>Sala:</label></td>
                <td>
                    <select name="sala">
                        <%
                            for(Sala tempSala : sali){
                        %>
                        <option><%= tempSala.getNume() %></option>
                        <%
                            }
                        %>
                    </select>
<%--                    Sala anterior salvata: <%= event.getNumeSala() %>--%>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Confirma schimbari"></td>
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
