<%@ page import="application.Eveniment" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 03-Aug-20
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 80%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 1px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
        h2{
            font-family: 'Amita';font-size: 25px;
            color: lightskyblue;
        }
        h5{
            font-family: 'Coming Soon';font-size: 10px;
            color: lightskyblue;
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

        <!-- buton adaugare eveniment-->
        <form action="perioadaEveniment.jsp">
            <input type="submit" value="Adauga Eveniment"/>
        </form>

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
                <td>
                    <form action="EventControllerServlet" method="post">
                        <input type="hidden" name="command" value="LOAD">
                        <input type="hidden" name="eventID" value="<%= tempEvent.getId() %>">
                        <input type="submit" value="Editare">
                    </form>
                </td>
                <td>
                    <form action="EventControllerServlet" method="post">
                        <input type="hidden" name="command" value="DELETE">
                        <input type="hidden" name="eventID" value="<%= tempEvent.getId() %>">
                        <input type="submit" value="Stergere" onclick="if (!(confirm('Esti sigur ca vrei sa stergi acest eveniment?'))) return false">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
    <p> <a href="home.jsp">Inapoi</a></p>
</div>

</body>
</html>
