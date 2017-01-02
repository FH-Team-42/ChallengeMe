package Profile;

import java.io.*;
/**
 * Created by Max on 02.01.2017.
 */
public class Registration {
    public static UserData register(String name, String password, int day, int month, int year) {
        UserData user = new UserData();
        user.username = name;
        user.birthday = day;
        user.birthmonth = month;
        user.birthyear = year;
        user.password = password;

        return user;
    }
}
