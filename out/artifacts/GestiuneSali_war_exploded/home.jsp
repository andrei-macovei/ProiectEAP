<%@ page import="database.AppDBUtil" %>
<%@ page import="application.Sala" %>
<%@ page import="java.util.List" %>
<%@ page import="application.Eveniment" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 03-Aug-20
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    <link href='https://fonts.googleapis.com/css?family=Coming Soon' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Amita' rel='stylesheet'>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 20%;
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
<body>
<div id="wrapper">
    <div id="header">
        <h2>Organizator Evenimente 1.0</h2>
    </div>
</div>

<div id="container">
    <h3><%
        String user;
        if (request.getParameter("user") != null) user = request.getParameter("user");
        else user = (String) session.getAttribute("userN");

        out.println("Bun venit, " + user + "!");
    %></h3>
    <table>
        <tbody>
            <tr>
                <td>
                    <form action="SalaControllerServlet" method="get">
                        <input type="hidden" name="command" value="LIST">
                        <input type="hidden" name="user" value="${user}">
                        <input type="submit" name="listSali" value="Lista Sali">
                    </form>
                </td>
                <td>
                    <form action="EventControllerServlet" method="post">
                        <input type="hidden" name="command" value="LIST">
                        <input type="submit" name="listEv" value="Lista Evenimente">
                    </form>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<p> Numar total de sali:
    <%
    AppDBUtil appDBUtil = new AppDBUtil();
    out.print(appDBUtil.countSali(user));

        List<Sala> sali = (List<Sala>)appDBUtil.mostUsedSali(user);
        int i = 0;
    %>
<br>
Salile cu evenimente programate:</p>
<table>
    <tr>
        <th>Nr crt.</th>
        <th>Nume Sala</th>
        <th>Numar evenimente</th>
    </tr>
    <tr>
        <%
            for(Sala tempSala : sali){
        %>
        <td> <%= ++i %> </td>
        <td> <%= tempSala.getNume() %> </td>
        <td> <%= tempSala.getCapacitate() %> </td>
    </tr>
    <%
        }
    %>
</table>

<%
    List<Eveniment> events = appDBUtil.getEvents(user);
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    long eventDuration = 0;
    long diff = 0;
    Eveniment longestEvent = null;
    for(Eveniment tempEvent : events){
        try {
            Date inc = dt.parse(tempEvent.getTimp_inceput());
            Date fin = dt.parse(tempEvent.getTimp_final());
            diff = fin.getTime() - inc.getTime();
            if(diff >= eventDuration) {
                longestEvent = tempEvent;
                eventDuration = diff;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
%>
<p> Cel mai lung eveniment este: <%if(longestEvent!=null) out.print(longestEvent.getDenumire()); else out.print("-"); %> care dureaza <%= eventDuration/3600000 %> ore.</p>
</body>
<footer>
    <h5>Macovei Andrei-Raul, grupa 252</h5>
</footer>
</html>
