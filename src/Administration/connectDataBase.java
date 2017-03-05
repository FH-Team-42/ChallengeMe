package Administration;

import java.sql.*;
/**
 * Created by Max on 03.03.2017.
 */
public class connectDataBase {

    private static Connection con;
    private Statement stmt;

    public connectDataBase(){
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch ( ClassNotFoundException e )
        {
            System.err.println( "Keine Treiber-Klasse!" );
            System.err.println(e);
        }


        try
        {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_me", "root", "" );
            stmt = con.createStatement();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    public void insertQery(String query)
    {
        try {
            stmt.executeUpdate(query);
            stmt.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        /*
        finally
        {
            if ( con != null )
                try { con.close(); } catch ( SQLException e ) { e.printStackTrace(); }
        }*/
    }

    public void disconnectDB(Statement stmt){
        try{
            con = stmt.getConnection();
            stmt.close();
            if(con != null){
                con.close();
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
