package Challenges;

import Profile.UserData;
import static org.junit.Assert.assertEquals;
import junit.framework.*;
import org.junit.*;
import org.junit.Test;

/**
 * Created by Max on 05.02.2017.
 */
public class unitTestChallenges {

    UserData testUser;
    Challenge testChallenge;
    String challengeName = "test";
    String challengeDescription = "du musst das und das machen.";
    int challengeTime = 350;
    String userName = "Max";
    String userPassword = "passWort";
    int userBirthday = 29;
    int userBirthmonth = 03;
    int userBirthyear = 1995;

    protected void setUp()
    {
        testUser = new UserData(userName, userPassword, userBirthday, userBirthmonth, userBirthyear);
        testChallenge = new Challenge(challengeName, challengeDescription, challengeTime, testUser.getUserID());
    }

    @Test
    protected void testTheChallenge(){
        assertEquals(userName, testUser.getName());
    }
}
