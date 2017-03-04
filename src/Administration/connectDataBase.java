package Administration;

import java.sql.*;
/**
 * Created by Max on 03.03.2017.
 */
public class connectDataBase {

    private static final connectDataBase dbcontroller = new connectDataBase();
    private static Connection con;

    public connectDataBase(){

    }

    public static void main( String[] args )
    {
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch ( ClassNotFoundException e )
        {
            System.err.println( "Keine Treiber-Klasse!" );
            System.err.println(e);
            return;
        }


        try
        {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/challenge_me;shutdown=true", "root", "" );
            Statement stmt = con.createStatement();


            ResultSet rs = stmt.executeQuery( "SELECT * FROM users" );

            while ( rs.next() )
                System.out.printf( "%s, %s %s%n", rs.getString(1),
                        rs.getString(2), rs.getString(3) );

            rs.close();

            stmt.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( con != null )
                try { con.close(); } catch ( SQLException e ) { e.printStackTrace(); }
        }
    }


}
