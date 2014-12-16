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

    private final LightHandler lightHandler = new LightHandler();

    private final ServerFrame frame = new ServerFrame();
    private final boolean running = true;

    private final int VAN = 1;
    private final int NAAR = 2;
    private final int VOERTUIG = 3;
    private final int AANTAL = 4;

    public TrafficSystem() {
        lightHandler.prevLight.deactivate();
        lightHandler.secPrio.deactivate();
        lightHandler.thirdPrio.deactivate();
    }

    public void messageHandler(char[] message) {

        /* TREIN */
        if (message[VOERTUIG] == 'T') {
            if (message[VAN] == 'O') {
                lightHandler.TREIN_OOST_WEST.setRowAmount(message[AANTAL]);
            }
            if (message[VAN] == 'W') {
                lightHandler.TREIN_WEST_OOST.setRowAmount(message[AANTAL]);
            }
        }

        /* BUS */
        if (message[VOERTUIG] == 'B') {
            if (message[VAN] == 'O' && message[NAAR] == 'W') {
                lightHandler.BUS_OOST_WEST.setRowAmount(message[AANTAL]);
            }
        }

        /* AUTO */
        if (message[VOERTUIG] == 'A') {
            if (message[VAN] == 'N') {
                if (message[NAAR] == 'O') {
                    lightHandler.AUTO_NOORD_OOST.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'Z') {
                    lightHandler.AUTO_NOORD_ZUID.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'W') {
                    lightHandler.AUTO_NOORD_WEST.setRowAmount(message[AANTAL]);
                }
            }
            if (message[VAN] == 'O') {
                if (message[NAAR] == 'N') {
                    lightHandler.AUTO_OOST_NOORD.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'W') {
                    lightHandler.AUTO_OOST_WEST.setRowAmount(message[AANTAL]);
                }
            }
            if (message[VAN] == 'Z') {
                if (message[NAAR] == 'W') {
                    lightHandler.AUTO_ZUID_WEST.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'N' || message[NAAR] == 'O') {
                    lightHandler.AUTO_ZUID_NOORD.setRowAmount(message[AANTAL]);
                }
            }
            if (message[VAN] == 'W') {
                if (message[NAAR] == 'N') {
                    lightHandler.AUTO_WEST_NOORD.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'O') {
                    lightHandler.AUTO_WEST_OOST.setRowAmount(message[AANTAL]);
                }
                if (message[NAAR] == 'Z') {
                    lightHandler.AUTO_WEST_ZUID.setRowAmount(message[AANTAL]);
                }
            }
        }

        /* FIETS */
        if (message[VOERTUIG] == 'F') {
            // AAN DE NOORDKANT
            if (message[VAN] == 'O' && message[NAAR] == 'W') {
                lightHandler.FIETS_OOST_WEST.setRowAmount(message[AANTAL]);
            }
            // AAN DE OOSTKANT
            if (message[VAN] == 'N' && message[NAAR] == 'Z') {
                lightHandler.FIETS_NOORD_ZUID.setRowAmount(message[AANTAL]);
            }
            // AAN DE WESTKANT
            if (message[VAN] == 'Z' && message[NAAR] == 'N') {
                lightHandler.FIETS_ZUID_NOORD.setRowAmount(message[AANTAL]);
            }
        }

        /* VOETGANGER */
        if (message[VOERTUIG] == 'V') {
            // AAN DE NOORDKANT
            if (message[VAN] == 'O' && message[NAAR] == 'W') {
                lightHandler.FIETS_OOST_WEST.setRowAmount(message[AANTAL]);
            }
            // AAN DE OOSTKANT
            if (message[VAN] == 'N' && message[NAAR] == 'Z') {
                lightHandler.FIETS_NOORD_ZUID.setRowAmount(message[AANTAL]);
            }
            // AAN DE WESTKANT
            if (message[VAN] == 'Z' && message[NAAR] == 'N') {
                lightHandler.FIETS_ZUID_NOORD.setRowAmount(message[AANTAL]);
            }
        }
    }

    public void run() {
        while (running) {
            if (lightHandler.prevLight.getActive() == false && lightHandler.secPrio.getActive() == false && lightHandler.thirdPrio.getActive() == false) {
                lightHandler.nextLight();
                lightHandler.checkHighAmount(lightHandler.ALL_LIGHTS);
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
