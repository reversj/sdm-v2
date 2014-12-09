/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficSystem extends Thread {

    TrafficLight prevLight = new TrafficLight("CL");
    TrafficLight firstPrio;
    TrafficLight secPrio;
    TrafficLight thirdPrio;
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
    char[] message;
    String light;
    int switcher;

    private int VAN = 1;
    private int NAAR = 2;
    private int VOERTUIG = 3;
    private int AANTAL = 4;

    public TrafficSystem() {
        prevLight = new TrafficLight("PREV");
        firstPrio = new TrafficLight("FIRST");
        prevLight.deactivate();
    }

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
        if (firstPrio == null) {
            firstPrio = light;
        }
    }

    public void checkHighAmount() {
        if (firstPrio != null);
        {
            firstPrio = (AUTO_NOORD_WEST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_NOORD_WEST : firstPrio);
            firstPrio = (AUTO_NOORD_OOST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_NOORD_OOST : firstPrio);
            firstPrio = (AUTO_NOORD_ZUID.getRowAmount() > firstPrio.getRowAmount() ? AUTO_NOORD_ZUID : firstPrio);

            firstPrio = (AUTO_OOST_NOORD.getRowAmount() > firstPrio.getRowAmount() ? AUTO_OOST_NOORD : firstPrio);
            firstPrio = (AUTO_OOST_WEST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_OOST_WEST : firstPrio);

            firstPrio = (AUTO_WEST_NOORD.getRowAmount() > firstPrio.getRowAmount() ? AUTO_WEST_NOORD : firstPrio);
            firstPrio = (AUTO_WEST_ZUID.getRowAmount() > firstPrio.getRowAmount() ? AUTO_WEST_ZUID : firstPrio);
            firstPrio = (AUTO_WEST_OOST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_WEST_OOST : firstPrio);

            firstPrio = (AUTO_ZUID_WEST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_ZUID_WEST : firstPrio);
            firstPrio = (AUTO_ZUID_NOORD.getRowAmount() > firstPrio.getRowAmount() ? AUTO_ZUID_NOORD : firstPrio);
            firstPrio = (AUTO_ZUID_OOST.getRowAmount() > firstPrio.getRowAmount() ? AUTO_ZUID_OOST : firstPrio);

            System.out.println(firstPrio.getName() + " amount : " + firstPrio.getRowAmount());
        }
    }

    public void checkPossible(TrafficLight light) {
        TrafficLight[] possibleLights;

        if (light.getName().equals("NWA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_OOST_NOORD, AUTO_OOST_WEST, AUTO_ZUID_NOORD, AUTO_WEST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("NZA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_ZUID_NOORD};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("NOA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        }
        if (light.getName().equals("ZNA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_ZUID_WEST, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("ZWA")) {
            possibleLights = new TrafficLight[]{AUTO_ZUID_NOORD, AUTO_OOST_NOORD, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("ONA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_ZUID_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("OWA")) {
            possibleLights = new TrafficLight[]{AUTO_OOST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        }
        if (light.getName().equals("WNA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("WOA")) {
            possibleLights = new TrafficLight[]{AUTO_OOST_NOORD, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_WEST_NOORD, AUTO_WEST_ZUID};
            nextLights(possibleLights);
        } 
        if (light.getName().equals("WZA")) {
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_OOST_WEST, AUTO_ZUID_NOORD, AUTO_ZUID_WEST, AUTO_WEST_NOORD, AUTO_WEST_OOST};
            nextLights(possibleLights);
        }
    }

    public void nextLights(TrafficLight[] possibleLights) {
        TrafficLight SecondPrior = new TrafficLight("placeHolder");
        // TrafficLight SecondPrior = new TrafficLight("placeHolder");
        for (int i = 0; i < possibleLights.length; i++) {
            SecondPrior = (possibleLights[i].getRowAmount() > SecondPrior.getRowAmount() ? possibleLights[i] : SecondPrior);
        }
        firstPrio.startTimer(0);
        SecondPrior.activate();
        SecondPrior.startTimer(1);
        SecondPrior.resetRowAmount();
    }

    public void nextLight() {
        if (firstPrio.getRowAmount() > 0) {
            System.out.println("Next Light : " + firstPrio.getName());
            firstPrio.activate();
            //highestPrior.StartTimer(0);
            checkPossible(firstPrio);
            prevLight = firstPrio;
            prevLight.resetRowAmount();
        }
    }

    public void run() {
        while (running) {
            if (prevLight.getActive() == false) {
                nextLight();
                checkHighAmount();
            } else {

            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(TrafficSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
