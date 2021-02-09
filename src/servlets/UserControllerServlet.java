package servlets;

import application.Institutie;
import database.LoginDBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
    private final LoginDBUtil loginDBUtil = new LoginDBUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //citeste parametrul command din form
        String command = request.getParameter("command");

        if(command == null){
            System.out.println("No command specified.");
        }
        switch (command){
            case "LOGIN":
                verifyLogin(request,response);
                break;
            case "ADD":
                addUser(request,response);
                break;
            default:
                System.out.println("Wrong command!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //read form data
        String name = request.getParameter("name");
        String username = request.getParameter("user");
        String pass = request.getParameter("pass");

        //create user object if all fields have been filled in
        Institutie user = new Institutie(name, username, pass);
        if (user.getUsername().equals("") || user.getNume().equals("") || user.getPassword().equals("")) {
            request.setAttribute("errorMessage", "Completati toate campurile!");
            try {
                request.getRequestDispatcher("/newUserForm.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //add user to database
            try {
                if (loginDBUtil.isUniqueUser(user)) {
                    loginDBUtil.addInstitutie(user);

                    //print success message
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();

                    out.println("Cont adaugat cu succes! Va puteti autentifica pe pagina de login.");
                    out.println("<br><a href=\"index.jsp\">Inapoi la pagina de login</a>");
                } else {
//                    response.setContentType("text/html");
//                    PrintWriter out = response.getWriter();
//
//                    out.println("Username-ul este deja folosit. Va rugam alegeti altul!");
//                    out.println("<br><a href=\"newUserForm.jsp\">Reincercati</a>");

                    request.setAttribute("errorMessage", "Username deja utilizat. Alegeti altul si reincercati!");
                    request.getRequestDispatcher("/newUserForm.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        //send back to login page
//        try {
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
//            dispatcher.forward(request, response);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        private void verifyLogin(HttpServletRequest request, HttpServletResponse response) {
            String username = request.getParameter("user");
            String pass = request.getParameter("pass");

            Institutie user = new Institutie(" ", username, pass);
            if (user.getUsername().equals("") || user.getPassword().equals("")) {
                request.setAttribute("errorMessage", "Completati username-ul si parola!");
                try {
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                if(loginDBUtil.isValidlogin(user)){
                    try {
                        request.setAttribute("user",user.getUsername());
                        request.getRequestDispatcher("/home.jsp").forward(request, response);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userN",username);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        request.setAttribute("errorMessage", "Username sau parola incorecte!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
}
