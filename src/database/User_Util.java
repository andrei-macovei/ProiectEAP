package database;

import java.sql.*;

public class User_Util {

    public static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/gestiunesali";

    public static final String USER = "Database";
    static final String PASS = "institutie";

    public boolean isValidLogin(String UserName, String Password){
        boolean isValid = false;

        Connection conn = null;
        Statement statement = null;
        String sql = " ";

        try {
            Class.forName(JDBC_DRIVER);
            //connect to DB
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = conn.createStatement();

            sql = "SELECT * FROM institutie WHERE username = \"" + UserName + "\"AND password =\"" + Password + "\"";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);

            if(rs.next()){
                isValid = true;
            }

            rs.close();
            statement.close();
            conn.close();
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Probleme peste probleme");
        }
        finally{
            try{
                if(statement != null) statement.close();
            }catch (SQLException sse){
                //whatever
            }try {
                if (conn != null) conn.close();
            }catch(SQLException ssse){
                ssse.printStackTrace();
            }
        }
        System.out.println("Database connection closed.");

        return isValid;
    }
}
