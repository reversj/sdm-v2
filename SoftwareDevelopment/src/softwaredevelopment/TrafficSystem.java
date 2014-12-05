/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficSystem extends Thread {

    TrafficLight currentLight = new TrafficLight("CL");
    TrafficLock Lock;
    /* AUTOS */
    TrafficLight AUTO_NOORD_OOST = new TrafficLight("NOA");
    TrafficLight AUTO_NOORD_ZUID = new TrafficLight("NZA");
    TrafficLight AUTO_NOORD_WEST = new TrafficLight("NWA");
    TrafficLight AUTO_OOST_NOORD = new TrafficLight("ONA");
    TrafficLight AUTO_OOST_WEST = new TrafficLight("OWA");
    TrafficLight AUTO_WEST_NOORD = new TrafficLight("WNA");
    TrafficLight AUTO_WEST_ZUID = new TrafficLight("WZA");
    TrafficLight AUTO_WEST_OOST = new TrafficLight("WOA");
    TrafficLight AUTO_ZUID_WEST = new TrafficLight("ZWA");
    TrafficLight AUTO_ZUID_NOORD = new TrafficLight("ZNA");
    TrafficLight AUTO_ZUID_OOST = new TrafficLight("ZOA");
    /* BUS */
    TrafficLight BUS_OOST_WEST;
    /* FIETS */
    TrafficLight FIETS_NOORD_ZUID;
    TrafficLight FIETS_ZUID_NOORD;
    /* VOETGANGERS */
    TrafficLight VOET_NOORD_ZUID;
    TrafficLight VOET_ZUID_NOORD;
    /* TREIN */
    TrafficLight TREIN_OOST_WEST;
    TrafficLight TREIN_WEST_OOST;
    public boolean next = true;
    private boolean running = true;
    char[] message;
    String light;
    int switcher;
    List<String> messageList = new ArrayList<String>();

    public TrafficSystem() {
        currentLight = new TrafficLight("");
        currentLight.setState("Done");
        Lock = new TrafficLock();
    }

    public void addToList(String message) {
        messageList.add(message);
    }
    
    private int VAN = 1;
    private int NAAR = 2;
    private int VOERTUIG = 3;
    private int AANTAL = 4;

    public void messageHandler() {
        if (messageList.size() > 0) {
            message = messageList.get(0).toCharArray();
            if (message[VAN] == 'N') {
                System.out.println(message);
                if (message[NAAR] == 'O' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_NOORD_OOST, message[AANTAL]);
                }
                if (message[NAAR] == 'Z' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_NOORD_ZUID, message[AANTAL]);
                }
                if (message[NAAR] == 'W' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_NOORD_WEST, message[AANTAL]);
                }
            } /*else if (message[VAN] == 'O' && message[VOERTUIG] == 'A') {
                if (message[NAAR] == 'N') {
                    lightHandler(AUTO_OOST_NOORD, "ON");
                }
                if (message[NAAR] == 'W' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_OOST_WEST, "OW");
                }
            } else if (message[VAN] == 'W' && message[VOERTUIG] == 'A') {
                if (message[NAAR] == 'N') {
                    lightHandler(AUTO_WEST_NOORD, "WN");
                }
                if (message[NAAR] == 'Z' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_WEST_ZUID, "WZ");
                }
                if (message[NAAR] == 'O' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_WEST_OOST, "WO");
                }
            } else if (message[VAN] == 'Z' && message[VOERTUIG] == 'A') {
                if (message[NAAR] == 'W') {
                    lightHandler(AUTO_ZUID_WEST, "ZW");
                }
                if (message[NAAR] == 'N' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_ZUID_NOORD, "ZN");
                }
                if (message[NAAR] == 'O' && message[VOERTUIG] == 'A') {
                    lightHandler(AUTO_ZUID_OOST, "ZO");
                }
            }*/
        }
    }

    public void lightHandler(TrafficLight light, char amount) {
        //light = new TrafficLight(sLight);
        currentLight = light;
        light.startTimer(0);
        messageList.remove(0);
        light.setRowAmount(amount);
        //System.out.println(messageList);
    }

    public void run() {
        while (running) {
            try {
                if (Lock.locked == false) {
                    messageHandler();
                    Lock.lock();
                }
            } catch (Exception e) {
            }
            try {
                if (currentLight.getState() == "Done") {
                    Lock.release();
                }
            } catch (Exception e) {
            }
        }
        /*
        
         if(next == true)
         {
            
            
         Collections.sort(traficLightPriorList, new Comparator<TraficLight>() {
         public int compare(TrafficLight o1, TrafficLight o2) {
         return o1.prio > o2.prio ? -1 : o1.prio == o2.prio ? 0 : 1;
         }
         });
         /*
       
         Lijst sorteren en kijken welke stoplichten aan en uit kunnen
        
         Logica voor welke prior eerst moet hierin
       
      
       
         current_TraficLight = traficLightPriorList.get(1);  
         current_TraficLight.StartTimer(0);
         traficLightPriorList.remove(current_TraficLight);
                
         } */
    }
}
