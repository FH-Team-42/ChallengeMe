package model.profile;

import administration.jpa.daos.AbstractDatabaseEntity;
import administration.Randomizer;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Felix on 02.01.2017.
 */

@Entity (name = "users")
public class UserData extends AbstractDatabaseEntity{

    @Column
    private String username;

    @Column
    private String password;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column
    private String profilePic;          //link to profile pic

    @Column
    private int challengesCompleted;    //count of the challenges the user hat completed

    @Column
    private int challengeAssigned;      //ID of the assigned challenge

    @Column
    private int reputation;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int userID;                 //UserID in database
    //private connectDataBase database;   //connection to the database

    public UserData(){

    }


    /**
     * Creates a new user object and writes it into the database if it is not there yet
     *
     * @param name  The username
     * @param pass  The users password hash, encrypted with SHA-256
     * @param day   The users birthday
     * @param month The users birthmonth
     * @param year  The users birthyear
     */
    public UserData(String name, String pass, Date day, int month, int year) {
        //database = new connectDataBase();
        username = name;
        password = pass;
        birthday = day;
        profilePic = "http://s3.amazonaws.com/37assets/svn/765-default-avatar.png";
        challengesCompleted = 0;
        challengeAssigned = 0;
        reputation = 100;
        /*if (!database.dataBaseQueryString("SELECT username FROM users WHERE username='" + username + "'", "username").equals(username)) {
            userID = generateUserID();
            String insertString = "INSERT INTO users (userID, username, password, birthday, birthmonth, birthyear, profilepic, challengesCompleted, challengeAssinged, reputation) VALUES("
                    + userID + ", '" + username + "', '" + password + "', " + birthday + ", '" + profilePic + "', "
                    + challengesCompleted + ", " + challengeAssigned + ", " + reputation + ")";
            database.insertQuery(insertString);
        } else {
            userID = database.dataBaseQueryInt("SELECT userID FROM users WHERE username='" + username + "'", "userID");
        }*/

    }

    /**
     * Assigns a new random challenge to a user
     *
     *
     * @return Challenge ID assigned to user
     */
    public int setNewChallenge() {
        int newID;
        if (challengeAssigned == 0) {
            //generate new challenge
            newID = Randomizer.getRandomInt(100)+1;
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
     * @return If giving up was successful
     */
    public int giveUp() {
        if (challengeAssigned != 0) {
            //challengeAddToDatabase(user.challengeAssigned);     //will be added to control unit
            challengeAssigned = 0;
            return 1;
        } else {
            reputation -= 2;
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
        //nextFreeIDFromDatabase = database.searchLastIndex(query);
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
    public Date getBirthday() {
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
     * Sets the users Name
     *
     * @param name The username
     */
    public void setName(String name) {
        username = name;
        String query = "UPDATE users SET username='" + name +  "' WHERE userID=" + userID;
    }

    /**
     * Sets the users Password
     *
     * @param pass The password
     */
    public void setPass(String pass) {
        password = pass;
        String query = "UPDATE users SET password='" + pass +  "' WHERE userID=" + userID;
    }


    /**
     * Sets the users birth year
     *
     * @param birthday The users birth year
     */
    public void setYear(Date birthday) {
        this.birthday = birthday;

    }

    public void voteForUser(int vote){
        reputation += vote;
    }
}