package Profile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Max on 02.01.2017.
 */
public class Registration {

    /**
     * This function takes user data and saves a new user in the database
     * @param name The username
     * @param password The user password, encrypted with SHA-256
     * @param day The birthday of the user
     * @param month The birthmonth of the user
     * @param year The birthyear of the user
     * @return The unique user ID
     */
    public static UserData register(String name, String password, int day, int month, int year) {
        UserData user = new UserData(name, password, day, month, year);

        /* get next free id from database */

        return user;
    }

    public PrintWriter createWriter() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("database.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return writer;
    }
}
