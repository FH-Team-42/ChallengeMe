package Profile;

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

    private String profilePic;
    private int challengesCompleted;
    private int challengeAssigned;
    private int reputation;
    private int userID;
    private connectDataBase database;


    /**
     * Creates a new user object
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
        System.out.println(maxChallengeIndex);
        if (challengeAssigned == 0) {
            //generate new challenge
            newID = Randomizer.getRandomInt(maxChallengeIndex);
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
        return username;
    }

    /**
     * Returns the user password
     *
     * @return The users password
     */
    public String getPass() {
        return password;
    }

    /**
     * Returns the users birthday
     *
     * @return The users birthday
     */
    public String getDay() {
        return Integer.toString(birthday);
    }

    /**
     * Returns the users birthmonth
     *
     * @return The users birthmonth
     */
    public String getMonth() {
        return Integer.toString(birthmonth);
    }

    /**
     * Returns the users birthyear
     *
     * @return The users birthyear
     */
    public String getYear() {
        return Integer.toString(birthyear);
    }

    /**
     * Returns the users ID
     *
     * @return The users ID
     */
    public int getUserID() {
        return userID;
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
}