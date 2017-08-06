package Profile;

import Administration.ConnectDataBase;
import Administration.Randomizer;

/**
 * Created by Felix on 02.01.2017.
 *
 * This class is responsible for the user data model. It contains all information to one user.
 * Assinging a random challenge, getting the next free ID automatically from the database as well
 * as returning userdata and update the userdata local and in the database are part of this class.
 *
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
    private int reputation;             //Reputation of the user (default 0)
    private int userID;                 //UserID in database
    private ConnectDataBase database;   //connection to the database


    /**
     * Creates a new user object and writes it into the database if it is not there yet
     *
     * @param username  The username
     * @param pass  The users password hash, encrypted with SHA-256
     * @param day   The users birthday
     * @param month The users birthmonth
     * @param year  The users birthyear
     */
    public UserData(String username, String pass, int day, int month, int year) {
        database = ConnectDataBase.getInstance();
        this.username = username;
        password = pass;
        birthday = day;
        birthmonth = month;
        birthyear = year;
        profilePic = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";
        challengesCompleted = 0;
        challengeAssigned = 0;
        reputation = 100;
        if (!database.dataBaseQueryString(username, "username", "username", "users").equals(username)) {
            userID = generateUserID();
            database.insertUser(userID, username, password, birthday, birthmonth, birthyear, profilePic, challengesCompleted, challengeAssigned, reputation);
        } else {
            userID = Integer.parseInt(database.dataBaseQueryString(username, "userID", "username", "users"));
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
        int maxChallengeIndex = database.searchLastIndex("users");
        if (challengeAssigned == 0) {
            //generate random challengeID
            newID = Randomizer.getRandomInt(maxChallengeIndex)+1;
            database.updateDataBaseInt(newID, "challengeAssinged", userID, "userID", "users");
            database.updateDataBaseInt(userID, "challenged", newID, "challengeID", "challenges");
            challengeAssigned = newID;
            return newID;
        } else {
            //user already has a challenge assigned, return a nope
            return -1;
        }
    }

    /**
     * Remove the assinged challenge from the user
     *
     * @return If giving up was successful
     */
    public int giveUp() {
        if (this.challengeAssigned != 0) {
            this.challengeAssigned = 0;
            return 1;
        } else {
            this.reputation -= 2;
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
        nextFreeIDFromDatabase = database.searchLastIndex("users");
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
    public int getDay() {
        return birthday;
    }

    /**
     * Returns the users birthmonth
     *
     * @return The users birthmonth
     */
    public int getMonth() {
        return birthmonth;
    }

    /**
     * Returns the users birthyear
     *
     * @return The users birthyear
     */
    public int getYear() {
        return birthday;
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
     * Returns the users reputation
     *
     * @return The users reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * Sets the users Name and update database
     *
     * @param name The username
     */
    public void setName(String name) {
        username = name;
        database.updateDataBaseString(name, "username", userID, "userID", "users");
    }

    /**
     * Sets the users Password and update database
     *
     * @param pass The password
     */
    public void setPass(String pass) {
        password = pass;
        database.updateDataBaseString(pass, "password", userID, "userID", "users");
    }

    /**
     * Sets the users birthday and update database
     *
     * @param day The users birthday
     */
    public void setDay(int day) {
        birthday = day;
        database.updateDataBaseInt(day, "birthday", userID, "userID", "users");
    }

    /**
     * Sets the users birth month and update database
     *
     * @param month The users birth month
     */
    public void setMonth(int month) {
        birthmonth = month;

        database.updateDataBaseInt(month, "birthmonth", userID, "userID", "users");
    }

    /**
     * Sets the users birth year and update database
     *
     * @param year The users birth year
     */
    public void setYear(int year) {
        birthyear = year;

        database.updateDataBaseInt(year, "birthyear", userID, "userID", "users");
    }

    /**
     * Adds or reduces the reputation of the user and update database
     *
     * @param vote +1 or -1 for voting the users reputation
     */
    public void voteForUser(int vote){
        reputation += vote;
        int newVotes = database.dataBaseQueryInt(userID, "reputation", "userID", "users");
        newVotes += vote;
        database.updateDataBaseInt(newVotes, "reputation", userID, "userID", "users");
    }

    /**
     * Disconnect from database
     */
    public void disconnectDB(){
        database.disconnectDB();
    }
}