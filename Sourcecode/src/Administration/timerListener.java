package Administration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Created by Max on 05.02.2017.
 *
 * This class is responsible for starting a countdown if the challenge is started.
 */
public class TimerListener implements ActionListener {

    int challengeCompletionTime;

    public TimerListener(int completionTimeOfStartingChallenge){
        challengeCompletionTime = completionTimeOfStartingChallenge;
    }

    @Override
    /*
    This should start the countdown and count down 1 every second
     */
    public void actionPerformed(ActionEvent e) {

        challengeCompletionTime--;
        System.out.println(challengeCompletionTime);
        if(challengeCompletionTime==0){

        }
    }
}
