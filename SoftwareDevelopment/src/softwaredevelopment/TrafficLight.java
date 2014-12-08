/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficLight {

    private int status = 1;
    private String state;
    private String stopLightName;
    private boolean active;
    private SoftwareDevServer softDevServer;
    public int stoplightRow;
    private String conflicts[];

    static Timer timer;
    static int count;

    public TrafficLight(String stoplight) {
        softDevServer = new SoftwareDevServer();
        active = true;
        stopLightName = stoplight;
        stoplightRow = 0;
        getConflicts();
    }
    
    public String[] getConflicts(){
        if (stopLightName.equals("NWA")){
            conflicts = new String[2];
            conflicts[0] = "OWA";
            conflicts[1] = "ZWA";
        }
        if (stopLightName.equals("NZA")){
            conflicts = new String[5];
            conflicts[0] = "OWA";
            conflicts[1] = "ZWA";
            conflicts[2] = "WNA";
            conflicts[3] = "WOA";
            conflicts[4] = "WZA";
        }
        if (stopLightName.equals("NOA")){
            conflicts = new String[6];
            conflicts[0] = "OWA";
            conflicts[1] = "ZNA";
            conflicts[2] = "ZOA";
            conflicts[3] = "ZWA";
            conflicts[4] = "WNA";
            conflicts[5] = "WOA";
        }
        if (stopLightName.equals("ONA")){
            conflicts = new String[3];
            conflicts[0] = "ZNA";
            conflicts[1] = "ZOA";
            conflicts[2] = "WNA";
        }
        if (stopLightName.equals("OWA")){
            conflicts = new String[7];
            conflicts[0] = "NOA";
            conflicts[1] = "NZA";
            conflicts[2] = "NWA";
            conflicts[3] = "ZNA";
            conflicts[4] = "ZOA";
            conflicts[5] = "ZWA";
            conflicts[6] = "WNA";
        }
        if (stopLightName.equals("ZNA") || stopLightName.equals("ZOA")){
            conflicts = new String[5];
            conflicts[0] = "NOA";
            conflicts[1] = "ONA";
            conflicts[2] = "OWA";
            conflicts[3] = "WNA";
            conflicts[4] = "WOA";
        }
        if (stopLightName.equals("ZWA")){
            conflicts = new String[6];
            conflicts[0] = "NOA";
            conflicts[1] = "NZA";
            conflicts[2] = "NWA";
            conflicts[3] = "OWA";
            conflicts[4] = "WNA";
            conflicts[5] = "WOA";
        }
        if (stopLightName.equals("WNA")){
            conflicts = new String[7];
            conflicts[0] = "NOA";
            conflicts[1] = "NZA";
            conflicts[2] = "ONA";
            conflicts[3] = "OWA";
            conflicts[4] = "ZNA";
            conflicts[5] = "ZOA";
            conflicts[6] = "ZWA";
        }
        if (stopLightName.equals("WOA")){
            conflicts = new String[5];
            conflicts[0] = "NOA";
            conflicts[1] = "NZA";
            conflicts[2] = "ZNA";
            conflicts[3] = "ZOA";
            conflicts[4] = "ZWA";
        }
        if (stopLightName.equals("WZA")){
            conflicts = new String[1];
            conflicts[0] = "NZA";
        }
        return conflicts;
    }

    public String statusString() {
        switch (status) {
            case 1:
                state = "R";    //Red        
                startTimer(2);
                active = false;
                break;
            case 2:
                state = "G";    //Green    
                if (stoplightRow > 6){
                    startTimer(stoplightRow);
                } else {
                    startTimer(6);
                }
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

    public void nextStatus() {
        if (status < 3) {
            status++;
        } else {
            status = 1;
        }
    }

    public void setState(String inputstate) {
        state = inputstate;
    }

    public String getState() {
        return state;
    }

    public void setRowAmount(char amount) {
        if (amount != stoplightRow) {
            stoplightRow = Character.getNumericValue(amount);
        }
        System.out.println("Row at " + stopLightName + " set to : " + stoplightRow);
    }

    public void resetRowAmount() {
        stoplightRow = 0;
    }

    public int getRowAmount() {
        return stoplightRow;
    }

    public void deactivate() {
        active = false;
    }

    public void activate() {
        active = true;
    }

    public boolean getActive() {
        return active;
    }

    public String getName() {
        return stopLightName;
    }

    public void startTimer(final int time) {
        final java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = time;

            public void run() {
                //System.out.println("Server: Time = " + i + "\n");
                i--;
                if (i < 0) {
                    timer.cancel();
                    if (active == true) {
                        nextStatus();
                        softDevServer.mServer.send(stopLightName + statusString());
                        System.out.println(stopLightName + statusString());
                    } else {
                        //status = 0;
                        timer.cancel();
                    }
                }
            }
        }, 0, 1000);
    }
}
