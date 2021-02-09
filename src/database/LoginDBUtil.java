package database;

import application.Institutie;

import javax.sql.DataSource;
import java.sql.*;

public class LoginDBUtil extends DBUtil{

    public void addInstitutie(Institutie user){

        PreparedStatement statement = null;

        //get connection to database
        Connection conn = open();

        try{
            //create prepared statement to insert data
            String sql = "INSERT INTO institutie (nume,username,password) VALUES (?,?,?)";

            statement = conn.prepareStatement(sql);

            statement.setString(1,user.getNume());
            statement.setString(2, user.getUsername());
            statement.setString(3,user.getPassword());

            statement.execute();

        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            close(conn,statement,null);
        }
    }

    public boolean isUniqueUser(Institutie user){
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isUnique = false;

        //get connection to database
        Connection conn = open();
        try{
            String sql = "select username from institutie where username = ?";
            statement = conn.prepareStatement(sql);

            statement.setString(1,user.getUsername());

            rs = statement.executeQuery();
            if(!rs.next()) isUnique = true;
        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            close(conn,statement,rs);
        }
        return isUnique;
    }

    public boolean isValidlogin(Institutie user) {
        boolean validLogin = false;

        PreparedStatement statement = null;
        ResultSet rs = null;

        //get connection to database
        Connection conn = open();
        try{
        //Verify if the user exists in DB
        String sql = "select * from institutie where username = ? and password= ?";

        statement = conn.prepareStatement(sql);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());

        rs = statement.executeQuery();

        if (rs.next()) validLogin = true;
        }catch(SQLException se){
               se.printStackTrace();
        }finally {
           close(conn,statement,rs);
    }
        return validLogin;
    }
}
