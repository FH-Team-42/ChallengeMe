package Profile;


import java.io.PrintWriter;

/**
 * Created by Max on 02.01.2017.
 */
public class Registration {
    public static UserData register(String name, String password, int day, int month, int year) {
        UserData user = new UserData(name, password, day, month, year);

        /* get next free id from database */

        return user;
    }
}
