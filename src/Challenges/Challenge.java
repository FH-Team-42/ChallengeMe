package Challenges;


import Administration.timerListener;
import Administration.connectDataBase;

import javax.swing.*;

public class Challenge {

    private String title;
    private String description;
    private int completionTime;
    private int idCreator;
    private int idChallenge;
    private int idChallenged;
    private int vote;
    connectDataBase database = new connectDataBase();
    ;

    /**
     * Creates a new challenge and inserts it into the database
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
            database.insertQery(insertString);
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
    public int getChallengendId() {
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
     * Set the challenge's creator ID
     *
     * @param setCreatorId The challenge creator's user ID
     */
    public void setCreatorId(int setCreatorId) {
        idCreator = setCreatorId;
    }

    /**
     * Set the challenge ID
     *
     * @param setChallengeId The new challenge ID
     */
    public void setChallengeId(int setChallengeId) {
        idChallenge = setChallengeId;
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
     *
     * @param value The value to add (1 for positive, -1 for negative vote)
     */
    public void userVote(int value) {
        vote += value;
    }
}
