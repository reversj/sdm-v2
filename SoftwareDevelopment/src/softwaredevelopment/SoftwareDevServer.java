/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import javax.swing.JFrame;

/**
 *
 * @author Niels Riemersma
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

    public void response() {
        mServer.send("");
    }
    public static Server mServer = new Server(new Server.onMessageReceived() {
        public void messageReceived(String message) {
            frame.txtAreaAppend("Client: " + message + "\n");

            if (!sys.messageList.contains(message)) {
                try {
                    sys.addToList(message);
                    prevMessage = message;

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    });

    public static void frame() {
        //opens the window where the messages will be received and sent
        frame = new ServerFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public TrafficSystem getSys(){
        return sys;
    }
    
    public void setActive(boolean setter){
        active = setter;
    }
    
    public boolean getActive(){
        return active;
    }
}
