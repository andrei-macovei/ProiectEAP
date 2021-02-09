package database;

import application.Eveniment;
import application.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppDBUtil extends DBUtil{

    public List<Sala> getSali(String username){
        List<Sala> sali = new ArrayList<>();

        Connection conn = open();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = conn.createStatement();
            String sql = "select * from sala where ID_inst=(select ID_i from institutie where username='" + username + "') order by nume";

            rs = statement.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID_s");
                String nume = rs.getString("nume");
                int cap = rs.getInt("capacitate");
                //int id_user = rs.getInt("ID_inst");

                Sala tempSala = new Sala(id,nume,cap);

                sali.add(tempSala);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            close(conn,statement,rs);
        }
        return sali;
    }

    public boolean isUniqueSala(Sala sala){
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isUnique = false;

        //get connection to database
        Connection conn = open();
        try{
            String sql = "select nume from sala where nume = ? and ID_inst = (SELECT ID_i FROM institutie WHERE username=? )";
            statement = conn.prepareStatement(sql);

            statement.setString(1,sala.getNume());
            statement.setString(2,sala.getUser());

            rs = statement.executeQuery();
            if(!rs.next()) isUnique = true;
        }catch(SQLException se){
            se.printStackTrace();
        }finally {
            close(conn,statement,rs);
        }
        return isUnique;
    }

    public void addSala(Sala sala){
        PreparedStatement statement = null;
        String user = sala.getUser();

        //get connection to database
        Connection conn = open();

        try{
            //create prepared statement to insert data
            String sql = "INSERT INTO sala (nume,capacitate,ID_inst) VALUES (?,?,(select ID_i from institutie where username='" + user + "'))";

            statement = conn.prepareStatement(sql);

            statement.setString(1,sala.getNume());
            statement.setInt(2,sala.getCapacitate());

            statement.execute();

        }catch(Exception se){
            se.printStackTrace();
        }finally {
            close(conn,statement,null);
        }
    }

    public Sala getSala(String salaID) throws Exception{

        Sala sala = null;
        Connection conn = open();
        PreparedStatement statement = null;
        ResultSet rs = null;
        int sala_id;
        try {
            sala_id = Integer.parseInt(salaID);

            String sql = "SELECT * FROM sala WHERE ID_s=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,sala_id);

            rs = statement.executeQuery();

            if(rs.next()){
                String nume = rs.getString("nume");
                int cap = rs.getInt("capacitate");

                sala = new Sala(sala_id,nume,cap);
            }
            else{
                throw new Exception("Could not find sala id: " + salaID);
            }
            return sala;
        } finally {
            close(conn,statement,rs);
        }
    }

    public void updateSala(Sala sala) throws SQLException {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = open();

            String sql = "UPDATE sala SET nume=?, capacitate=?, ID_inst=(SELECT ID_i FROM institutie WHERE username=? ) WHERE ID_s=?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, sala.getNume());
            statement.setInt(2, sala.getCapacitate());
            statement.setString(3, sala.getUser());
            statement.setInt(4, sala.getId());

            statement.execute();

        }finally {
            close(conn,statement,null);
        }

    }

    public void deleteSala(String salaID) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        try{
            int sala_id = Integer.parseInt(salaID);

            conn = open();
            String sql = "DELETE FROM sala WHERE ID_s=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,sala_id);
            statement.execute();
        }finally{
            close(conn,statement,null);
        }
    }

    public List<Eveniment> getEvents(String username) {
        List<Eveniment> events = new ArrayList<>();

        Connection conn = open();
        Statement statement = null;
        ResultSet rs = null;

        try {
            statement = conn.createStatement();
            String sql = "SELECT * FROM eveniment WHERE ID_sala IN (SELECT ID_s FROM sala WHERE ID_inst=" +
                    "(select ID_i from institutie where username='" + username + "')) order by inceput";

            rs = statement.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("ID_e");
                String denumire = rs.getString("denumire");
                String inceput = rs.getString("inceput");
                String finall = rs.getString("final");
                int idSala = rs.getInt("ID_sala");

                Eveniment tempEvent = new Eveniment(id,denumire,inceput,finall,idSala,getNumeSala(idSala));

                events.add(tempEvent);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            close(conn,statement,rs);
        }
        return events;
    }

    public List<Eveniment> getEvents(int sala_id) {
        List<Eveniment> events = new ArrayList<>();

        Connection conn = open();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM eveniment WHERE ID_sala=? ORDER BY inceput";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,sala_id);

            rs = statement.executeQuery();

            while(rs.next()){
                int id = rs.getInt("ID_e");
                String denumire = rs.getString("denumire");
                String inceput = rs.getString("inceput");
                String finall = rs.getString("final");
                int idSala = rs.getInt("ID_sala");

                Eveniment tempEvent = new Eveniment(id,denumire,inceput,finall,idSala,getNumeSala(idSala));

                events.add(tempEvent);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            close(conn,statement,rs);
        }
        return events;
    }

    public void addEvent(Eveniment event) {
        PreparedStatement statement = null;
        //////////////String user = sala.getUser();

        //get connection to database
        Connection conn = open();

        try{
            //create prepared statement to insert data
            String sql = "INSERT INTO eveniment (denumire,inceput,final,ID_sala) VALUES (?,STR_TO_DATE(?,'%Y-%m-%d %H:%i'),STR_TO_DATE(?,'%Y-%m-%d %H:%i'),?)";

            statement = conn.prepareStatement(sql);

            statement.setString(1,event.getDenumire());
            statement.setString(2,event.getTimp_inceput());
            statement.setString(3,event.getTimp_final());
            statement.setInt(4,event.getSala());

            statement.execute();

        }catch(Exception se){
            se.printStackTrace();
        }finally {
            close(conn,statement,null);
        }
    }

    public Eveniment getEvent(String eventID) throws Exception{
        Eveniment event = null;
        Connection conn = open();
        PreparedStatement statement = null;
        ResultSet rs = null;
        int event_id;
        try {
            event_id = Integer.parseInt(eventID);

            String sql = "SELECT * FROM eveniment WHERE ID_e=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,event_id);

            rs = statement.executeQuery();

            if(rs.next()){
                String denumire = rs.getString("denumire");
                String inceput = rs.getString("inceput");
                String finall = rs.getString("final");
                int id_sala = rs.getInt("ID_sala");
                String numeSala = getNumeSala(id_sala);

                event = new Eveniment(event_id,denumire,inceput,finall,id_sala,numeSala);
            }
            else{
                throw new Exception("Could not find event id: " + eventID);
            }
            return event;
        } finally {
            close(conn,statement,rs);
        }
    }

    public void updateEvent(Eveniment event) throws SQLException {
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = open();

            String sql = "UPDATE eveniment SET denumire=?, inceput=?, final=?, ID_sala=? WHERE ID_e=?";

            statement = conn.prepareStatement(sql);
            statement.setString(1, event.getDenumire());
            statement.setString(2, event.getTimp_inceput());
            statement.setString(3, event.getTimp_final());
            statement.setInt(4, event.getSala());
            statement.setInt(5, event.getId());

            statement.execute();

        }finally {
            close(conn,statement,null);
        }
    }

    public void deleteEvent(String eventID) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;

        try{
            int sala_id = Integer.parseInt(eventID);

            conn = open();
            String sql = "DELETE FROM eveniment WHERE ID_e=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,sala_id);
            statement.execute();
        }finally{
            close(conn,statement,null);
        }
    }
    public String getNumeSala(int sala_id){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try{
            conn = open();
            String sql = "SELECT nume FROM sala WHERE ID_S=?";

            statement = conn.prepareStatement(sql);
            statement.setInt(1,sala_id);

            rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("nume");
            } else throw new Exception("Couldn't get name for id=" + sala_id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(conn,statement,rs);
        }
        return "error";
    }

    public int getIdSala(String nume,String user){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            conn = open();
            String sql = "SELECT ID_s FROM sala WHERE nume=? AND ID_inst=(SELECT ID_i FROM institutie WHERE username=?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1,nume);
            statement.setString(2,user);

            rs = statement.executeQuery();

            if(rs.next()){
                return rs.getInt("ID_s");
            } else throw new Exception("Couldn't get id for name=" + nume);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(conn,statement,rs);
        }
        return -1;
        }

    public boolean isFreeSala(Eveniment event) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isFree = true;

        Connection conn = open();

        try{
            String sql = "SELECT * from eveniment WHERE ((inceput BETWEEN ? AND ? ) OR (final BETWEEN ? AND ?) OR (inceput < ? AND final > ?))" +
                    "AND (ID_sala=? AND ID_e!=?)";
            statement = conn.prepareStatement(sql);

            statement.setString(1, event.getTimp_inceput());
            statement.setString(2, event.getTimp_final());
            statement.setString(3, event.getTimp_inceput());
            statement.setString(4, event.getTimp_final());
            statement.setString(5, event.getTimp_inceput());
            statement.setString(6, event.getTimp_final());
            statement.setInt(7,event.getSala());
            statement.setInt(8,event.getId());

            rs = statement.executeQuery();
            if(rs.next()) isFree = false;
        }catch (SQLException se){
            se.printStackTrace();
        }finally {
            close(conn,statement,rs);
        }
        return isFree;
    }

    public int countSali(String user) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try{
            conn = open();
            String sql = "SELECT COUNT(ID_s) AS count FROM sala WHERE ID_inst=(SELECT ID_i FROM institutie WHERE username=?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1,user);

            rs = statement.executeQuery();
            if(rs.next()) return rs.getInt("count");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public List<Sala> mostUsedSali(String user){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Sala> sali = new ArrayList<>();
        try{
            conn = open();
            String sql = "SELECT count(s.nume) counter,s.nume FROM sala s JOIN eveniment e ON s.ID_s=e.ID_sala where s.ID_inst=" +
                    "(SELECT ID_i FROM institutie WHERE username=?) GROUP BY s.nume ORDER BY counter DESC";

            statement = conn.prepareStatement(sql);
            statement.setString(1,user);

            rs = statement.executeQuery();
            while(rs.next()){
                String nume = rs.getString("nume");
                int nr_evenimente = rs.getInt("counter");

                Sala tempSala = new Sala(nume,nr_evenimente);
                sali.add(tempSala);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(conn,statement,rs);
        }
        return sali;
    }

//    public int eventLength(Eveniment event){
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        int duration = -1;
//        try{
//            conn = open();
//            String sql = "SELECT TIMESTAMPDIFF(minute, ?, ?) diff";
//
//            statement = conn.prepareStatement(sql);
//
//            rs = statement.executeQuery();
//
//            if(rs.next()){
//                duration = rs.getInt("diff");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return duration;
//    }
}
