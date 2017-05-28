package Profile;


import java.util.Date;

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
    public static UserData register(String name, String password, Date day, int month, int year) {
        UserData user = new UserData(name, password, day, month, year);
        return user;
    }
}
