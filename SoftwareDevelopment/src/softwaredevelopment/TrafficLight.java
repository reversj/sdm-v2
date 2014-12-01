/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficLight {

    public int status = 1;
    public String stat, response;
    public boolean active;
    public int prio, amount, trigger;
    public SoftwareDevServer soft;
    public String stoplight;

    public TrafficLight(String stoplight) {
        soft = new SoftwareDevServer();
        active = true;
        this.stoplight = stoplight;
    }

    public String statusString() {
        switch (status) {
            case 1:
                stat = "R";    //Red        
                startTimer(2);
                active = false;
                soft.active = true;
                System.out.println("Server: Status = " + status + "\n");
                break;
            case 2:
                stat = "G";    //Green    
                startTimer(6);
                break;
            case 3:
                stat = "O";   //Orange
                startTimer(4);
                break;
            default:
                stat = "Done";
        }
        return stat;
    }

    public void nextStatus() {
        if (status < 3) {
            status++;
        } else {
            status = 1;
        }
    }

    public void startTimer(final int time) {
        final java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = time;

            public void run() {
                System.out.println("Server: Time = " + i + "\n");
                i--;
                if (i < 0) {
                    timer.cancel();
                    if (active == true) {
                        nextStatus();
                        soft.mServer.send(stoplight + "A" + statusString());
                    } else {
                        status = 0;
                    }
                    System.out.println(stoplight + "A" + statusString());
                }
            }
        }, 0, 1000);
    }
}
