package src;

import Profile.UserData;
import Challenges.Challenge;


import junit.framework.*;
import org.junit.Test;

/**
 * Created by Max on 05.02.2017.
 */
public class unitTestChallenges extends TestCase {

    private UserData testUserCreator;
    private UserData testUserChallenged;
    private Challenge testChallenge;
    private String challengeName = "test";
    private String challengeDescription = "du musst das und das machen.";
    private int challengeTime = 350;
    private String userNameC = "Max";
    private String userPasswordC = "passWord";
    private int userBirthdayC = 29;
    private int userBirthmonthC = 03;
    private int userBirthyearC = 1995;
    private String userNameChallenged = "Peter";
    private String userPasswordChallenged = "passWord2";
    private int userBirthdayChallenged = 15;
    private int userBirthmonthChallenged = 03;
    private int userBirthyearChallenged = 1996;

    /*
    This method is called before every test and creates 2 new users and 1 challenge. It also writes them in the data base
    if they are not part of it yet.
     */
    public void setUp() {
        testUserCreator = new UserData(userNameC, userPasswordC, userBirthdayC, userBirthmonthC, userBirthyearC);
        testUserChallenged = new UserData(userNameChallenged, userPasswordChallenged, userBirthdayChallenged, userBirthmonthChallenged, userBirthyearChallenged);
        testChallenge = new Challenge(challengeName, challengeDescription, challengeTime, testUserCreator.getUserID());
    }


    @Test
    /*
    A test for checking if a user can be assigned a challenge and if it is registered right in the challenge itself.
     */
    public void testChallengeAssignmentToUser() {
        System.out.println("Tests if the assingment of a challenge to a user is working");
        if (testUserChallenged.setNewChallenge() >= 0) {
            testChallenge.setChallengedId(testUserChallenged.getUserID());
        }
        assertEquals(testChallenge.getChallengedId(), testUserChallenged.getUserID());
        testChallenge.disconnectDB();
        testUserChallenged.disconnectDB();
        testUserCreator.disconnectDB();
    }

    @Test
    /*
    This tests the increment/decrement of the reputation of the challenge.
     */
    public void testUserVotingOnAChallenge() {
        System.out.println("\n Testing of user voting");
        int expectedVote = testChallenge.getVote();
        System.out.println("Old vote: " + testChallenge.getVote());
        testChallenge.userVote(1);
        System.out.println("New vote: " + testChallenge.getVote());
        expectedVote++;
        assertEquals(testChallenge.getVote(), expectedVote);
        testChallenge.userVote(-1);
        System.out.println("New decrement vote: " + testChallenge.getVote());
        expectedVote--;
        assertEquals(testChallenge.getVote(), expectedVote);
        testChallenge.disconnectDB();
        testUserChallenged.disconnectDB();
        testUserCreator.disconnectDB();
    }

    @Test
    /*
    This Method tests if there occurred any errors while creating a challenge.
     */
    public void testTheChallenge() {
        System.out.println("\n Testing the assignment of the attributes of challenge");
        assertEquals(challengeName, testChallenge.getTitle());
        assertEquals(challengeDescription, testChallenge.getDescription());
        assertEquals(challengeTime, testChallenge.getCompletionTime());
        assertEquals(testUserCreator.getUserID(), testChallenge.getCreatorId());
        testChallenge.disconnectDB();
        testUserChallenged.disconnectDB();
        testUserCreator.disconnectDB();
    }

    @Test
    /*
    Tests if a user voting on another User is working.
     */
    public void testUserVotingOnAUser() {
        System.out.println("\n Testing user voting on another users reputation");
        int expectedReputation = testUserCreator.getReputation();
        System.out.println("Old reputation: " + expectedReputation);
        testUserCreator.voteForUser(1);
        System.out.println("New reputation: " +testUserCreator.getReputation());
        expectedReputation++;
        assertEquals(testUserCreator.getReputation(), expectedReputation);
        testUserCreator.voteForUser(-1);
        System.out.println("New decrement reputation: " + testUserCreator.getReputation());
        expectedReputation--;
        assertEquals(testUserCreator.getReputation(), expectedReputation);
        testChallenge.disconnectDB();
        testUserChallenged.disconnectDB();
        testUserCreator.disconnectDB();
    }
}
