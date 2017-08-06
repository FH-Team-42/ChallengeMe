package Challenges;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 02.01.2017.
 *
 * An ArrayList which stores all Challenges from the current Session.
 */
public class allChallenges {
    List<Challenge> allChallenges = new ArrayList<Challenge>();

    public allChallenges() {

    }

    public Challenge getArrayIndex(int index) {
        return allChallenges.get(index);
    }


    public void addChallenge(Challenge newChallenge) {
        allChallenges.add(newChallenge);
    }
}