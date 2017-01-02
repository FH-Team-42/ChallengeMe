package Challenges;

/**
 * Created by Max on 02.01.2017.
 */
public class allChallenges {

    private Challenge[] allChallenges = new Challenge[20];

    public allChallenges() {

    }

    public void addChallenge(Challenge newChallenge){
        int nextChallenge = 0;
        while(allChallenges[nextChallenge] == null) {
               nextChallenge++;
        }
        allChallenges[nextChallenge] = newChallenge;
    }
}


