import Profile.UserData;
import Administration.*;
import Challenges.*;
import Profile.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String name = br.readLine();
        String pass = br.readLine();
        String Tday = br.readLine();
        String Tmon = br.readLine();
        String Tyear = br.readLine();
        int day = Integer.parseInt(Tday);
        int mon = Integer.parseInt(Tmon);
        int year = Integer.parseInt(Tyear);
        UserData user = Registration.register(name, pass, day, mon, year);
    }
}
