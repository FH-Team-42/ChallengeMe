import Challenges.Challenge;
import Challenges.allChallenges;
import Profile.Registration;
import Profile.UnitTestsProfile;
import Profile.UserData;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Felix on 02.01.2017.
 */

public class Main {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UnitTestsProfile.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
