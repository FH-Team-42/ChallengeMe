package Challenges;


import Administration.ConnectDataBase;
import Administration.TimerListener;

import javax.swing.*;


/**
 * This class represents the challenge data model. The challenge is able to be started,
 * information to it can be returned or the stored information can be edited and passed
 * to the database controller update the database as well
 * On creation of a new challenge it is going to be stored in the database if it is not
 * part of it yet. The ID of the challenge gets automatically fetched from the database.
 *
 * @author Max
 */
public class Challenge {

    private String title;       //title of the challenge
    private String description; //description of the challenge
    private int completionTime; //time to complete challenge
    private int idCreator;      //ID of challenge creator in database
    private int idChallenge;    //ID of challenge in database
    private int idChallenged;   //ID of the user which is assigned this challenge
    private int vote;           //votes of the challenge, delete challenges with too much negative votes
    ConnectDataBase database;   //get a connection to the database

    /**
     * Creates a new challenge and inserts it into the database if it is not part of it yet.
     *
     * @param title          The title of the challenge
     * @param description    The challenge description
     * @param completionTime The time to complete the challenge in seconds
     * @param idCreator      User-ID of user creating the challenge
     */
    public Challenge(String title, String description, int completionTime, int idCreator) {
        this.database = ConnectDataBase.getInstance();
        this.title = title;
        this.description = description;
        this.completionTime = completionTime;
        this.idCreator = idCreator;
        idChallenged = 0;
        vote = 0;
        if (!database.dataBaseQueryString(title, "title", "title", "challenges").equals(this.title)) {
            idChallenge = getNewChallengeID();
            database.insertChallenge(this.title, this.description, this.completionTime, this.idCreator, this.idChallenge);
        }else{
            idChallenge = Integer.parseInt(database.dataBaseQueryString(title, "challengeID", "title", "challenges"));
        }
    }

    /**
     * Starts the countdown for the challenge
     */
    public void startChallenge() {
        TimerListener listener = new TimerListener(completionTime);
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
        nextFreeIDFromDatabase = database.searchLastIndex("challenges");
        return nextFreeIDFromDatabase + 1;
    }

    /**
     * Get the title of the challenge
     *
     * @return The title of the challenge
     */
    public String getTitle() {
    	return title;
    }

    /**
     * Get the description of the challenge
     *
     * @return The description of the challenge
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the challenge creator's ID
     *
     * @return The challenge creator's ID
     */
    public int getCreatorId() {
        return idCreator;
    }

    /**
     * Get the challenge's ID
     *
     * @return The challenge's ID
     */
    public int getChallengeId() {
        return idChallenge;
    }

    /**
     * Get the challenged person's user ID
     *
     * @return The challenged person's user ID
     */
    public int getChallengedId() {
        return idChallenged;
    }

    /**
     * Get the current voting of the challenge
     *
     * @return The current voting of the challenge
     */
    public int getVote() {
        return vote;
    }

    /**
     * Get remaining completion time
     *
     * @return The remaining completion time
     */
    public int getCompletionTime() {
        return completionTime;
    }

    /**
     * Set the title of the challenge
     *
     * @param setTitle The new title
     */
    public void setTitle(String setTitle) {
        title = setTitle;

    	database.updateDataBaseString(setTitle, "title", idChallenge, "challengeID","challenges");
    }

    /**
     * Set the description of the title
     *
     * @param setDescription The new description
     */
    public void setDescription(String setDescription) {
        description = setDescription;

        database.updateDataBaseString(setDescription, "description", idChallenge, "challengeID", "challenges");
    }

    /**
     * Set the completion time
     *
     * @param setCompletionTime The new completion time in seconds
     */
    public void setCompletionTime(int setCompletionTime) {
        completionTime = setCompletionTime;

        database.updateDataBaseInt(setCompletionTime, "completionTime", idChallenge, "challengeID", "challenges");
    }

    /**
     * Set the challenged user's ID
     *
     * @param setChallengedId The challenged user's ID
     */
    public void setChallengedId(int setChallengedId) {
        idChallenged = setChallengedId;

        database.updateDataBaseInt(setChallengedId, "challenged", idChallenge, "challengeID", "challenges");
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
        challengeUserVote = database.dataBaseQueryInt(idChallenge, "votes", "challengeID", "challenges");
        challengeUserVote += value;
    	if(challengeUserVote > -100) {
            database.updateDataBaseInt(challengeUserVote, "votes", idChallenge, "challengeID", "challenges");
        } else {
    	    database.deleteRow("challengeID", idChallenge, "challenges");
        }
    }

    /**
     * Disconnect the database
     */
    public void disconnectDB(){
        database.disconnectDB();
    }
}
