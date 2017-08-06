package Administration;

import java.sql.*;

/**
 * Created by Max on 03.03.2017.
 *
 * This Class responsible for database issues. It establishes a connection to the database and
 * creates SQL-Statements out of passed Information.
 * All kinds of inserts, updates and deletes are handled here as well as the connect and drop function
 * of the database connection.
 */
public class ConnectDataBase {

    private static ConnectDataBase instance;

    private static Connection con;
    private Statement stmt;


    /**
     * Creates a new Object ConnectDataBase which establishes a connection to the database and handles
     * all database actions for the future
     *
     */
    public ConnectDataBase() {
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
     * Returns a new instance form ConnectDataBase to the Challenge and the UserData class,
     * returns current instance if there is already one assigned.
     *
     * @return  instance of ConnectDataBase
     */
    public static synchronized ConnectDataBase getInstance() {
        if(instance == null){
            instance = new ConnectDataBase();
        }
        return instance;
    }


    /**
     * Inserts a challenge into the database
     *
     * @param title             Title of the challenge
     * @param description       Description of the challenge
     * @param completionTime    Time for completion of the challenge
     * @param idCreator         ID of the creator
     * @param idChallenge       ID of the challenge
     */
    public void insertChallenge(String title, String description, int completionTime, int idCreator, int idChallenge) {
        String query = "INSERT INTO challenges (ChallengeID, challenged, creator, title, description, completionTime, votes) VALUES("
                + idChallenge + ", " + null + ", " + idCreator + ", '" + title + "', '" + description + "', " + completionTime + ", " + 0 + ")";
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Inserts a user into the database
     *
     * @param userID                UserID of the user
     * @param username              username of the user
     * @param password              password of the user
     * @param birthday              Birthday of the user
     * @param birthmonth            Birthmonth of the user
     * @param birthyear             Birthyear of the user
     * @param profilePic            String or address of the profilePic
     * @param challengesCompleted   number of challenges completed
     * @param challengeAssigned     Current challenge assinged (default 0)
     * @param reputation            Current deputation of the user (default 0)
     */
    public void insertUser(int userID, String username, String password, int birthday, int birthmonth, int birthyear, String profilePic, int challengesCompleted, int challengeAssigned, int reputation) {
        String insertString = "INSERT INTO users (userID, username, password, birthday, birthmonth, birthyear, profilepic, challengesCompleted, challengeAssinged, reputation) VALUES("
                + userID + ", '" + username + "', '" + password + "', " + birthday + ", " + birthmonth + ", " + birthyear + ", '" + profilePic + "', "
                + challengesCompleted + ", " + challengeAssigned + ", " + reputation + ")";
    }


    /**
     * Scan the referenced table and search for the highest ID
     *
     * @param table     name of the table
     * @return          Last entry of the referenced table
     */
    public int searchLastIndex(String table) {
        int lastID = 0;
        String query = "";
        if(table.equals("challenges")) {
            query = "SELECT challengeID FROM challenges ORDER BY challengeID DESC LIMIT 1";
        }else if (table.equals("users")) {
            query = "SELECT userID FROM users ORDER BY userID DESC LIMIT 1";
        }else {
            System.out.println("falscher Tabellenname eingegeben");
        }

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
     * Creates a SQL-Query to update the database on the basis of the Parameters for int values
     *
     * @param value         Value to be inserted into the database
     * @param column        Name of column which gets selected
     * @param id            ID from the challenge or user for which we want to search
     * @param whereColumn   Name of the column for the WHERE-Condition
     * @param table         Name of the database to update in
     */
    public void updateDataBaseString(String value, String column, int id, String whereColumn, String table) {

        String query = "UPDATE " + table + " SET " + column + "='" + value + "' WHERE "+ whereColumn + "= " + id;

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a SQL-Query to update the database on the basis of the Parameters for string values
     *
     * @param value         Value to be inserted into the database
     * @param column        Name of column which gets selected
     * @param id            ID from the challenge or user for which we want to search
     * @param whereColumn   Name of the column for the WHERE-Condition
     * @param table         Name of the database to update in
     */
    public void updateDataBaseInt(int value, String column, int id, String whereColumn, String table) {

        String query = "UPDATE " + table + " SET " + column +"=" + value + " WHERE "+ whereColumn +" = " + id;

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a SQL-Query on the basis of the Parameter and returns the String value of a certain column
     *
     * @param whereValue    Value of the WHERE-Condition
     * @param column        Name of column which gets selected
     * @param whereColumn   Name of the column for the WHERE-Condition
     * @param table         Name of the database to search in
     * @return              Value of the searched column
     */
    public String dataBaseQueryString(String whereValue, String column, String whereColumn, String table){

        String query = "SELECT " + column + " FROM " + table + " WHERE " + whereColumn + "='" + whereValue + "'";

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
     * Creates a SQL-Query on the basis of the Parameter and returns the Int value of a certain column
     *
     * @param whereValue    Value of the WHERE-Condition
     * @param column        Name of column which gets selected
     * @param whereColumn   Name of the column for the WHERE-Condition
     * @param table         Name of the database to search in
     * @return              Value of the searched column
     */
    public int dataBaseQueryInt(int whereValue, String column, String whereColumn, String table){

        String query = "SELECT " + column + " FROM " + table + " WHERE " + whereColumn + "=" + whereValue;

        try {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(column);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -101; // A challenge is getting deleted if its reputation is below 101
    }


    /**
     * Deletes the a row in the Database
     *
     * @param column  Column name to specify WHERE-condition
     * @param value   Column value to delete only specified columns
     * @param table   Name of the database to delete in
     */
    public void deleteRow(String column, int value, String table){

        String query = "DELETE from " + table + " WHERE " + column + "=" + value;

        try{
            stmt.executeQuery(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Disconnect the database, reset the connection and the instance
     *
     */
    public void disconnectDB() {
        try {
            if (con != null) {
                con = stmt.getConnection();
                stmt.close();
                con.close();
                this.instance = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
