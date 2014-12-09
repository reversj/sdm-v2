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

    private int status = 1;
    private String state;
    private boolean active;
    private SoftwareDevServer softDevServer;
    private String stopLightName;
    private int stoplightRow;

    static Timer timer;
    static int count;

    public TrafficLight(String stoplight) {
        softDevServer = new SoftwareDevServer();
        active = true;
        stopLightName = stoplight;
        stoplightRow = 0;
    }

    public String stateString() {
        switch (status) {
            case 1:
                state = "R";    //Red        
                startTimer(2);
                active = false;
                softDevServer.setActive(true);
                break;
            case 2:
                state = "G";    //Green    
                startTimer(6);
                break;
            case 3:
                state = "O";   //Orange
                startTimer(4);
                break;
            default:
                state = "Done";
        }
        return state;
    }

    public void setState(String inputstate) {
        state = inputstate;
    }

    public String getState() {
        return state;
    }

    public boolean getActive() {
        return active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public String getName() {
        return stopLightName;
    }

    public void setRowAmount(char amount) {
        stoplightRow = Character.getNumericValue(amount);
    }

    public int getRowAmount() {
        return stoplightRow;
    }

    public void resetRowAmount() {
        stoplightRow = 0;
    }

    public void nextStatus() {
        if (status < 3) {
            status++;
        } else {
            status = 1;
        }
    }

    public void startTimer(final float time) {
        final java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            float i = time;

            public void run() {
                i--;
                if (i < 0) {
                    timer.cancel();
                    if (active == true) {
                        nextStatus();
                        String temp = stateString();
                        softDevServer.mServer.send(stopLightName + temp);
                    } else {
                        timer.cancel();
                        //status = 0;test
                    }
                }
            }
        }, 0, 1000);
    }
}
