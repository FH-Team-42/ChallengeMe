package src.Profile;

import Administration.connectDataBase;
import Administration.Randomizer;

/**
 * Created by Felix on 02.01.2017.
 */

public class UserData {
    private String username;
    private String password;
    private int birthday;
    private int birthmonth;
    private int birthyear;

    private String profilePic;          //link to profile pic
    private int challengesCompleted;    //count of the challenges the user hat completed
    private int challengeAssigned;      //ID of the assigned challenge
    private int reputation;
    private int userID;                 //UserID in database
    private connectDataBase database;   //connection to the database


    /**
     * Creates a new user object and writes it into the database if it is not there yet
     *
     * @param name  The username
     * @param pass  The users password hash, encrypted with SHA-256
     * @param day   The users birthday
     * @param month The users birthmonth
     * @param year  The users birthyear
     */
    public UserData(String name, String pass, int day, int month, int year) {
        database = new connectDataBase();
        username = name;
        password = pass;
        birthday = day;
        birthmonth = month;
        birthyear = year;
        profilePic = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";
        challengesCompleted = 0;
        challengeAssigned = 0;
        reputation = 100;
        if (!database.dataBaseQueryString("SELECT username FROM users WHERE username='" + username + "'", "username").equals(username)) {
            userID = generateUserID();
            String insertString = "INSERT INTO users (userID, username, password, birthday, birthmonth, birthyear, profilepic, challengesCompleted, challengeAssinged, reputation) VALUES("
                    + userID + ", '" + username + "', '" + password + "', " + birthday + ", " + birthmonth + ", " + birthyear + ", '" + profilePic + "', "
                    + challengesCompleted + ", " + challengeAssigned + ", " + reputation + ")";
            database.insertQuery(insertString);
        } else {
            userID = database.dataBaseQueryInt("SELECT userID FROM users WHERE username='" + username + "'", "userID");
        }

    }

    /**
     * Assigns a new random challenge to a user
     *
     *
     * @return Challenge ID assigned to user
     */
    public int setNewChallenge() {
        int newID;
        int maxChallengeIndex = database.searchLastIndex("SELECT challengeID FROM challenges ORDER BY challengeID DESC");
        if (challengeAssigned == 0) {
            //generate new challenge
            newID = Randomizer.getRandomInt(maxChallengeIndex)+1;
            database.insertQuery("UPDATE users SET challengeAssinged=" + newID + " WHERE userID=" + userID);
            database.insertQuery("UPDATE challenges SET challenged=" + userID + " WHERE challengeID=" + newID);
            challengeAssigned = newID;
            return newID;
        } else {
            //user already has a challenge assigned, return a nope
            return -1;
        }
    }

    /**
     * Remove a challenge from a user
     *
     * @param user The user giving up
     * @return If giving up was successful
     */
    public int giveUp(UserData user) {
        if (user.challengeAssigned != 0) {
            //challengeAddToDatabase(user.challengeAssigned);     //will be added to control unit
            user.challengeAssigned = 0;
            return 1;
        } else {
            user.reputation -= 2;
            return 0;
        }
    }

    /**
     * Generates a user ID
     *
     * @return The user ID
     */
    private int generateUserID() {
        int nextFreeIDFromDatabase = 0;
        String query = "SELECT * FROM users ORDER BY userID DESC";
        nextFreeIDFromDatabase = database.searchLastIndex(query);
        return nextFreeIDFromDatabase + 1;
    }

    /**
     * Returns the username
     *
     * @return The username
     */
    public String getName() {
        String abfrageColumn = "username";
        String query = "SELECT username From users WHERE userID = " + userID;
        return database.dataBaseQueryString(query, abfrageColumn);
    }

    /**
     * Returns the user password
     *
     * @return The users password
     */
    public String getPass() {
        String abfrageColumn = "password";
        String query = "SELECT password From users WHERE userID = " + userID;
        return database.dataBaseQueryString(query, abfrageColumn);
    }

    /**
     * Returns the users birthday
     *
     * @return The users birthday
     */
    public int getDay() {
        String abfrageColumn = "birthday";
        String query = "SELECT birthday From users WHERE userID = " + userID;
        return database.dataBaseQueryInt(query, abfrageColumn);
    }

    /**
     * Returns the users birthmonth
     *
     * @return The users birthmonth
     */
    public int getMonth() {
        String abfrageColumn = "birthmonth";
        String query = "SELECT birthmonth From users WHERE userID = " + userID;
        return database.dataBaseQueryInt(query, abfrageColumn);
    }

    /**
     * Returns the users birthyear
     *
     * @return The users birthyear
     */
    public int getYear() {
        String abfrageColumn = "birthyear";
        String query = "SELECT birthyear From users WHERE userID = " + userID;
        return database.dataBaseQueryInt(query, abfrageColumn);
    }

    /**
     * Returns the users ID
     *
     * @return The users ID
     */
    public int getUserID() {
        String abfrageColumn = "userID";
        String query = "SELECT userID From users WHERE username = '" + username + "'";
        return database.dataBaseQueryInt(query, abfrageColumn);
    }

    /**
     * Returns the users reputation
     *
     * @return The users reputation
     */
    public int getReputation() {
        String abfrageColumn = "reputation";
        String query = "SELECT reputation From users WHERE userID = " + userID;
        return database.dataBaseQueryInt(query, abfrageColumn);
    }

    /**
     * Sets the users Name
     *
     * @param name The username
     */
    public void setName(String name) {
        username = name;
        String query = "UPDATE users SET username='" + name +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    /**
     * Sets the users Password
     *
     * @param pass The password
     */
    public void setPass(String pass) {
        password = pass;
        String query = "UPDATE users SET password='" + pass +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    /**
     * Sets the users birthday
     *
     * @param day The users birthday
     */
    public void setDay(int day) {
        birthday = day;
        String query = "UPDATE users SET birthday='" + day +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    /**
     * Sets the users birth month
     *
     * @param month The users birth month
     */
    public void setMonth(int month) {
        birthmonth = month;
        String query = "UPDATE users SET birthmonth='" + month +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    /**
     * Sets the users birth year
     *
     * @param year The users birth year
     */
    public void setYear(int year) {
        birthyear = year;
        String query = "UPDATE users SET birthyear='" + year +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    public void voteForUser(int vote){
        reputation += vote;
        int newVotes = database.dataBaseQueryInt("SELECT reputation FROM users WHERE userID=" +userID, "reputation");
        newVotes += vote;
        String query = "UPDATE users SET reputation='" + newVotes +  "' WHERE userID=" + userID;
        database.insertQuery(query);
    }

    /**
     * Disconnect from database
     */
    public void disconnectDB(){
        database.disconnectDB();
    }
}