package Challenges;

import Profile.UserData;
import static org.junit.Assert.assertEquals;
import junit.framework.*;
import org.junit.Test;

/**
 * Created by Max on 05.02.2017.
 */
public class unitTestChallenges extends TestCase{

    private int userID = 0;
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


    protected void setUp()
    {
        testUserCreator = new UserData(userNameC, userPasswordC, userBirthdayC, userBirthmonthC, userBirthyearC);
        testUserChallenged = new UserData(userNameChallenged, userPasswordChallenged, userBirthdayChallenged, userBirthmonthChallenged, userBirthyearChallenged);
        testChallenge = new Challenge(challengeName, challengeDescription, challengeTime, testUserCreator.getUserID());
    }

    @Test
    /*
    A test for checking if a user can be assigned a challenge and if it is registered right in the challenge itself.
     */
    public void testChallengeAssignmentToUser(){
        System.out.println("Tests if the assingment of a challenge to a user is working");
        testChallenge.setChallengedId(testUserChallenged.getUserID());
        assertEquals(testChallenge.getChallengendId(), testUserChallenged.getUserID());
    }

    @Test
    /*
    This tests the increment/decrement of the reputation of the challenge.
     */
    public void testUserVoting(){
        System.out.println("Testing of user voting");
        int oldVote = testChallenge.getVote();
        System.out.println("Old vote: "+ testChallenge.getVote());
        testChallenge.userVote(1);
        System.out.println("New vote: "+ testChallenge.getVote());
        oldVote++;
        assertEquals(testChallenge.getVote(), oldVote);
        testChallenge.userVote(-1);
        System.out.println("New decrement vote: "+ testChallenge.getVote());
        oldVote--;
        assertEquals(testChallenge.getVote(), oldVote);
    }

    @Test
    /*
    This Method tests if there occurred any errors while creating a challenge.
     */
    public void testTheChallenge(){
        System.out.println("Testing the assignment of the attributes of challenge");
        assertEquals(challengeName, testChallenge.getTitle());
        assertEquals(challengeDescription, testChallenge.getDescription());
        assertEquals(challengeTime, testChallenge.getCompletionTime());
        assertEquals(testUserCreator.getUserID(), testChallenge.getCreatorId());
    }
}
