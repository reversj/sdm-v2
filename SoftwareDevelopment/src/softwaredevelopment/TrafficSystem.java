/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficSystem extends Thread {

    TrafficLight currentLight;
    TrafficLock Lock;
    /* AUTOS */
    TrafficLight AUTO_NOORD_OOST;
    TrafficLight AUTO_NOORD_ZUID;
    TrafficLight AUTO_NOORD_WEST;
    TrafficLight AUTO_OOST_NOORD;
    TrafficLight AUTO_OOST_WEST;
    TrafficLight AUTO_WEST_NOORD;
    TrafficLight AUTO_WEST_ZUID;
    TrafficLight AUTO_WEST_OOST;
    TrafficLight AUTO_ZUID_WEST;
    TrafficLight AUTO_ZUID_NOORD;
    TrafficLight AUTO_ZUID_OOST;
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
        currentLight.stat = "Done";
        Lock = new TrafficLock();
    }

    public void addToList(String message) {
        messageList.add(message);
    }
    private int VAN = 1;
    private int NAAR = 2;

    public void messageHandler() {
        if (messageList.size() > 0) {
            message = messageList.get(0).toCharArray();
            if (message[VAN] == 'N') {
                System.out.println(message);
                if (message[NAAR] == 'O') {
                    lightHandler(AUTO_NOORD_OOST, "NO");
                }
                if (message[NAAR] == 'Z') {
                    lightHandler(AUTO_NOORD_ZUID, "NZ");
                }
                if (message[NAAR] == 'W') {
                    lightHandler(AUTO_NOORD_WEST, "NW");
                }
            } else if (message[VAN] == 'O') {
                if (message[NAAR] == 'N') {
                    lightHandler(AUTO_OOST_NOORD, "ON");
                }
                if (message[NAAR] == 'W') {
                    lightHandler(AUTO_OOST_WEST, "OW");
                }
            } else if (message[VAN] == 'W') {
                if (message[NAAR] == 'N') {
                    lightHandler(AUTO_WEST_NOORD, "WN");
                }
                if (message[NAAR] == 'Z') {
                    lightHandler(AUTO_WEST_ZUID, "WZ");
                }
                if (message[NAAR] == 'O') {
                    lightHandler(AUTO_WEST_OOST, "WO");
                }
            } else if (message[VAN] == 'Z') {
                if (message[NAAR] == 'W') {
                    lightHandler(AUTO_ZUID_WEST, "ZW");
                }
                if (message[NAAR] == 'N') {
                    lightHandler(AUTO_ZUID_NOORD, "ZN");
                }
                if (message[NAAR] == 'O') {
                    lightHandler(AUTO_ZUID_OOST, "ZO");
                }
            }
        }
    }

    public void lightHandler(TrafficLight light, String sLight) {
        light = new TrafficLight(sLight);
        currentLight = light;
        light.startTimer(0);
        messageList.remove(0);
        System.out.println(messageList);
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
                if (currentLight.stat == "Done") {
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
