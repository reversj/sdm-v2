/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Niels Riemersma (Jan ter Schure)
 */
public class ServerFrame extends JFrame {

    private final SoftwareDevServer softDevServer;
    //private LightHandler handler = new LightHandler();
    private Container frameTop = new Container();
    private JTextArea txtArea;
    private JLabel lblFrame;
    private JScrollPane scrollPane;
    //private JTextArea inputArea = new JTextArea();
    //public JButton btnSend = new JButton("Send");
    private Font myFont = new Font("Arial", Font.BOLD, 12);

    public ServerFrame() {
        softDevServer = new SoftwareDevServer();
        lblFrame = new JLabel("KRUISPUNT SIM");
        txtArea = new JTextArea();
        scrollPane = new JScrollPane(txtArea);

        frameTop.setLayout(new GridLayout(1, 3));
        //frameTop.add(inputArea);
        //frameTop.add(btnSend);
        //btnSend.addActionListener(this);
        frameTop.add(lblFrame);
        this.add(frameTop, BorderLayout.NORTH);
        txtArea.setFont(myFont);
        txtArea.setBackground(Color.LIGHT_GRAY);
        txtArea.append("\n" + "Sietse van der Werf\n" + "Niels Riemersma\n" + "NHL Informatica\n" + "SDM --- 2014");
        txtArea.setEditable(false);

        this.add(scrollPane);
    }
    
    /*@Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(btnSend)){
            //String msg = inputArea.getText();
            handler.firstPrio = handler.BUS_OOST_WEST;
        }
    }*/

    public void txtAreaAppend(String printMessage) {
        txtArea.append(printMessage + "\n");
    }
}
