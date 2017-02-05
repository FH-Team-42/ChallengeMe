package Profile;

import Challenges.Challenge;
import junit.framework.*;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static javax.xml.transform.OutputKeys.ENCODING;

/**
 * Created by Felix on 05.02.2017.
 */
public class UnitTestsProfile extends TestCase {
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

    private void database_write(PrintWriter writer) {
        writer.println(userNameC);
        writer.println(userPasswordC);
        writer.println(userBirthdayC);
        writer.println(userBirthmonthC);
        writer.println(userBirthyearC);
        writer.println(userNameChallenged);
        writer.println(userPasswordChallenged);
        writer.println(userBirthdayChallenged);
        writer.println(userBirthmonthChallenged);
        writer.println(userBirthyearChallenged);
        writer.close();
    }
/*
    private String[] database_load(String aFileName) {
        Path path = Paths.get(aFileName);
        return Files.readAllLines(path, ENCODING);
    }

    protected void setUp()
    {
        testUserCreator = new UserData(userNameC, userPasswordC, userBirthdayC, userBirthmonthC, userBirthyearC);
        testUserChallenged = new UserData(userNameChallenged, userPasswordChallenged, userBirthdayChallenged, userBirthmonthChallenged, userBirthyearChallenged);
        testChallenge = new Challenge(challengeName, challengeDescription, challengeTime, testUserCreator.getUserID());
    }
*/
    /* Test for database integrity */
    /*
    @Test public void testDatabaseIntegrity() {
        PrintWriter writer = Registration.createWriter();
        database_write(writer);
        database_load("database.txt");
    }
    */
}
