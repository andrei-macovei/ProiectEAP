package servlets;

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
import java.util.List;

@WebServlet("/SalaControllerServlet")
public class SalaControllerServlet extends HttpServlet {

    private AppDBUtil appDBUtil = new AppDBUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //citeste parametrul command din form
        String command = request.getParameter("command");

        if(command == null) System.out.println("No command received");

        switch (command){
            case "LIST":
                try {
                    listSali(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "ADD":
                try {
                    addSala(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case "LOAD":
                try {
                    loadSala(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UPDATE":
                try {
                    updateSala(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DELETE":
                try {
                    deleteSala(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                default:
                System.out.println("Unknown command");
        }
    }

    private void deleteSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //read form data
        String salaID = request.getParameter("salaID");

        appDBUtil.deleteSala(salaID);

        //return to list
        listSali(request,response);
    }

    private void updateSala(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //read form data
        int id = Integer.parseInt(request.getParameter("salaID"));
        String nume = request.getParameter("name");
        int cap = Integer.parseInt(request.getParameter("cap"));

        //get login username
        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        Sala sala = new Sala(id,nume,cap,user);

        appDBUtil.updateSala(sala);

        //return to list
        listSali(request,response);

    }

    private void loadSala(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //read salaID
        String salaID = request.getParameter("salaID");

        //retrieve Sala from db by ID
        Sala sala = appDBUtil.getSala(salaID);

        request.setAttribute("SALA", sala);

        //send to jsp
        request.getRequestDispatcher("/updateSala.jsp").forward(request,response);
    }

    private void listSali(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = (String) request.getSession(true).getAttribute("userN");
        //request.setAttribute("user",user);

        List<Sala> sali = appDBUtil.getSali(user);
        request.setAttribute("sali_list", sali);

            RequestDispatcher dispatcher = request.getRequestDispatcher("listaSali.jsp");
            dispatcher.forward(request, response);
    }

    private void addSala(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        //read form data
        //String user = request.getParameter("user");
        String name = request.getParameter("name");
        String capacity = request.getParameter("cap");
        int cap = Integer.parseInt(capacity);

        HttpSession session = request.getSession(true);
        String user = (String) session.getAttribute("userN");

        Sala sala = new Sala(name,cap,user);

        if(name.equals("") || capacity.equals("")){
            request.setAttribute("errorMessage", "Completati toate campurile!");
            try {
                request.getRequestDispatcher("addSala.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try{
                if(appDBUtil.isUniqueSala(sala)){
                    appDBUtil.addSala(sala);
                    listSali(request,response);
                } else {
                    request.setAttribute("errorMessage", "Sala este deja salvata!");
                    request.getRequestDispatcher("addSala.jsp").forward(request, response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
