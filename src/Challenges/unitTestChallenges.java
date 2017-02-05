package Challenges;

import Profile.UserData;
import junit.framework.*;
import org.junit.*;
import org.junit.Test;

/**
 * Created by Max on 05.02.2017.
 */
public class unitTestChallenges {
    @Test
    protected void setUp()
    {
        UserData testUser = new UserData();
        Challenge testChallenge = new Challenge("test", "du musst das und das machen.", 350, 1);

    }
}
