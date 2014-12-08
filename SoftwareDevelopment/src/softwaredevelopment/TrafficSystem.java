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

    TrafficLight prevLight;
    TrafficLight highPriorityLight;
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

    List<TrafficLight> lightRowList = new ArrayList<TrafficLight>();

    public void list() {
        lightRowList.add(AUTO_NOORD_OOST);
        lightRowList.add(AUTO_NOORD_ZUID);
        lightRowList.add(AUTO_NOORD_WEST);
        lightRowList.add(AUTO_OOST_NOORD);
        lightRowList.add(AUTO_OOST_WEST);
        lightRowList.add(AUTO_WEST_NOORD);
        lightRowList.add(AUTO_WEST_ZUID);
        lightRowList.add(AUTO_WEST_OOST);
        lightRowList.add(AUTO_ZUID_WEST);
        lightRowList.add(AUTO_ZUID_NOORD);
        lightRowList.add(AUTO_ZUID_OOST);
    }

    /* BUS */
    TrafficLight BUS_OOST_WEST = new TrafficLight("OWB");
    /* FIETS */
    TrafficLight FIETS_NOORD_ZUID = new TrafficLight("NZF");
    TrafficLight FIETS_ZUID_NOORD = new TrafficLight("ZNF");
    /* VOETGANGERS */
    TrafficLight VOET_NOORD_ZUID = new TrafficLight("NZV");
    TrafficLight VOET_ZUID_NOORD = new TrafficLight("ZNV");
    /* TREIN */
    TrafficLight TREIN_OOST_WEST = new TrafficLight("OWT");
    TrafficLight TREIN_WEST_OOST = new TrafficLight("WOT");

    public boolean next = true;
    private boolean running = true;
    String light;
    int switcher;

    public TrafficSystem() {
        highPriorityLight = new TrafficLight("HPL");
        prevLight = new TrafficLight("PREV");
        prevLight.deactivate();
    }

    private int VAN = 1;
    private int NAAR = 2;
    private int VOERTUIG = 3;
    private int AANTAL = 4;

    public void messageHandler(char[] message) {
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
        } else if (message[VAN] == 'O' && message[VOERTUIG] == 'A') {
            if (message[NAAR] == 'N') {
                lightHandler(AUTO_OOST_NOORD, message[AANTAL]);
            }
            if (message[NAAR] == 'W' && message[VOERTUIG] == 'A') {
                lightHandler(AUTO_OOST_WEST, message[AANTAL]);
            }
        } else if (message[VAN] == 'W' && message[VOERTUIG] == 'A') {
            if (message[NAAR] == 'N') {
                lightHandler(AUTO_WEST_NOORD, message[AANTAL]);
            }
            if (message[NAAR] == 'Z' && message[VOERTUIG] == 'A') {
                lightHandler(AUTO_WEST_ZUID, message[AANTAL]);
            }
            if (message[NAAR] == 'O' && message[VOERTUIG] == 'A') {
                lightHandler(AUTO_WEST_OOST, message[AANTAL]);
            }
        } else if (message[VAN] == 'Z' && message[VOERTUIG] == 'A') {
            if (message[NAAR] == 'W') {
                lightHandler(AUTO_ZUID_WEST, message[AANTAL]);
            }
            if (message[NAAR] == 'N' && message[VOERTUIG] == 'A') {
                lightHandler(AUTO_ZUID_NOORD, message[AANTAL]);
            }
            if (message[NAAR] == 'O' && message[VOERTUIG] == 'A') {
                lightHandler(AUTO_ZUID_OOST, message[AANTAL]);
            }
        }
    }

    public void lightHandler(TrafficLight light, char amount) {
        light.setRowAmount(amount);
        if (highPriorityLight == null) {
            highPriorityLight = light;
        }
    }

    public void checkHighAmount() {
        if (highPriorityLight != null) {
            highPriorityLight = (AUTO_NOORD_WEST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_NOORD_WEST : highPriorityLight);
            highPriorityLight = (AUTO_NOORD_OOST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_NOORD_OOST : highPriorityLight);
            highPriorityLight = (AUTO_NOORD_ZUID.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_NOORD_ZUID : highPriorityLight);

            highPriorityLight = (AUTO_OOST_NOORD.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_OOST_NOORD : highPriorityLight);
            highPriorityLight = (AUTO_OOST_WEST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_OOST_WEST : highPriorityLight);

            highPriorityLight = (AUTO_WEST_NOORD.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_WEST_NOORD : highPriorityLight);
            highPriorityLight = (AUTO_WEST_ZUID.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_WEST_ZUID : highPriorityLight);
            highPriorityLight = (AUTO_WEST_OOST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_WEST_OOST : highPriorityLight);

            highPriorityLight = (AUTO_ZUID_WEST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_ZUID_WEST : highPriorityLight);
            highPriorityLight = (AUTO_ZUID_NOORD.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_ZUID_NOORD : highPriorityLight);
            highPriorityLight = (AUTO_ZUID_OOST.getRowAmount() > highPriorityLight.getRowAmount() ? AUTO_ZUID_OOST : highPriorityLight);

            System.out.println(highPriorityLight.getName() + " amount : " + highPriorityLight.getRowAmount());
        }
    }

    public void nextLight() {
        /*for (int i = 0; i < 3; i++){
         lightRowList.get(i).activate();
         lightRowList.get(i).startTimer(0);
         prevLight = lightRowList.get(i);
         prevLight.resetRowAmount();
         }*/

        if (highPriorityLight.getRowAmount() > 0) {
            System.out.println("Next Light : " + highPriorityLight.getName());
            highPriorityLight.activate();
            highPriorityLight.startTimer(0);
            String conflicts[] = highPriorityLight.getConflicts();
            for (int i = 0; i < lightRowList.size(); i++) {
                for (int p = 0; p < conflicts.length; p++) {
                    if (!lightRowList.get(i).getName().equals(conflicts[p])) {
                        lightRowList.get(i).activate();
                        lightRowList.get(i).startTimer(0);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception e){
                        
                    }
                }
            }
            prevLight = highPriorityLight;
            prevLight.resetRowAmount();
        }
    }

    public void run() {
        while (running) {
            if (prevLight.getActive() == false) {
                checkHighAmount();
                nextLight();
            } else {
                checkHighAmount();
            }
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {

        }
    }
}
