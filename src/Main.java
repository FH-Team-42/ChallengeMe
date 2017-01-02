import Profile.UserData;
import Administration.*;
import Challenges.*;
import Profile.*;
import java.util.Random;


import java.io.*;

public class Main {

    public static void main(String[] args) {
        //UNIT TEST
        int counter = 1;
        int TESTS = 299;
        int error = 0;

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
            if (name.equals(user.getName(user))) {
                System.out.println("Name matched.");
            } else {
                System.out.println("Name not matching.");
                error++;
            }
            if (pass.equals(user.getPass(user))) {
                System.out.println("Password matched.");
            } else {
                System.out.println("Password not matching.");
                error++;
            }
            if (Tday.equals(user.getDay(user))) {
                System.out.println("Day matched.");
            } else {
                System.out.println("Day not matching.");
                error++;
            }
            if (Tmon.equals(user.getMonth(user))) {
                System.out.println("Month matched.");
            } else {
                System.out.println("Month not matching.");
                error++;
            }
            if (Tyear.equals(user.getYear(user))) {
                System.out.println("Year matched.");
            } else {
                System.out.println("Year not matching.");
                error++;
            }
            counter++;
        }
        System.out.println("TESTS COMPLETED. Out of "
                + Integer.toString(counter) + " tests, there were " + Integer.toString(error)
                + " errors.");
    }
}
