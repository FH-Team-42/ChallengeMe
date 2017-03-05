package Administration;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Max on 03.03.2017.
 */
public class connectDataBase {

    private static Connection con;
    private Statement stmt;

    public connectDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Keine Treiber-Klasse!");
            System.err.println(e);
        }


        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_me", "root", "");
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertQery(String query) {
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        finally
        {
            if ( con != null )
                try { con.close(); } catch ( SQLException e ) { e.printStackTrace(); }
        }*/
    }

    public int searchLastIndex(String query) {
        int lastID = 0;
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                lastID = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastID;
    }


    public String dataBaseQueryString(String query, String column) {
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getString(column);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int dataBaseQueryInt(String query, String column) {
        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(column);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void disconnectDB() {
        try {
            if (con != null) {
                con = stmt.getConnection();
                stmt.close();
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
