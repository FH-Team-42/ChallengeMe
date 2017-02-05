package Profile;


import java.io.PrintWriter;

/**
 * Created by Max on 02.01.2017.
 */
public class Registration {
    public static UserData register(String name, String password, int day, int month, int year) {
        UserData user = new UserData();
        user.setName(user, name);
        user.setDay(user, day);
        user.setMonth(user, month);
        user.setyear(user, year);
        user.setPass(user, password);

        /* get next free id from database */

        return user;
    }
}
