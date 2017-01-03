package Challenges;

/**
 * Created by Max on 02.01.2017.
 */
public class allChallenges {
    int TESTS = 300;
    Challenge[] allChallenges = new Challenge[TESTS + 1];

    public allChallenges() {

    }

    public void addChallenge(Challenge newChallenge) {
        int nextChallenge = 0;
        while (allChallenges[nextChallenge] == null) {
            nextChallenge++;
        }
        allChallenges[nextChallenge] = newChallenge;
    }
}