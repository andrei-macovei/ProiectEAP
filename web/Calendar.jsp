<%@ page import="application.Eveniment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 03-Aug-20
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
<link type="text/css" rel="stylesheet" href="Titlu.css">
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 50%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 2px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
    <title>Lista Evenimente</title>
</head>
<%
    List<Eveniment> events = (List<Eveniment>) request.getAttribute("events_list");
    int i = 0;
%>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organizator Evenimente 1.0</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Nr crt.</th>
                <th>Denumire</th>
                <th>Data/ora inceput</th>
                <th>Data/ora final</th>
                <th>Sala</th>
            </tr>
            <%
                for(Eveniment tempEvent : events){
            %>
            <tr>
                <td align="center"> <%= ++i %> </td>
                <td align="center"> <%= tempEvent.getDenumire()%> </td>
                <td align="center"> <%= tempEvent.getTimp_inceput()%> </td>
                <td align="center"> <%= tempEvent.getTimp_final()%> </td>
                <td> <%= tempEvent.getNumeSala()%></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <br>
    <form action="SalaControllerServlet" method="get">
        <input type="hidden" name="command" value="LIST">
        <input type="submit" value="Inapoi">
    </form>
</div>

</body>
</html>
