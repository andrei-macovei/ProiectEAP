<%@ page import="application.Sala" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 26-Aug-20
  Time: 12:03
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
        <%
            List<Sala> sali = (List<Sala>) request.getAttribute("sali_list");
        %>
        <p>Introduceti detaliile evenimentului:</p>
        <form action="EventControllerServlet" method="post">
            <input type="hidden" name="command" value="ADD">
            <input type="hidden" name="inc_d" value="<%= request.getParameter("inc_d")%>">
            <input type="hidden" name="inc_t" value="<%= request.getParameter("inc_t")%>">
            <input type="hidden" name="fin_d" value="<%= request.getParameter("fin_d")%>">
            <input type="hidden" name="fin_t" value="<%= request.getParameter("fin_t")%>">

            <table>
                <tbody>
                <tr>
                    <td><label>Denumire Eveniment:</label></td>
                    <td><input type="text" name="denumire" /></td>
                </tr>
                <tr>
                    <td><label>Alege una din salilie disponibile:</label></td>
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
                    <td> </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Adauga Eveniment"></td>
                </tr>
                </tbody>
            </table>
        </form>
        <p style="color:red;">${errorMessage}</p>
        <a href="perioadaEveniment.jsp">Inapoi</a>
    </div>
</body>
</html>
