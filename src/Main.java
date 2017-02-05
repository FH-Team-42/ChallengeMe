import Profile.UserData;
import Administration.*;
import Challenges.*;
import Profile.*;
import java.util.Random;


import java.io.*;

/**
 * Created by Felix on 02.01.2017.
 */

public class Main {

    public static void main(String[] args) {
        /* unit test */
        int counter = 1;
        int TESTS = 20;
        int error = 0;
        int fillIndex = counter - 1;

        allChallenges challengeArray = new allChallenges();

        while (counter <= TESTS) {
            System.out.println("Starting test " + Integer.toString(counter) + "...");
            Random rand = new Random();
            String name = Integer.toString(rand.nextInt(999999));
            String pass = Integer.toString(rand.nextInt(999999));
            String Tday = Integer.toString(rand.nextInt(999999));
            String Tmon = Integer.toString(rand.nextInt(999999));
            String Tyear = Integer.toString(rand.nextInt(999999));
            int day = Integer.parseInt(Tday);
            int mon = Integer.parseInt(Tmon);
            int year = Integer.parseInt(Tyear);
            UserData user = Registration.register(name, pass, day, mon, year);
            if (name.equals(user.getName())) {
                System.out.println("Name matched.");
            } else {
                System.out.println("Name not matching.");
                error++;
            }
            if (pass.equals(user.getPass())) {
                System.out.println("Password matched.");
            } else {
                System.out.println("Password not matching.");
                error++;
            }
            if (Tday.equals(user.getDay())) {
                System.out.println("Day matched.");
            } else {
                System.out.println("Day not matching.");
                error++;
            }
            if (Tmon.equals(user.getMonth())) {
                System.out.println("Month matched.");
            } else {
                System.out.println("Month not matching.");
                error++;
            }
            if (Tyear.equals(user.getYear())) {
                System.out.println("Year matched.");
            } else {
                System.out.println("Year not matching.");
                error++;
            }
            System.out.println("Adding challenge to user...");
            Challenge challenge = new Challenge();
            challengeArray.addChallenge(challenge);
            counter++;
        }
        counter--;
        System.out.println("---GENERATED ARRAY---");
        while (fillIndex < TESTS) {
            System.out.println("Array Index " + Integer.toString(fillIndex) +
                    ": " + challengeArray.getArrayIndex(fillIndex));
            fillIndex++;
        }
        System.out.println("TESTS COMPLETED. Out of "
                + Integer.toString(counter) + " tests, there were " + Integer.toString(error)
                + " errors.");
    }
}
