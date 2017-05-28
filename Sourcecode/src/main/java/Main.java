package src;

import Challenges.*;
import Profile.*;
import Chat.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by Felix on 02.01.2017.
 */

public class Main {

    public static void main(String[] args) {
        Server server = new Server(56000);
        server.start();

        Client client = new Client("127.0.0.1", 56000, "TheCh4lleng3r");
        client.start();

        Client client2 = new Client("127.0.0.1", 56000, "Horst");
        client2.start();
    }
}
