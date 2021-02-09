<%@ page import="application.Sala" %>
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
    <title>Lista Sali</title>
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
</head>
<%
    List<Sala> sali = (List<Sala>) request.getAttribute("sali_list");
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

        <!-- buton adaugare sala-->
        <form action="addSala.jsp">
        <input type="submit" value="Adauga sala"/>
            <input type="hidden" name="user" value="${user}">
        </form>
        <table>
            <tr>
                <th>Nr crt.</th>
                <th>Nume</th>
                <th>Capacitate</th>

            </tr>
            <%
                for(Sala tempSala : sali){
            %>
            <tr>
                <td> <%= ++i %> </td>
                <td> <%= tempSala.getNume()%> </td>
                <td> <%= tempSala.getCapacitate()%> </td>
                <td>
                    <form action="SalaControllerServlet" method="get">
                        <input type="hidden" name="command" value="LOAD">
                        <input type="hidden" name="salaID" value="<%= tempSala.getId() %>">
                        <input type="submit" value="Editare">
                    </form>
                </td>
                <td>
                    <form action="SalaControllerServlet" method="get">
                        <input type="hidden" name="command" value="DELETE">
                        <input type="hidden" name="salaID" value="<%= tempSala.getId() %>">
                        <input type="submit" value="Stergere" onclick="if (!(confirm('Esti sigur ca vrei sa stergi aceasta sala?'))) return false">
                    </form>
                </td>
                <td>
                    <form action="addEventInSala.jsp">
                        <input type="hidden" name="salaID" value="<%= tempSala.getId() %>">
                        <input type="submit" value="Adauga eveniment">
                    </form>
                </td>
                <td>
                    <form action="EventControllerServlet" method="post">
                        <input type="hidden" name="command" value="LISTPERSALA">
                        <input type="hidden" name="salaID" value="<%= tempSala.getId() %>">
                        <input type="submit" value="Calendar sala">
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
