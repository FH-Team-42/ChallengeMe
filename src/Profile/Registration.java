package Profile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Max on 02.01.2017.
 */
public class Registration {
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
