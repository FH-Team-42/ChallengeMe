package Challenges;


import Administration.timerListener;
import Administration.connectDataBase;

import javax.swing.*;

public class Challenge {

    private String title;       //title of the challenge
    private String description; //description of the challenge
    private int completionTime; //time to complete challenge
    private int idCreator;      //ID of challenge creator in database
    private int idChallenge;    //ID of challenge in database
    private int idChallenged;   //ID of the user which is assigned this challenge
    private int vote;           //votes of the challenge, delete challenges with too much negative votes
    connectDataBase database = new connectDataBase(); //connection to the database

    /**
     * Creates a new challenge and inserts it into the database if it is not part of it yet.
     *
     * @param title          The title of the challenge
     * @param description    The challenge description
     * @param completionTime The time to complete the challenge in seconds
     * @param idCreator      User-ID of user creating the challenge
     */
    public Challenge(String title, String description, int completionTime, int idCreator) {
        this.title = title;
        this.description = description;
        this.completionTime = completionTime;
        this.idCreator = idCreator;
        idChallenged = 0;
        vote = 0;
        if (!database.dataBaseQueryString("SELECT * FROM challenges WHERE title='" + this.title + "'", "title").equals(this.title)) {
            idChallenge = getNewChallengeID();
            String insertString = "INSERT INTO challenges (ChallengeID, challenged, creator, title, description, completionTime, votes) VALUES("
                    + idChallenge + ", " + idChallenged + ", " + idCreator + ", '" + title + "', '" + description + "', " + completionTime + ", " + vote + ")";
            database.insertQuery(insertString);
        }else{
            idChallenge = database.dataBaseQueryInt("SELECT * FROM challenges WHERE title='" + this.title + "'", "ChallengeID");
        }
    }

    /**
     * Starts the countdown for the challenge
     */
    public void startChallenge() {
        timerListener listener = new timerListener(completionTime);
        Timer timer = new Timer(1000, listener);
        timer.start();
    }

    /**
     * Gets the next free ID from the challenge database
     *
     * @return Free challenge ID
     */
    public int getNewChallengeID() {
        int nextFreeIDFromDatabase = 0;
        String query = "SELECT * FROM challenges ORDER BY challengeID DESC";
        nextFreeIDFromDatabase = database.searchLastIndex(query);
        return nextFreeIDFromDatabase + 1;
    }

    /**
     * Get the title of the challenge
     *
     * @return The title of the challenge
     */
    public String getTitle() {
    	String challengeTitle;
    	String abfragColumn = "title";
    	String query = "SELECT * FROM challenges WHERE challengeID = " + idChallenge;
    	challengeTitle = database.dataBaseQueryString(query, abfragColumn); 
    	return challengeTitle;
    }

    /**
     * Get the description of the challenge
     *
     * @return The description of the challenge
     */
    public String getDescription() {
    	String challengeDescription;
    	String abfrageColumn = "description";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeDescription = database.dataBaseQueryString(query, abfrageColumn);
        return challengeDescription;
    }

    /**
     * Get the challenge creator's ID
     *
     * @return The challenge creator's ID
     */
    public int getCreatorId() {
    	int challengeCreatorID;
    	String abfrageColumn = "creator";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeCreatorID = database.dataBaseQueryInt(query, abfrageColumn);
        return challengeCreatorID;
    }

    /**
     * Get the challenge's ID
     *
     * @return The challenge's ID
     */
    public int getChallengeId() {
    	int challengeChallengeID;
    	String abfrageColumn = "ChallengeID";
    	String query = "SELECT * From challenges WHERE title = " + title;
    	challengeChallengeID = database.dataBaseQueryInt(query, abfrageColumn);
        return challengeChallengeID;
    }

    /**
     * Get the challenged person's user ID
     *
     * @return The challenged person's user ID
     */
    public int getChallengedId() {
    	int challengeChallengedID;
    	String abfrageColumn = "challenged";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeChallengedID = database.dataBaseQueryInt(query, abfrageColumn);
        return challengeChallengedID;
    }

    /**
     * Get the current voting of the challenge
     *
     * @return The current voting of the challenge
     */
    public int getVote() {
    	int challengeVote;
    	String abfrageColumn = "votes";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeVote = database.dataBaseQueryInt(query, abfrageColumn);
        return challengeVote;
    }

    /**
     * Get remaining completion time
     *
     * @return The remaining completion time
     */
    public int getCompletionTime() {
    	int challengeCompletionTime;
    	String abfrageColumn = "completionTime";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeCompletionTime = database.dataBaseQueryInt(query, abfrageColumn);
        return challengeCompletionTime;
    }

    /**
     * Set the title of the challenge
     *
     * @param setTitle The new title
     */
    public void setTitle(String setTitle) {
        title = setTitle;
    	String query = "UPDATE challenges SET title=" + setTitle + " WHERE challengeID = " + idChallenge;
    	database.insertQuery(query);
    }

    /**
     * Set the description of the title
     *
     * @param setDescription The new description
     */
    public void setDescription(String setDescription) {
        description = setDescription;
    	String query = "UPDATE challenges SET description=" + setDescription + " WHERE challengeID = " + idChallenge;
    	database.insertQuery(query);
    }

    /**
     * Set the completion time
     *
     * @param setCompletionTime The new completion time in seconds
     */
    public void setCompletionTime(int setCompletionTime) {
        completionTime = setCompletionTime;
    	String query = "UPDATE challenges SET completionTime=" + setCompletionTime + " WHERE challengeID = " + idChallenge;
    	database.insertQuery(query);
    }

    /**
     * Set the challenged user's ID
     *
     * @param setChallengedId The challenged user's ID
     */
    public void setChallengedId(int setChallengedId) {
        idChallenged = setChallengedId;
    	String query = "UPDATE challenges SET challenged=" + setChallengedId + " WHERE challengeID = " + idChallenge;
    	database.insertQuery(query);
    }

    /**
     * Vote the challenge
     * First catches the old vote from the database and then writes the new value into it
     *
     * @param value The value to add (1 for positive, -1 for negative vote)
     */
    public void userVote(int value) {
    	vote += value;
    	int challengeUserVote;
    	String abfrageColumn = "votes";
    	String query = "SELECT * From challenges WHERE challengeID = " + idChallenge;
    	challengeUserVote = database.dataBaseQueryInt(query, abfrageColumn);
    	challengeUserVote += value;
    	
    	String query2 = "UPDATE challenges SET votes=" + challengeUserVote + " WHERE challengeID = " + idChallenge;
    	database.insertQuery(query2);
    }

    /**
     * Disconnect the database
     */
    public void disconnectDB(){
        database.disconnectDB();
    }
}
