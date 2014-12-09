/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

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
    

    public TrafficLight(String stoplight) {
        softDevServer = new SoftwareDevServer();
        active = true;
        stopLightName = stoplight;
        stoplightRow = 0;
    }

    public String statusString() {
        switch (status) {
            case 1:
                state = "R";    //Red        
                startTimer(2);
                active = false;
                softDevServer.setActive(true);
                System.out.println("Server: Status = " + status + "\n");
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

    public void nextStatus() {
        if (status < 3) {
            status++;
        } else {
            status = 1;
        }
    }
    
    public void setState(String inputstate){
        state = inputstate;
    }
    
    public String getState(){
        return state;
    }
    
    public void setRowAmount(char amount){
        stoplightRow = Character.getNumericValue(amount);
        System.out.println("Row set to : " + amount);
    }
    
    public int getRowAmount(){
        return stoplightRow;
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
                        softDevServer.mServer.send(stopLightName + statusString());
                    } else {
                        status = 0;
                    }
                    System.out.println(stopLightName + statusString());
                }
            }
        }, 0, 1000);
    }
}
