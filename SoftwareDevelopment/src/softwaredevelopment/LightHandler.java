/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

/**
 *
 * @author Niels Riemersma
 */
public class LightHandler {
    //private ServerFrame frame = new ServerFrame();

    TrafficLight prevLight = new TrafficLight("PREV");
    TrafficLight firstPrio = new TrafficLight("FIRST");
    TrafficLight secPrio = new TrafficLight("SECOND");
    TrafficLight thirdPrio = new TrafficLight("THIRD");
    TrafficLight fietsLight = new TrafficLight("FIETS");
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
    TrafficLight AUTO_ZUID_NOORD = new TrafficLight("ZOA"); /// <<<<==== EROOR MOGEOGELIJKE HENDE

    /* BUS */
    TrafficLight BUS_OOST_WEST = new TrafficLight("OWB");

    /* FIETS */
    TrafficLight FIETS_NOORD_ZUID = new TrafficLight("NZF");
    TrafficLight FIETS_ZUID_NOORD = new TrafficLight("ZNF");
    TrafficLight FIETS_OOST_WEST = new TrafficLight("OWF");

    /* VOETGANGERS */
    /*TrafficLight VOET_NOORD_ZUID = new TrafficLight("NZV");
     TrafficLight VOET_ZUID_NOORD = new TrafficLight("ZNV");
     TrafficLight VOET_OOST_WEST = new TrafficLight("OWV");*/

    /* TREIN */
    TrafficLight TREIN_OOST_WEST = new TrafficLight("OWT");
    TrafficLight TREIN_WEST_OOST = new TrafficLight("WOT");

    TrafficLight[] ALL_LIGHTS = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_NOORD_ZUID, AUTO_OOST_NOORD, AUTO_OOST_WEST,
        AUTO_WEST_NOORD, AUTO_WEST_ZUID, AUTO_WEST_OOST, AUTO_ZUID_WEST, AUTO_ZUID_NOORD};

    public TrafficLight[] checkPossible(TrafficLight light) {
        TrafficLight[] possibleLights = null;
        /* BUS */
        if (light.getName().equals("OWB")) { // 2
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_WEST_OOST, AUTO_WEST_ZUID, FIETS_OOST_WEST};
        }
        /* AUTOS */
        if (light.getName().equals("NWA")) { // 7
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_OOST_NOORD, AUTO_ZUID_NOORD, AUTO_WEST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID, FIETS_ZUID_NOORD};
        }
        if (light.getName().equals("NZA")) { // 5
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_ZUID_NOORD, FIETS_NOORD_ZUID, FIETS_ZUID_NOORD /*AUTO_ZUID_OOST*/};
        }
        if (light.getName().equals("NOA")) { // 4
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_WEST_ZUID, FIETS_NOORD_ZUID};
        }
        if (light.getName().equals("ZOA")) { // 4 <<<<<<<<<<< EOROROEOEOE OEJEOEJEOJE
            possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_ZUID_WEST, AUTO_WEST_ZUID, FIETS_NOORD_ZUID, FIETS_ZUID_NOORD};
        }
        /*if (light.getName().equals("ZOA")) { // 4
         possibleLights = new TrafficLight[]{AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_ZUID_WEST, AUTO_WEST_ZUID, FIETS_NOORD_ZUID};
         }*/
        if (light.getName().equals("ZWA")) { // 4
            possibleLights = new TrafficLight[]{AUTO_ZUID_NOORD, /*AUTO_ZUID_OOST,*/ AUTO_OOST_NOORD, AUTO_WEST_ZUID, FIETS_ZUID_NOORD, FIETS_OOST_WEST};
        }
        if (light.getName().equals("ONA")) { // 7
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_ZUID_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID, FIETS_NOORD_ZUID};
        }
        if (light.getName().equals("OWA")) { // 3
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_OOST_NOORD, AUTO_WEST_OOST, AUTO_WEST_ZUID, FIETS_OOST_WEST};
        }
        if (light.getName().equals("WNA")) { // 3
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID, FIETS_ZUID_NOORD};
        }
        if (light.getName().equals("WOA")) { // 5
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_OOST_NOORD, AUTO_NOORD_WEST, AUTO_OOST_WEST, AUTO_WEST_NOORD, AUTO_WEST_ZUID, FIETS_OOST_WEST};
        }
        if (light.getName().equals("WZA")) { // 9
            possibleLights = new TrafficLight[]{AUTO_NOORD_OOST, AUTO_NOORD_WEST, AUTO_OOST_NOORD, AUTO_OOST_WEST, AUTO_ZUID_NOORD, /*AUTO_ZUID_OOST,*/ AUTO_ZUID_WEST, AUTO_WEST_NOORD, AUTO_WEST_OOST, FIETS_ZUID_NOORD, FIETS_OOST_WEST};
        }

        if (light.getName().equals("OWF")) {
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_OOST_WEST, AUTO_WEST_OOST, AUTO_WEST_ZUID, AUTO_ZUID_WEST, FIETS_NOORD_ZUID, FIETS_ZUID_NOORD};
        }
        if (light.getName().equals("NZF")) {
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_OOST, AUTO_NOORD_ZUID, AUTO_ZUID_NOORD, AUTO_OOST_NOORD, FIETS_ZUID_NOORD, FIETS_OOST_WEST};
        }
        if (light.getName().equals("ZNF")) {
            possibleLights = new TrafficLight[]{TREIN_OOST_WEST, TREIN_WEST_OOST, AUTO_NOORD_WEST, AUTO_NOORD_ZUID, AUTO_ZUID_NOORD, AUTO_WEST_NOORD, AUTO_WEST_ZUID, AUTO_ZUID_WEST, FIETS_OOST_WEST, FIETS_NOORD_ZUID};
        }
        return possibleLights;
    }

    public void checkHighAmount(TrafficLight[] all_lights) {
        if (firstPrio != null);
        {
            if (TREIN_OOST_WEST.getRowAmount() != 0) {
                firstPrio = TREIN_OOST_WEST;
                TREIN_OOST_WEST.resetRowAmount();
            }
            if (TREIN_WEST_OOST.getRowAmount() != 0) {
                firstPrio = TREIN_WEST_OOST;
                TREIN_WEST_OOST.resetRowAmount();
            }
            if (BUS_OOST_WEST.getRowAmount() != 0) {
                firstPrio = BUS_OOST_WEST;
                BUS_OOST_WEST.resetRowAmount();
            }
            for (int i = 0; i < all_lights.length; i++) {
                if (all_lights[i].getRowAmount() > firstPrio.getRowAmount()) {
                    firstPrio = all_lights[i];
                }
                firstPrio.resetcalled();
            }
        }
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

    public void bikeLight(TrafficLight[] lights1, TrafficLight[] lights2, TrafficLight[] lights3) {
        for (int i = 0; i < lights1.length; i++) {
            for (int o = 0; o < lights2.length; o++) {
                for (int p = 0; p < lights3.length; p++) {
                    if (lights1[i].getName().equals(lights2[o].getName()) && lights1[i].getName().equals(lights3[p].getName())) {
                        if (lights1[i].getName().equals("NZF") || lights1[i].getName().equals("ZNF") || lights1[i].getName().equals("OWF")) {
                            String tempname = lights1[i].getName();
                            if (!fietsLight.getName().equals(tempname)) {
                                fietsLight = lights1[i];
                                fietsLight.activate();
                                fietsLight.resetRowAmount();
                                fietsLight.startTimer(0);
                            }
                        }
                    }
                }
            }
        }
        activateLights();
    }

    public void activateLights() {
        firstPrio.startTimer(0);
        firstPrio.called();
        secPrio.startTimer(0);
        secPrio.called();
        thirdPrio.startTimer(0);
        thirdPrio.called();
        System.out.println("FIRST PRIO = " + firstPrio.getName() + ", CALLED " + firstPrio.getCalled() + " TIMES.");
        System.out.println("SECOND PRIO = " + secPrio.getName() + ", CALLED " + secPrio.getCalled() + " TIMES.");
        System.out.println("THIRD PRIO = " + thirdPrio.getName() + ", CALLED " + thirdPrio.getCalled() + " TIMES.");
    }

    public void nextLight() {
        if (firstPrio.getRowAmount() > 0) {
            firstPrio.activate();
            secLight(checkPossible(firstPrio));
            prevLight = firstPrio;
            prevLight.resetRowAmount();
        }
    }

}
