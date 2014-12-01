/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jan
 */
public class Server extends Thread {

    ServerSocket serverSocket;
    private Server.onMessageReceived messageListener;
    public static final int SERVERPORT = 4443;
    private boolean running = false;
    PrintWriter outw;
    BufferedReader inpr;

    public Server(Server.onMessageReceived messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void run() {
        super.run();

        running = true;

        try {
            System.out.println("S: Connecting to Unity");

            //Create a server socket on port 4443 (10 connect attempts max.)
            serverSocket = new ServerSocket(4443, 10);

            //Create client socket and accept() the incoming client.
            Socket socket = serverSocket.accept();
            System.out.println("S: Connection with client has been made");

            try {
                //Sends a message to the client
                outw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

                //Reads the message received from client                
                inpr = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //This while listens for messages
                while (running) {
                    String message = inpr.readLine();

                    if (message != null && messageListener != null) {
                        messageListener.messageReceived(message);
                    }
                }
            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            } finally {
                socket.close();
                System.out.println("S: Done.");
            }
        } catch (Exception e) {
            System.out.println("S: Could not connect.");
            e.printStackTrace();
        }
    }

    public interface onMessageReceived {

        public void messageReceived(String message);
    }

    public void send(String sendString) {
        try {
            outw.println(sendString);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
