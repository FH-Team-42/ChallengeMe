package model.challenges;

import javax.persistence.*;

import administration.jpa.daos.AbstractDatabaseEntity;
import administration.timerListener;

import javax.swing.*;

@Entity (name = "challenges")
public class Challenge extends AbstractDatabaseEntity{

    @Column
    private String title;       //title of the challenge

    @Column
    private String description; //description of the challenge

    @Column
    private int completionTime; //time to complete challenge

    @Column
    private int idCreator;      //ID of challenge creator in database

    @Column
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idChallenge;    //ID of challenge in database

    @Column
    private int idChallenged;   //ID of the user which is assigned this challenge

    @Column
    private int vote;           //votes of the challenge, delete challenges with too much negative votes


    //connectDataBase database = new connectDataBase(); //connection to the database


    public Challenge() {

    }
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
       /* if (!database.dataBaseQueryString("SELECT * FROM challenges WHERE title='" + this.title + "'", "title").equals(this.title)) {
            idChallenge = getNewChallengeID();
            String insertString = "INSERT INTO challenges (ChallengeID, challenged, creator, title, description, completionTime, votes) VALUES("
                    + idChallenge + ", " + idChallenged + ", " + idCreator + ", '" + title + "', '" + description + "', " + completionTime + ", " + vote + ")";
            database.insertQuery(insertString);
        }else{
            idChallenge = database.dataBaseQueryInt("SELECT * FROM challenges WHERE title='" + this.title + "'", "ChallengeID");
        }*/
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
        return idChallenge;
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

    }

    /**
     * Set the description of the title
     *
     * @param setDescription The new description
     */
    public void setDescription(String setDescription) {
        description = setDescription;

    }

    /**
     * Set the completion time
     *
     * @param setCompletionTime The new completion time in seconds
     */
    public void setCompletionTime(int setCompletionTime) {
        completionTime = setCompletionTime;

    }

    /**
     * Set the challenged user's ID
     *
     * @param setChallengedId The challenged user's ID
     */
    public void setChallengedId(int setChallengedId) {
        idChallenged = setChallengedId;

    }

    /**
     * Vote the challenge
     * First catches the old vote from the database and then writes the new value into it
     *
     * @param value The value to add (1 for positive, -1 for negative vote)
     */
    public void userVote(int value) {
    	vote += value;
    }

}
