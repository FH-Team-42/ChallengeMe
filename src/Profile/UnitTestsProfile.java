package Profile;

import Challenges.Challenge;
import junit.framework.*;
import java.io.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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

    private void database_write(PrintWriter writer, String[][] array) {
        System.out.println("Generating...");
        System.out.println("Saving to file...");
        writer.println(userNameC);
        array[0][0] = userNameC;
        writer.println(userPasswordC);
        array[0][1] = userPasswordC;
        writer.println(userBirthdayC);
        array[0][2] = Integer.toString(userBirthdayC);
        writer.println(userBirthmonthC);
        array[0][3] = Integer.toString(userBirthmonthC);
        writer.println(userBirthyearC);
        array[0][4] = Integer.toString(userBirthyearC);
        writer.println(userNameChallenged);
        array[1][0] = userNameChallenged;
        writer.println(userPasswordChallenged);
        array[1][1] = userPasswordChallenged;
        writer.println(userBirthdayChallenged);
        array[1][2] = Integer.toString(userBirthdayChallenged);
        writer.println(userBirthmonthChallenged);
        array[1][3] = Integer.toString(userBirthmonthChallenged);
        writer.println(userBirthyearChallenged);
        array[1][4] = Integer.toString(userBirthyearChallenged);
        writer.close();
    }

    private void database_load(String aFileName, String[][] array) {
        System.out.println("Loading from file...");
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(aFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < 5; k++) {
                try {
                    array[i][k] = buffer.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void setUp()
    {
        testUserCreator = new UserData(userNameC, userPasswordC, userBirthdayC, userBirthmonthC, userBirthyearC);
        testUserChallenged = new UserData(userNameChallenged, userPasswordChallenged, userBirthdayChallenged, userBirthmonthChallenged, userBirthyearChallenged);
        testChallenge = new Challenge(challengeName, challengeDescription, challengeTime, testUserCreator.getUserID());
    }

    /* Test for database integrity */

    @Test public void testDatabaseIntegrity() {
        System.out.println("Testing database integrity...");
        String[][] array0 = new String[2][5];
        String[][] array1 = new String[2][5];
        Registration register = new Registration();
        PrintWriter writer = register.createWriter();
        database_write(writer, array0);
        database_load("database.txt", array1);
        System.out.println("Testing...");
        for (int i = 0; i < 1; i++) {
            for (int k = 0; k < 5; k++) {
                System.out.println("Array0[" + Integer.toString(i) + "][" + Integer.toString(k) + "] matched Array1[" + Integer.toString(i) + "][" + Integer.toString(k) + "]");
                assertEquals(array0[i][k], array1[i][k]);
            }
        }
    }
}
