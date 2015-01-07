/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import javax.swing.JFrame;

/**
 *
 * @author Niels Riemersma (Jan ter Schure)
 */
public class SoftwareDevServer {

    /**
     * @param args the command line arguments
     */
    private static ServerFrame frame = new ServerFrame();
    private static TrafficSystem sys;
    private boolean active = true;
    private static String prevMessage;

    public static void main(String[] args) {
        sys = new TrafficSystem();

        frame();
        mServer.start();
        sys.start();
    }

    public static Server mServer = new Server(new Server.onMessageReceived() {
        public void messageReceived(String message) {

            try {
                sys.messageHandler(message.toCharArray());
                prevMessage = message;

            } catch (Exception e) {
            }
        }
    });

    public static void frame() {
        frame = new ServerFrame();
        frame.setSize(200, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public TrafficSystem getSys() {
        return sys;
    }

    public void setActive(boolean setter) {
        active = setter;
    }

    public boolean getActive() {
        return active;
    }
}
