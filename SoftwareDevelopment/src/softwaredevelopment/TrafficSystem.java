/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

/**
 *
 * @author Niels Riemersma
 */
public class TrafficSystem extends Thread {

    TrafficLight prevLight;
    TrafficLight firstPrio;
    TrafficLight secPrio = new TrafficLight("SECOND");
    TrafficLight thirdPrio = new TrafficLight("THIRD");

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

    TrafficLight[] AUTO_LIGHTS = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_NOORD_ZUID, AUTO_OOST_NOORD, AUTO_OOST_WEST,
        AUTO_WEST_NOORD, AUTO_WEST_ZUID, AUTO_WEST_OOST, AUTO_ZUID_WEST, AUTO_ZUID_NOORD, AUTO_ZUID_OOST};

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
        secPrio.deactivate();
        thirdPrio.deactivate();
    }

    public void messageHandler(char[] message) {
        if (message[VAN] == 'N') {
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
        if (message[VAN] == 'O' && message[NAAR] == 'W' && message[VOERTUIG] == 'B') {
            firstPrio = BUS_OOST_WEST;
        }
    }

    public void lightHandler(TrafficLight light, char amount) {
        light.setRowAmount(amount);
        if (firstPrio == null) {
            firstPrio = light;
        }
    }

    public void checkHighAmount(TrafficLight[] all_lights) {
        if (firstPrio != null);
        {
            for (int i = 0; i < all_lights.length; i++) {
                if (all_lights[i].getRowAmount() > 8) {
                    firstPrio = all_lights[i];
                } else if (all_lights[i].getRowAmount() > firstPrio.getRowAmount()) {
                    firstPrio = all_lights[i];
                }
            }
        }
    }

    public TrafficLight[] checkPossible(TrafficLight light) {
        TrafficLight[] possibleLights;

        if (light.getName().equals("OWB")) { // 2
            possibleLights = new TrafficLight[]{AUTO_WEST_OOST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("NWA")) { // 7
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_OOST_NOORD, AUTO_ZUID_NOORD, AUTO_WEST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("NZA")) { // 5
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_ZUID_NOORD, AUTO_ZUID_OOST};
            return possibleLights;
        }
        if (light.getName().equals("NOA")) { // 4
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("ZNA")) { // 4
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_ZUID_WEST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("ZOA")) { // 4
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_ZUID_WEST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("ZWA")) { // 4
            possibleLights = new TrafficLight[]{AUTO_ZUID_NOORD, AUTO_ZUID_OOST, AUTO_OOST_NOORD, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("ONA")) { // 7
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_ZUID_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("OWA")) { // 3
            possibleLights = new TrafficLight[]{AUTO_OOST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("WNA")) { // 3
            possibleLights = new TrafficLight[]{AUTO_NOORD_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("WOA")) { // 5
            possibleLights = new TrafficLight[]{AUTO_OOST_NOORD, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_WEST_NOORD, AUTO_WEST_ZUID};
            return possibleLights;
        }
        if (light.getName().equals("WZA")) { // 9
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_OOST_WEST, AUTO_ZUID_NOORD, AUTO_ZUID_OOST, AUTO_ZUID_WEST, AUTO_WEST_NOORD, AUTO_WEST_OOST};
            return possibleLights;
        }
        return null;
    }

    public void secLight(TrafficLight[] possibleLights) {
        secPrio = possibleLights[1];

        for (int i = 0; i < possibleLights.length; i++) {
            if (possibleLights[i].getRowAmount() > secPrio.getRowAmount()) {
                String tempname = possibleLights[i].getName();
                if (!secPrio.getName().equals(tempname)) {
                    secPrio = possibleLights[i];
                }
            }
        }

        secPrio.activate();
        secPrio.resetRowAmount();
        thirdLight(checkPossible(secPrio), possibleLights);
    }

    public void thirdLight(TrafficLight[] firstLights, TrafficLight[] secLights) {

        for (int i = 0; i < firstLights.length; i++) {
            for (int j = 0; j < secLights.length; j++) {
                if (firstLights[i].getName().equals(secLights[j].getName())) {
                    String tempname = firstLights[i].getName();
                    if (!thirdPrio.getName().equals(tempname) && (!tempname.equals(firstPrio.getName())) && (!tempname.equals(secPrio.getName()))) {
                        thirdPrio = firstLights[i];
                    }
                }
            }
        }
        thirdPrio.activate();
        thirdPrio.resetRowAmount();
        activateLights();
    }

    public void activateLights() {
        firstPrio.startTimer(0);
        secPrio.startTimer(0);
        thirdPrio.startTimer(0);
        System.out.println("Activating lights at:");
        System.out.println(firstPrio.getName());
        System.out.println(secPrio.getName());
        System.out.println(thirdPrio.getName());
        System.out.println("------------------------");
    }

    public void nextLight() {
        if (firstPrio.getRowAmount() > 0) {
            firstPrio.activate();
            firstPrio.resetRowAmount();
            secLight(checkPossible(firstPrio));
            prevLight = firstPrio;
            prevLight.resetRowAmount();
        }
    }

    public void run() {
        while (running) {
            if (prevLight.getActive() == false && secPrio.getActive() == false && thirdPrio.getActive() == false) {
                nextLight();
                checkHighAmount(AUTO_LIGHTS);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(TrafficSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
