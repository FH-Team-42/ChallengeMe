package Challenges;


public class Challenge {

    private String title;
    private String description;
    private int CompletionTime;
    private int idCreator;
    private int idChallenge;
    private int idChallenged;
    private int vote;

    public Challenge(){
        vote=0;
    }

    public String getTitle(){
        return title;
    }
    public String description(){
        return description;
    }
    public int CompletionTime(){
        return CompletionTime;
    }

    public int getCreatorId(){
        return idCreator;
    }

    public int getChallengeId() {
        return idChallenge;
    }
    public int getChallengendId() {
        return idChallenged;
    }
    public int getVote() {
        return vote;
    }
    public void setTitle(String setTitle) {
        title = setTitle;
    }
    public void setDescription(String setDescription) {
        description = setDescription;
    }
    public void setCompletionTime(int setCompletionTime) {
        CompletionTime = setCompletionTime;
    }
    public void setCreatorId(int setCreatorId) {
        idCreator = setCreatorId;
    }
    public void setChallengeId(int setChallengeId) {
        idChallenge = setChallengeId;
    }
    public void setChallengedId(int setChallengedId) {
        idChallenged = setChallengedId;
    }

    public void userVote(int newVote){
        vote += newVote;
    }
}
