package servlets;

import application.Eveniment;
import application.Sala;
import database.AppDBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/EventControllerServlet")
public class EventControllerServlet extends HttpServlet {

    AppDBUtil appDBUtil = new AppDBUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //citeste parametrul command din form
        String command = request.getParameter("command");

        if(command == null) System.out.println("No command received");
        else
        switch (command){
            case "LIST":
                try {
                    listEvents(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "LISTPERSALA":
                try {
                    listEventsPerSala(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "ADD":
                try {
                    addEvent(request,response,false);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "ADDS":
                try {
                    addEvent(request,response,true);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "LOAD":
                try {
                    loadEvent(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "LOADSALI":
                try{
                    loadSali(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "CHECK":
                try{
                    checkForSali(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "UPDATE":
                try {
                    updateEvent(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DELETE":
                try {
                    deleteEvent(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Unknown command");
        }
    }

    private void checkForSali(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inc_time = request.getParameter("inc_t");
        String inc_date = request.getParameter("inc_d");
        String fin_time = request.getParameter("fin_t");
        String fin_date = request.getParameter("fin_d");
        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        if(inc_time.equals("") || inc_date.equals("") || fin_time.equals("") || fin_date.equals("")){
            request.setAttribute("errorMessage","Completati toate campurile!");
            request.getRequestDispatcher("perioadaEveniment.jsp").forward(request,response);
        }
        else {
            String inc = inc_date + " " + inc_time;
            String fin = fin_date + " " + fin_time;

            List<Sala> allSali = appDBUtil.getSali(user);
            List<Sala> sali = new ArrayList<>();
            Eveniment event = new Eveniment(inc, fin);

            for (Sala tempSala : allSali) {
                event.setSala(tempSala.getId());
                if (appDBUtil.isFreeSala(event)) sali.add(tempSala);
            }
            if (sali.isEmpty()) {
                request.setAttribute("errorMessage", "Nicio sala disponibila pentru perioada selectata! <a href=\"addSala.jsp\">" +
                        "Adaugati o sala</a>");
                request.getRequestDispatcher("perioadaEveniment.jsp").forward(request, response);

            } else {
                request.setAttribute("inc_t", inc_time);
                request.setAttribute("inc_d", inc_date);
                request.setAttribute("fin_t", fin_time);
                request.setAttribute("fin_d", fin_date);
                request.setAttribute("sali_list", sali);

                request.getRequestDispatcher("addEvent.jsp").forward(request, response);
            }
        }
    }

    private void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //read form data
        String eventID = request.getParameter("eventID");

        appDBUtil.deleteEvent(eventID);

        //return to list
        listEvents(request,response);
    }

    private void updateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read form data
        int id = Integer.parseInt(request.getParameter("eventID"));
        String denumire = request.getParameter("denumire");
        String inc_time = request.getParameter("inc_t");
        String inc_date = request.getParameter("inc_d");
        String fin_time = request.getParameter("fin_t");
        String fin_date = request.getParameter("fin_d");
        String numeSala = request.getParameter("sala");
        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        int sala_id = appDBUtil.getIdSala(numeSala,user);

        String inc = inc_date + " " + inc_time;
        String fin = fin_date + " " + fin_time;

        Eveniment event = new Eveniment(id,denumire,inc,fin,sala_id);

        if(checkEventConstraints(event) != null) {
            request.setAttribute("errorMessage", checkEventConstraints(event));
            loadEvent(request,response);
        }
        else {
            appDBUtil.updateEvent(event);

            //return to list
            listEvents(request, response);
        }
    }

    private void loadEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read eventID
        String eventID = request.getParameter("eventID");
        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        //retrieve Sala from db by ID
        Eveniment event= appDBUtil.getEvent(eventID);
        List<Sala> sali= appDBUtil.getSali(user);

        request.setAttribute("EVENT", event);
        request.setAttribute("sali_list",sali);

        //send to jsp
        request.getRequestDispatcher("/updateEveniment.jsp").forward(request,response);
    }
    private void loadSali(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get login username
        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        List<Sala> sali = appDBUtil.getSali(user);
        request.setAttribute("sali_list", sali);

        request.getRequestDispatcher("addEveniment.jsp").forward(request,response);
    }

    private void addEvent(HttpServletRequest request, HttpServletResponse response, boolean isAttachedToSala) throws ParseException {
        String denumire = request.getParameter("denumire");
        String inc_time = request.getParameter("inc_t");
        String inc_date = request.getParameter("inc_d");
        String fin_time = request.getParameter("fin_t");
        String fin_date = request.getParameter("fin_d");
        String numeSala = request.getParameter("sala");
        String salaID = request.getParameter("salaID");

        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        int sala_id;
        if(!isAttachedToSala)  sala_id = appDBUtil.getIdSala(numeSala,user);
        else sala_id = Integer.parseInt(salaID);

        String inc = inc_date + " " + inc_time;
        String fin = fin_date + " " + fin_time;

        Eveniment event = new Eveniment(denumire, inc, fin, sala_id);

        if(checkEventConstraints(event) != null) {
            request.setAttribute("errorMessage", checkEventConstraints(event));
            try {
                if(isAttachedToSala) {
                    request.setAttribute("salaID",sala_id);
                    request.getRequestDispatcher("addEventInSala.jsp").forward(request, response);
                }
                else request.getRequestDispatcher("perioadaEveniment.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                appDBUtil.addEvent(event);
                listEvents(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String checkEventConstraints(Eveniment event) throws ParseException {
        //check if all fields were completed
        if (event.getDenumire().equals("") || event.getTimp_inceput().equals(" ") || event.getTimp_final().equals(" "))
            return "Completati toate datele evenimentului!";

        //check if event times are in order
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date inceput = dt.parse(event.getTimp_inceput());
        Date finall = dt.parse(event.getTimp_final());
        if(inceput.after(finall))  return "Perioada evenimentului este invalida!"; // checks if inceput is before final

        if(!appDBUtil.isFreeSala(event)) return "Sala este deja ocupata pentru perioada selectata";

        return null;
    }

    private void listEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession(true).getAttribute("userN");

        List<Eveniment> events = appDBUtil.getEvents(user);
        request.setAttribute("events_list", events);

        RequestDispatcher dispatcher = request.getRequestDispatcher("listaEvents.jsp");
        dispatcher.forward(request, response);
    }

    private void listEventsPerSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String salaID = request.getParameter("salaID");
        request.setAttribute("salaID",salaID);
        int sala_id = Integer.parseInt(salaID);

        List<Eveniment> events = appDBUtil.getEvents(sala_id);
        request.setAttribute("events_list",events);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Calendar.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
