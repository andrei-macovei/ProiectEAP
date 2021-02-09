package database;

import java.sql.*;

public class DBUtil {
    protected Connection open(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestiunesali","proiect","imiplaceeap!");
        }catch(SQLException se){
            se.printStackTrace();
        }
        return conn;
    }

    protected void close(Connection conn, Statement statement, ResultSet rs){
        try{
            if(conn != null) conn.close();
            if(statement != null) statement.close();
            if(rs != null) rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
