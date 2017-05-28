package src.Administration;

import java.sql.*;

/**
 * Created by Max on 03.03.2017.
 */
public class connectDataBase {

    private static Connection con;
    private Statement stmt;

    /**
     * Creates a new Object connectDataBase which establishes a connection to the database and handles
     * all database actions for the future
     */
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

    /**
     * Insert a data set in the database or update an existing one
     *
     * @param query
     */
    public void insertQuery(String query) {
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scan the table referenced the handed SQL Query for the last entry
     *
     * @param query     SQL Query which is executed on the database table
     * @return          Last entry of the referenced table
     */
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

    /**
     * Execute the handed SQL Query and return the String value of a certain column
     *
     * @param query     SQL Query which is executed on the database table
     * @param column    Name of column which gets selected
     * @return          Value of selected column
     */
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

    /**
     * Execute the handed SQL Query and return the int value of a certain column
     *
     * @param query     SQL Query which is executed on the database table
     * @param column    Name of column which gets selected
     * @return          Value of selected column
     */
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


    /**
     * Disconnect the database and reset the connection
     *
     */
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
